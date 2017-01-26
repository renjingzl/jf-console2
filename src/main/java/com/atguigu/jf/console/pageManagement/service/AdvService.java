package com.atguigu.jf.console.pageManagement.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.pageManagement.bean.pojo.Adv;

public interface AdvService {
	
    int insertSelective(Adv record);

    Adv selectByPrimaryKey(Long advId);

    int updateByPrimaryKeySelective(Adv record);

    List<Adv> selectAllAdv(Map<String, Object> map);	//根据查询条件查询广告列表
    
    int deleteAdv(Map<String, Object> map);	//删除广告位

    int updateState(Map<String, Object> map);	//发布广告位
    
    int updateAdvOrder(Map<String, Object> map);	//更改广告位顺序
    
    List<Adv> selectSmailAdvOrderList(Map<String, Object> map);	//向上功能时，查找小于当前advOrder的AdvList
    
    List<Adv> selectBigAdvOrderList(Map<String, Object> map);	//向下功能时，查找大于当前advOrder的AdvList
    
    Long maxAdvOrder();	//查最大advOrder值
    
    Long minAdvOrder();	//查最小advOrder值
}
