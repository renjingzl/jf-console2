package com.atguigu.jf.console.marchant.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.jf.console.comm.bean.bo.ResultBean;
import com.atguigu.jf.console.marchant.bean.bo.Area;
import com.atguigu.jf.console.marchant.bean.pojo.Provider;
import com.atguigu.jf.console.marchant.service.ProviderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("provider")
public class ProviderHandler {

	@Autowired
	private ProviderService providerService;
	
	
	//查询供应商列表
	@RequestMapping("/getProviderList")
	@ResponseBody
	public PageInfo<Provider> getProviderList(Provider provider,Integer page,Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(provider.getProviderName() != "") {
			map.put("providerName", provider.getProviderName());
		}
		map.put("providerKind", provider.getProviderKind());
		
		PageHelper.startPage(page, limit);
		List<Provider> list = providerService.selectProviderList(map);
		
		PageInfo<Provider> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	
	
	//去添加或修改页面
	@RequestMapping("/toAddMarchantUI")
	public String toAddMarchantUI(@RequestParam(value="type") String type,
								  @RequestParam(value="providerId",required=false) String providerId,
								  Map<String, Object> map){
		map.put("type", type);
		map.put("providerId", providerId);
		
		if("modify".equals(type)) {
			Provider provider = providerService.selectByPrimaryKey(Long.parseLong(providerId));
			map.put("provider", provider);
		}
		return "marchant/addMarchant";
	}
	
	
	
	//添加供应商
	@RequestMapping("/addProvider")
	@ResponseBody
	public ResultBean addProvider(Provider provider){
		
		provider.setProviderKind(new Short("1"));
		int i = providerService.insertSelective(provider);
		
		ResultBean res = new ResultBean();
		if(i > 0){
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("保存成功！");
		}else {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("保存失败！");
		}
		return res;
	}
	
	
	
	
	//修改供应商
	@RequestMapping("/modifyProvider")
	@ResponseBody
	public ResultBean modifyProvider(Provider provider){
		
		int i = providerService.updateByPrimaryKeySelective(provider);
		
		ResultBean res = new ResultBean();
		if(i > 0){
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("修改成功！");
		}else {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("修改失败！");
		}
		return res;
	}
	
	
	
	//删除供应商
	@RequestMapping("/deleteProvider")
	@ResponseBody
	public ResultBean deleteProvider(String providerId){
		Map<String, Object> map = new HashMap<>();
		map.put("providerId", providerId);
		
		int i = providerService.deleteProviderById(map);
		
		ResultBean res = new ResultBean();
		if(i > 0){
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("删除成功！");
		}else {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("删除失败！");
		}
		return res;
	}
	
	
	
	//三级联动查询地区信息
	@RequestMapping("/getArea")
	@ResponseBody
	public List<Area> getArea(Integer areaLevel,Integer supAreaId) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("areaLevel", areaLevel);
		map.put("supAreaId", supAreaId);
		
		return providerService.selectArea(map);
	}
	

	
	
	/*//多文件上传(表单中上传文件的name属性必须为：接收参数数组时自己起的名字uploadFile，接收参数要用数组)
	@RequestMapping(value="/fileUpload",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean fileUpload(@RequestParam(value="uploadFile",required=false) MultipartFile[] uploadFile){
		ResultBean res = new ResultBean();
		
		String path = "C:\\jfUpload\\";
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		
		for (int i = 0; i < uploadFile.length; i++) {
			
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + uploadFile[i].getOriginalFilename();
			File filePath = new File(path + fileName);
			
			try {
				uploadFile[i].transferTo(filePath);
				
				res.setResult(ResultBean.RESULT_SUCCESS);
				res.setMsg(fileName);
				
			} catch (Exception e) {
				e.printStackTrace();
				res.setResult(ResultBean.RESULT_FAILED);
				res.setMsg("文件上传失败！");
			}
		}
		return res;
	}*/
	
	
	
	//上传logo
	@RequestMapping(value="/fileUpload1",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean fileUpload1(MultipartFile uploadFile1){
		ResultBean res = new ResultBean();
		
		String path = "C:\\jfUpload\\";
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		
		UUID uuid = UUID.randomUUID();
		String fileName = uuid + uploadFile1.getOriginalFilename();
		File filePath = new File(path + fileName);
			
		try {
			uploadFile1.transferTo(filePath);
				
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg(fileName);
				
		} catch (Exception e) {
			e.printStackTrace();
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("文件上传失败！");
		}
		return res;
	}
	//上传营业执照
	@RequestMapping(value="/fileUpload2",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean fileUpload2(MultipartFile uploadFile2){
		ResultBean res = new ResultBean();
		
		String path = "C:\\jfUpload\\";
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		
		UUID uuid = UUID.randomUUID();
		String fileName = uuid + uploadFile2.getOriginalFilename();
		File filePath = new File(path + fileName);
		
		try {
			uploadFile2.transferTo(filePath);
			
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg(fileName);
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("文件上传失败！");
		}
		return res;
	}
	//上传合同扫描件
	@RequestMapping(value="/fileUpload3",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean fileUpload3(MultipartFile uploadFile3){
		ResultBean res = new ResultBean();
		
		String path = "C:\\jfUpload\\";
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		
		UUID uuid = UUID.randomUUID();
		String fileName = uuid + uploadFile3.getOriginalFilename();
		File filePath = new File(path + fileName);
		
		try {
			uploadFile3.transferTo(filePath);
			
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg(fileName);
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("文件上传失败！");
		}
		return res;
	}
	
	
	
	//查看供应商详情
	@RequestMapping("/toShowMarchant")
	public String toShowMarchant(@RequestParam(value="providerId",required=false) String providerId,
								  Map<String, Object> map){
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("providerId", providerId);
		Provider provider = providerService.selectProviderById(map2);
		
		map.put("provider", provider);
		return "marchant/showMarchantMgnt";
	}
}
