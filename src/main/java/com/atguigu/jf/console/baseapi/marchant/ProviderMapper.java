package com.atguigu.jf.console.baseapi.marchant;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.marchant.bean.pojo.Provider;

public interface ProviderMapper {
    int deleteByPrimaryKey(Long providerId);

    int insert(Provider record);

    int insertSelective(Provider record);

    Provider selectByPrimaryKey(Long providerId);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);
    
    //根据条件查询供应商列表
    List<Provider> selectProviderList(Map<String, Object> map);
    
    //删除供应商
    int deleteProviderById(Map<String, Object> map);
    
    //供应商查看详情
    Provider selectProviderById(Map<String, Object> map);
}