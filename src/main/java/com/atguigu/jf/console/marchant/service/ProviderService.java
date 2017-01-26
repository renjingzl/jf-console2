package com.atguigu.jf.console.marchant.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.marchant.bean.bo.Area;
import com.atguigu.jf.console.marchant.bean.pojo.Provider;

public interface ProviderService {
    
    int insertSelective(Provider record);

    Provider selectByPrimaryKey(Long providerId);

    int updateByPrimaryKeySelective(Provider record);
    
    List<Provider> selectProviderList(Map<String, Object> map);	//根据条件查询供应商列表
    
    int deleteProviderById(Map<String, Object> map);	//删除供应商
    
    List<Area> selectArea(Map<String, Object> map);	//三级联动查询地区信息
    
  //供应商查看详情
    Provider selectProviderById(Map<String, Object> map);

}
