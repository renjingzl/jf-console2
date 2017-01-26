package com.atguigu.jf.console.comm.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.comm.bean.pojo.CodeValue;

public interface CommService {
	
	/**
	 * @方法名: selectCodeValue  
	 * @功能描述: 根据codeType查询下拉框的内容
	 * @param map
	 * @return
	 * @作者  Rj
	 * @日期 2016年11月26日
	 */
	List<CodeValue> selectCodeValue(Map<String, Object> map);
}
