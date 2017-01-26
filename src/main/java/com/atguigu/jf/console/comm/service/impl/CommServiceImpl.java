package com.atguigu.jf.console.comm.service.impl;

import java.util.List;
import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.comm.CodeValueMapper;
import com.atguigu.jf.console.comm.bean.pojo.CodeValue;
import com.atguigu.jf.console.comm.service.CommService;

@Service
public class CommServiceImpl implements CommService {

	@Autowired
	private CodeValueMapper codeValueMapper;
	
	
	@Override
	public List<CodeValue> selectCodeValue(Map<String, Object> map) {
		return codeValueMapper.selectCodeValue(map);
	}

}
