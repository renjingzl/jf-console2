package com.atguigu.jf.console.role.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.jf.console.role.bean.bo.FuncTreeBean;
import com.atguigu.jf.console.role.bean.bo.SysRoleFunc;
import com.atguigu.jf.console.role.bean.pojo.SysRole;
import com.atguigu.jf.console.role.service.RoleService;
import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("role")
public class RoleHandler {
	
	@Autowired
	private RoleService roleService;
	
	
	//查询角色列表
	@RequestMapping("getRoleList")
	@ResponseBody
	public PageInfo<SysRole> getRoleList(String roleName,Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(roleName != "") {
			map.put("roleName", roleName);
		}
		
		PageHelper.startPage(page, limit);
		List<SysRole> list = roleService.selectSysRole(map);
		
		PageInfo<SysRole> pageInfo = new PageInfo<>(list);
		
		return pageInfo;
	}
	
	
	
	//查询所有功能，构造树结构(需要封装一个新的FuncTreeBean，属性与前台需要的json字符串对应)
	@RequestMapping("/getAllSysFunc")
	@ResponseBody
	public List<FuncTreeBean> getAllSysFunc() {
		List<FuncTreeBean> list = new ArrayList<>();	//用于返回处理完的json数据
		
		List<SysFuncBean> AllList = roleService.selectAllSysFuncBean();
		for (SysFuncBean sysFuncBean : AllList) {
			
			//新封装一个FuncTreeBean类是因为原有的类中funcName属性生成json格式后,
			//前台构建树解析不了，必须将funcName改为name属性
			FuncTreeBean ftb = new FuncTreeBean();	
			
			ftb.setFuncId(sysFuncBean.getFuncId());
			ftb.setSupFuncId(sysFuncBean.getSupFuncId());
			ftb.setName(sysFuncBean.getFuncName());
			
			list.add(ftb);
		}
		return list;
	}
	
	
	
	//根据角色Id查询功能，用于回显树
	@RequestMapping("/getSysFuncByRoleName")
	@ResponseBody
	public List<FuncTreeBean> getSysFuncByRoleName(String roleId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("roleId", roleId);
		
		List<FuncTreeBean> list = new ArrayList<>();	//用于返回处理完的json数据
		
		List<Long> FuncId = roleService.selectFuncByRoleId(map);	//该角色拥有的功能Id
		List<SysFuncBean> allFunc = roleService.selectAllSysFuncBean();
		
		for (SysFuncBean sysFuncBean : allFunc) {
			
			FuncTreeBean ftb = new FuncTreeBean();
			
			ftb.setFuncId(sysFuncBean.getFuncId());
			ftb.setSupFuncId(sysFuncBean.getSupFuncId());
			ftb.setName(sysFuncBean.getFuncName());
			
			//如果该角色拥有的功能Id包含所有功能列表的Id,则选中
			if(FuncId.contains(sysFuncBean.getFuncId())) {	
				ftb.setChecked(true);
				ftb.setOpen(true);
			}
			list.add(ftb);
		}
		return list;
	}

	
	
	
	//更新功能
	@RequestMapping("/updatSysFunc")
	@ResponseBody
	public void updatSysFunc(@RequestParam("sysFuncIdStr") String sysFuncIdStr,
										  @RequestParam("roleId") String roleId) throws Exception {
		//封装参数，用roleId删除该角色拥有的功能
		Map<String, Object> map = new HashMap<>();
		map.put("roleId", roleId);
		
		//先删除树
		roleService.deleteFuncByRoleId(map);
		
		//再新增树
		//将从前台接收过来的被选中功能Id的字符串：sysFuncIdStr，转为集合
		String sysIdStr = sysFuncIdStr.substring(1);
		String[] sysIdArr = sysIdStr.split(",");
		List<String> sysFuncIdList = Arrays.asList(sysIdArr);
			
		List<SysRoleFunc> sysRoleFuncList = new ArrayList<>();	//传给mapper去插入中间表的数据
			
		for (String FuncId : sysFuncIdList) {
			SysRoleFunc srf = new SysRoleFunc();	//创建跟中间表对应的类，用来向中间表中插入数据
				
			srf.setFuncId(Long.parseLong(FuncId));
			srf.setRoleId(Long.parseLong(roleId));
			srf.setCreator(1l);
			srf.setModifyDate(new Date());
			sysRoleFuncList.add(srf);
		}
		roleService.inertBatchSysRoleFunc(sysRoleFuncList);
	}
}
