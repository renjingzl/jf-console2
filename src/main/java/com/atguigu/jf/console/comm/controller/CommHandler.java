package com.atguigu.jf.console.comm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.jf.console.comm.bean.pojo.CodeValue;
import com.atguigu.jf.console.comm.service.CommService;

@Controller
@RequestMapping("comm")
public class CommHandler {
	
	@Autowired
	private CommService commService;
	
	
	//下拉框
	@RequestMapping("/getCodeValue")
	@ResponseBody
	public List<CodeValue> getCodeValue(Integer codeType) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeType", codeType);
		
		List<CodeValue> list = commService.selectCodeValue(map);
		return list;
	}
	
}
