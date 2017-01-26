package com.atguigu.jf.console.system.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.jf.console.comm.bean.bo.ResultBean;
import com.atguigu.jf.console.system.bean.pojo.IcMallCat;
import com.atguigu.jf.console.system.service.IcMallCatService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("mallCat")
public class IcMallCatHandler {
	
	@Autowired
	private IcMallCatService icMallCatService;
	
	
	
	//查询类目列表
	@RequestMapping("/getMallCat")
	@ResponseBody
	public PageInfo<IcMallCat> getMallCat(Integer page,Integer limit) {
		
		PageHelper.startPage(page, limit);
		List<IcMallCat> list = icMallCatService.selectAllMallCat();
		
		PageInfo<IcMallCat> pageInfo = new PageInfo<>(list);
		
		return pageInfo;
	}
	
	
	
	//去添加或修改页面
	@RequestMapping("/toAddOrModifyUI")
	public String toAddOrModifyUI(@RequestParam("type") String type,
								  @RequestParam(value="mallCatId",required=false) String mallCatId,
								  Map<String, Object> map){
		
		if ("modify".equals(type)) {
			IcMallCat mallCat = icMallCatService.selectByPrimaryKey(Long.parseLong(mallCatId));
			map.put("IcMallCat", mallCat);
		}
		
		map.put("type", type);
		map.put("mallCatId", mallCatId);
		return "system/addMallCat";
	}
	
	
	
	//添加类目
	@RequestMapping("/addMallCat")
	@ResponseBody
	public ResultBean addMallCat(IcMallCat icMallCat) {
		ResultBean res = new ResultBean();
		
		icMallCatService.insertSelective(icMallCat);
		
		icMallCat.setMallCatCode(icMallCat.getMallCatId().toString());	//使用生成的主键，给mallCatCode赋值
		int i = icMallCatService.updateByPrimaryKeySelective(icMallCat);	//更新表中的mallCatCode值
		
		if(i > 0) {
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("保存成功！");
		}else {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("保存失败！");
		}
		return res;
	}
	
	
	
	
	//修改类目
	@RequestMapping("/modifyMallCat")
	@ResponseBody
	public ResultBean modifyMallCat(IcMallCat icMallCat) {
		ResultBean res = new ResultBean();
		
		int i = icMallCatService.updateByPrimaryKeySelective(icMallCat);
		if(i > 0) {
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("修改成功！");
		}else {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("修改失败！");
		}
		return res;
	}
	
	
	
	//删除类目
	@RequestMapping("/deleteMallCat")
	@ResponseBody
	public ResultBean deleteMallCat(String mallCatId) {
		ResultBean res = new ResultBean();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mallCatId", mallCatId);
		
		int i = icMallCatService.deleteMallCatById(map);
		if(i > 0) {
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("删除成功！");
		}else {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("删除失败！");
		}
		return res;
	}
	
	
	
	//文件上传
	@RequestMapping("/fileUpload")
	@ResponseBody
	public ResultBean fileUpload(MultipartFile fileUpload){
		ResultBean res = new ResultBean();
		
		String path="C:\\jfUpload\\";
		File file = new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		UUID uuid = UUID.randomUUID();
		String fileName = uuid + fileUpload.getOriginalFilename();
		
		File filePath = new File(path + fileName);
		try {
			fileUpload.transferTo(filePath);
			
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg(fileName);
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("文件上传失败");
		}
		return res;
	}
}
