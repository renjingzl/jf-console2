package com.atguigu.jf.console.user.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.jf.console.comm.utils.VerifyCodeUtil;
import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.LoginService;

@Controller
@RequestMapping("/logincontroller")
public class LoginHandler {
	
	@Autowired
	private LoginService loginService;
	
	
	//生成验证码
	@RequestMapping("/getVerifyCode")
	public void VerifyCode(HttpServletRequest request,
							HttpServletResponse response,
							HttpSession session) throws IOException {
		
		//设置页面不缓存
	   response.setHeader("Pragma", "no-cache");//HTTP 1.1
	   response.setHeader("Cache-Control", "no-cache");//HTTP 1.0
	   response.setDateHeader("Expires", 0);//设置缓存时间，0就是不缓存

		
		//生成随机字符串
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		session.setAttribute("verifyCode", verifyCode);
		
		 //设置输出的内容的类型为JPEG图像
		 response.setContentType("image/jpeg");
		 //将生成的验证码生成为图片流
		 BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
		 //写给浏览器
		 ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	}
	
	
	
	//登录
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("username") String username,
						@RequestParam("password") String password,
						@RequestParam("verifyCode") String verifyCode,
						HttpSession session,
						RedirectAttributes redirectAttributes) throws Exception {
		
		String verifyCodeStr = (String)session.getAttribute("verifyCode");
		if(!verifyCode.equals(verifyCodeStr)) {
			redirectAttributes.addFlashAttribute("errMsg", "验证码错误！");
			return "redirect:/login";
		}
		
		/*	之前的普通验证用户名、秘密的方法
		SysOp sysOp = new SysOp();
		sysOp.setLoginCode(username);
		sysOp.setLoginPasswd(password);
		
		SysOp currentUser = loginService.selectSysOpByNameAndPwd(sysOp);
		if(currentUser == null) {
			redirectAttributes.addFlashAttribute("errMsg", "用户名或密码错误！");
			return "redirect:/login";
		}
		session.setAttribute("currentUser", currentUser);*/
		
		
		// 核心代码：获取到当前的Subject对象，代表着当前的用户信息
        Subject currentUser = SecurityUtils.getSubject();
        
        // 验证是否登录
        if (!currentUser.isAuthenticated()) {
        	// 使用前台的用户名和密码构造UsernamePasswordToken。
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            try {
            	// ******执行登录，传入的是上面包装好的token
                currentUser.login(token);	//通过框架，去了JFRealm页面的认证方法
                
                //currentUser.getPrincipals()就是当前登录的SysOp对象，由JFRealm中的认证方法返回的info带来的
                session.setAttribute("currentUser", currentUser.getPrincipal());
            } 
            catch (AuthenticationException ae) {
            	// 捕获所有shiro认证的异常
            	redirectAttributes.addFlashAttribute("errMsg", "用户名或密码错误！");
    			return "redirect:/login";
            }
        }
		return "redirect:/index";
	}
	
	
	
	//生成菜单列表(@ResponseBody可以直接将方法返回的数据转成Json类型)
	@RequestMapping("/getMenu")
	@ResponseBody
	public List<SysFuncBean> getMenu(HttpSession session) throws Exception {
		SysOp currentUser = (SysOp) session.getAttribute("currentUser");
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(currentUser != null) {
			map.put("opId", currentUser.getOpId());
		}
		
		List<SysFuncBean> list = loginService.selectSysFuncByOpId(map);
		
		List<SysFuncBean> newList = new ArrayList<>();	// 放返回结果
		
		//循环查出来的列表，每一项都是一个SysFuncBean对象，用getFuncLevel()属性判断是否为一级菜单
		for (int i = 0; i < list.size(); i++) {	
			SysFuncBean parent = list.get(i);
			if(parent.getFuncLevel().equals(new Short("1"))) {	
				
				List<SysFuncBean> childlist = new ArrayList<>();	//构造子节点的树
				
				//再次循环查出来的列表,子节点的SupFuncId == 上一次循环的父节点的Id,即说明它是二级菜单
				for (int j = 0; j < list.size(); j++) {	
					SysFuncBean child = list.get(j);
					if(parent.getFuncId().equals(child.getSupFuncId())) {
						childlist.add(child);
					}
				}
				parent.setChildren(childlist);
				newList.add(parent);
			}
		}
		return newList;
	}
	
}
