package com.atguigu.jf.console.pageManagement.controller;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.jf.console.baseapi.marchant.AreaMapper;
import com.atguigu.jf.console.comm.bean.bo.ResultBean;
import com.atguigu.jf.console.marchant.bean.bo.Area;
import com.atguigu.jf.console.pageManagement.bean.pojo.Adv;
import com.atguigu.jf.console.pageManagement.service.AdvService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("adv")
public class AdvHandler {
	
	@Autowired
	private AdvService advService;
	@Autowired
	private AreaMapper areaMapper;
	
	
	//根据条件查询广告位列表
	@RequestMapping("/selectAllAdv")
	@ResponseBody
	public String selectAllAdv(Adv adv,Integer page,Integer limit) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("advState", adv.getAdvState());
		map.put("advPos", adv.getAdvPos());
		map.put("advAreaId", adv.getAdvAreaId());
		if(adv.getAdvName() != ""){
			map.put("advName", adv.getAdvName());
		}
		
		Long maxAdvOrder = advService.maxAdvOrder();	//查出表中的最大Order值
		Long minAdvOrder = advService.minAdvOrder();	//查出表中的最小Order值
		
		PageHelper.startPage(page, limit);
		List<Adv> list = advService.selectAllAdv(map);
		
		for (Adv adv2 : list) {
			//为了页面上判断当前advOrder是否是“最大/最小”值，来决定要不要显示“向上/向下”超链接
			adv2.setMaxAdvOrder(maxAdvOrder);	
			adv2.setMinAdvOrder(minAdvOrder);
		}
		
		PageInfo<Adv> pageInfo = new PageInfo<>(list);
		
