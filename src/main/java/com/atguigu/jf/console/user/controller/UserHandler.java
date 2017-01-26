package com.atguigu.jf.console.user.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.jf.console.comm.bean.bo.ResultBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("user")
public class UserHandler {
	
	@Autowired
	private UserService userService;
	
	
	/*//分页查询管理员用户列表(自己写的page)
	@RequestMapping("/getSysOpPageList")
	@ResponseBody
	public PageModel<SysOp> getSysOpPageList(SysOp sysOp,Integer start, Integer limit) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("limit", limit);
		if(sysOp.getOpName() != "") {
			map.put("opName", sysOp.getOpName());
		}
		map.put("opKind", sysOp.getOpKind());
		
		Integer count = userService.selectSysOpListCount(map);
		List<SysOp> pageList = userService.selectSysOpPageList(map);
		
		PageModel<SysOp> page = new PageModel<>();
		page.setPagelist(pageList);
		page.setTotalCount(count);
		page.setPageNo(start);
		page.setPageSize(limit);
		
		return page;
	}*/
	
	
	
	/*//分页查询管理员用户列表(利用分页插件)
	@RequestMapping("/getSysOpPageList")
	@ResponseBody
	public PageInfo<SysOp> getSysOpPageListByHelper(SysOp sysOp,Integer page, Integer limit) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		//若不判断，前台传的opName是空字符串，即用管理员类型和名称为空字符串的查询条件进行查询的，所以没有用户
		if(sysOp.getOpName() != "") {		
			map.put("opName", sysOp.getOpName());
		}
		map.put("opKind", sysOp.getOpKind());
		
		PageHelper.startPage(page, limit);
		List<SysOp> pageList = userService.selectSysOpPageList(map);
		
		PageInfo<SysOp> pageInfo = new PageInfo<>(pageList);
		return pageInfo;
	}*/
	
	
	
	//分页查询管理员用户列表(使用JSON.toJSONString序列化方式，将对象转为JSON字符串)
	@RequestMapping("/getSysOpPageList")
	@ResponseBody
	public String getSysOpPageListByHelper(SysOp sysOp,Integer page, Integer limit) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		//若不判断，前台传的opName是空字符串，即用管理员类型和名称为空字符串的查询条件进行查询的，所以没有用户
		if(sysOp.getOpName() != "") {		
			map.put("opName", sysOp.getOpName());
		}
		map.put("opKind", sysOp.getOpKind());
		
		PageHelper.startPage(page, limit);
		List<SysOp> pageList = userService.selectSysOpPageList(map);
		
		PageInfo<SysOp> pageInfo = new PageInfo<>(pageList);
		
		
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
		String result = JSON.toJSONString(pageInfo,
										  SerializerFeature.WriteDateUseDateFormat,
										  SerializerFeature.WriteMapNullValue, 
										  SerializerFeature.WriteNullNumberAsZero,
										  SerializerFeature.WriteNullStringAsEmpty);
		return result;
	}
	
	
	
	//去添加或修改用户页面
	@RequestMapping("/addUserPage")
	public String addUserPage(String type,String opId,Map<String, Object> map) {
		map.put("type", type);
		map.put("opId", opId);
		
		if("modify".equals(type)) {
			SysOp sysOp = userService.selectByPrimaryKey(Long.parseLong(opId));
			map.put("sysOp", sysOp);
		}
		return "user/addUser";
	}
	
	
	//添加用户
	@RequestMapping("/addUser")
	@ResponseBody
	public ResultBean addUser(SysOp sysOp) {
		
		int i = userService.insertSelective(sysOp);
		
		ResultBean res = new ResultBean();
		if(i < 0) {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("新增用户失败！");
		}else {
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("新增用户成功！");
		}
		return res;
	}
	
	
	
	
	//修改用户
	@RequestMapping("/modifyUser")
	@ResponseBody
	public ResultBean modifyUser(SysOp sysOp) {
		
		int i = userService.updateByPrimaryKeySelective(sysOp);
		
		ResultBean res = new ResultBean();
		if(i < 0) {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("修改用户失败！");
		}else {
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("修改用户成功！");
		}
		return res;
	}
	
	
	
	
	
	/*//普通方法删除用户
	@RequestMapping("/deleteUser")
	public String deleteUser(String opId) {
		
		SysOp sysOp = userService.selectByPrimaryKey(Long.parseLong(opId));
		sysOp.setDataState(new Short("0"));
		
		userService.updateByPrimaryKeySelective(sysOp);
		
		return "user/userMgnt";
	}*/
	
	//ajax删除用户
	@RequestMapping("/deleteUser")
	@ResponseBody
	public ResultBean deleteUser(String opId) {
		
		SysOp sysOp = userService.selectByPrimaryKey(Long.parseLong(opId));
		sysOp.setDataState(new Short("0"));
		
		int i = userService.updateByPrimaryKeySelective(sysOp);
		ResultBean res = new ResultBean();
		
		if(i < 0) {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("删除用户失败！");
		}else {
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("删除用户成功！");
		}
		return res;
	}
	
	
	
	
	//头像上传
	@RequestMapping(value="fileUpload",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean fileUpload(MultipartFile uploadFile) {
		ResultBean res = new ResultBean();
		
		//设置图片保存的路径
		String path = "C:\\jfUpload\\";
		File filePath = new File(path);	//创建文件目录
		if(!filePath.exists()) {	//如果没有该路径，创建它和它下面的所有子路径
			filePath.mkdirs();
		}
		
		UUID uuid = UUID.randomUUID();
		String fileName = uuid + uploadFile.getOriginalFilename();	//获取上传的文件名
		
		File file = new File(path + fileName);	//创建文件
		try {
			uploadFile.transferTo(file);	//保存文件
			
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg(fileName);
		} catch (Exception e) {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("头像上传失败！");
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	
	/*//阿里云对象存储方式文件上传
	@RequestMapping(value="uploadFileByOss",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean uploadFileByOss(MultipartFile uploadFile)throws Exception{
		//endpoint以杭州为例，其它region请按实际情况填写
		String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
		//accessKey请登录https://ak-console.aliyun.com/#/查看
		String accessKeyId = "WuS8WuJxWJapjxbT";
		String accessKeySecret = "KCBTAFEfnIVaQodVp4tROu3L5evuXA";

		//创建OSSClient实例
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

		//使用访问OSS
		//String content = "Hello OSS";
		//client.putObject("0717oss", "hello", new ByteArrayInputStream(content.getBytes()));
		//上传文件流
		InputStream inputStream = new FileInputStream("C:\\testFileUpload\\jf.sql");
		client.putObject("0717oss", "jf1.sql", inputStream);
		
		client.shutdown();	//关闭client
		ResultBean rlt = new ResultBean();
		return rlt;
	}*/
}
