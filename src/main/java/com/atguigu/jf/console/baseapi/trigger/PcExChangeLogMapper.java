package com.atguigu.jf.console.baseapi.trigger;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.trigger.bean.bo.ExchangeLogBean;

public interface PcExChangeLogMapper {

	/**
	 * @方法名: selectPcExchangeLog  
	 * @功能描述: 查询积分导入流水
	 * @param map
	 * @return
	 * @作者  
	 * @日期 Dec 3, 2016
	 */
	List<ExchangeLogBean> selectPcExchangeLog(Map<String,Object> map);
}