		JSON.DEFFAULT_DATE_FORMAT = "HH:mm:ss";
		String result = JSON.toJSONString(pageInfo,
										  SerializerFeature.WriteDateUseDateFormat,
										  SerializerFeature.WriteMapNullValue, 
										  SerializerFeature.WriteNullNumberAsZero,
										  SerializerFeature.WriteNullStringAsEmpty);
		return result;
	}
	
	
	
	//去添加或修改页面
	@RequestMapping("/toAddOrModifyUI")
	public String toAddOrModifyUI(@RequestParam("type") String type,
								  @RequestParam(value="advId",required=false) String advId,
								  Map<String, Object> map){
		map.put("type", type);
		map.put("advId", advId);
		
		if("modify".equals(type)) {
			Adv adv = advService.selectByPrimaryKey(Long.parseLong(advId));
			map.put("adv", adv);
		}
		return "pageManagement/addAdv";
	}
	
	
	
	//地区下拉框查询
	@RequestMapping("selectAllArea")
	@ResponseBody
	public List<Area> selectAllArea(){
		Map<String, Object> map = new HashMap<>();
		return areaMapper.selectArea(map);
	}

	
	
	//添加(点发布时)
	@RequestMapping("/fabuAdv")
	@ResponseBody
	public ResultBean fabuAdv(Adv adv){
		ResultBean res = new ResultBean();
		
		adv.setAdvState(new Short("2"));	//将广告位状态设为：2-已上架
		
		advService.insertSelective(adv);
		adv.setAdvOrder(adv.getAdvId());	//用生成的主键给advOrder赋值
		int i = advService.updateByPrimaryKeySelective(adv);
		
		if(i > 0) {
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("发布成功！");
		}else {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("发布失败！");
		}
		return res;
	}
	
	//添加(点保存时)
	@RequestMapping("/saveAdv")
	@ResponseBody
	public ResultBean saveAdv(Adv adv){
		ResultBean res = new ResultBean();
		
		adv.setAdvState(new Short("1"));	//将广告位状态设为：1-待发布
		advService.insertSelective(adv);
		adv.setAdvOrder(adv.getAdvId());	//用生成的主键给advOrder赋值
		int i = advService.updateByPrimaryKeySelective(adv);
		
		if(i > 0) {
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("保存成功！");
		}else {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("保存失败！");
		}
		return res;
	}
	
	
	
	//文件上传
	@RequestMapping(value="/fileUpload",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean fileUpload1(MultipartFile fileUpload){
		ResultBean res = new ResultBean();
			
		String path = "C:\\jfUpload\\";
		File file = new File(path);
		if(!file.exists()){
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
			res.setMsg("文件上传失败！");
		}
		return res;
	}
	
	
	
	//修改(点保存时)
	@RequestMapping("/modifySaveAdv")
	@ResponseBody
	public ResultBean modifySaveAdv(Adv adv){
		ResultBean res = new ResultBean();
		
		adv.setAdvState(new Short("1"));	//将广告位状态设为：1-待发布
		int i = advService.updateByPrimaryKeySelective(adv);
		if(i > 0) {
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("修改成功！");
		}else {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("修改失败！");
		}
		return res;
	}
	
	
	//修改(点发布时)
	@RequestMapping("/modifyFabuAdv")
	@ResponseBody
	public ResultBean modifyFabuAdv(Adv adv){
		ResultBean res = new ResultBean();
		
		adv.setAdvState(new Short("2"));	//将广告位状态设为：2-已上架
		int i = advService.updateByPrimaryKeySelective(adv);
		if(i > 0) {
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("修改成功！");
		}else {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("修改失败！");
		}
		return res;
	}
	
	
	
	
	//删除广告位
	@RequestMapping("/deleteAdv")
	@ResponseBody
	public ResultBean deleteAdv(String advId){
		ResultBean res = new ResultBean();
		
		Map<String, Object> map = new HashMap<>();
		map.put("advId", advId);
		
		int i = advService.deleteAdv(map);
		if(i > 0) {
			res.setResult(ResultBean.RESULT_SUCCESS);
			res.setMsg("删除成功！");
		}else {
			res.setResult(ResultBean.RESULT_FAILED);
			res.setMsg("删除失败！");
		}
		return res;
	}
	
	
	
	//发布超连接
	@RequestMapping("/updateState")
	public String updateState(String advId){
		
		Map<String, Object> map = new HashMap<>();
		map.put("advId", advId);
		advService.updateState(map);
		return "redirect:/page/pageManagement/advMgnt.jsp";
	}
	
	
	
	
	//调整顺序
	@RequestMapping("/updateAdvOrder")
	public String updateAdvOrder(String type,String advOrder,String advId){
		
		Adv adv = advService.selectByPrimaryKey(Long.parseLong(advId));	//当前对象
		Map<String, Object> map = new HashMap<>();
		map.put("advOrder", advOrder);
		
		if("up".equals(type)){
			List<Adv> advList = advService.selectSmailAdvOrderList(map);//查出advOrder比当前对象小的adv集合
			
			//取出advOrder比当前对象小的adv集合中的最后一个对象，即列表中当前对象的上一个对象
			Adv lastAdv = advList.get(advList.size() - 1);	
			
			//将当前对象的advOrder与它上一个对象的advOrder互换
			Long tempLast = lastAdv.getAdvOrder();
			Long tempThis = adv.getAdvOrder();
			
			adv.setAdvOrder(tempLast);
			lastAdv.setAdvOrder(tempThis);
			
			//更新当前对象和它上一个对象
			advService.updateByPrimaryKeySelective(adv);
			advService.updateByPrimaryKeySelective(lastAdv);
		}else {
			List<Adv> advList = advService.selectBigAdvOrderList(map);//查出advOrder比当前对象小的adv集合
			
			//取出advOrder比当前对象小的adv集合中的第一个对象，即列表中当前对象的下一个对象
			Adv firstAdv = advList.get(0);	
			
			//将当前对象的advOrder与它下一个对象的advOrder互换
			Long tempFirst = firstAdv.getAdvOrder();
			Long tempThis = adv.getAdvOrder();
			
			adv.setAdvOrder(tempFirst);
			firstAdv.setAdvOrder(tempThis);
			
			//更新当前对象和它上一个对象
			advService.updateByPrimaryKeySelective(adv);
			advService.updateByPrimaryKeySelective(firstAdv);
		}
		return "redirect:/page/pageManagement/advMgnt.jsp";
	}
	
	
}
