package com.atguigu.jf.console.baseapi.system;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.system.bean.pojo.IcMallCat;

public interface IcMallCatMapper {
    int deleteByPrimaryKey(Long mallCatId);

    int insert(IcMallCat record);

    int insertSelective(IcMallCat record);

    IcMallCat selectByPrimaryKey(Long mallCatId);

    int updateByPrimaryKeySelective(IcMallCat record);

    int updateByPrimaryKey(IcMallCat record);
    
    List<IcMallCat> selectAllMallCat();	//查询类目列表
    
    int deleteMallCatById(Map<String, Object> map);	//删除类目
}