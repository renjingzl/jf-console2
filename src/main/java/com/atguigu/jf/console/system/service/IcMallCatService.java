package com.atguigu.jf.console.system.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.system.bean.pojo.IcMallCat;

public interface IcMallCatService {
	int deleteMallCatById(Map<String, Object> map);	//删除类目

    int insertSelective(IcMallCat record);

    IcMallCat selectByPrimaryKey(Long mallCatId);

    int updateByPrimaryKeySelective(IcMallCat record);

	List<IcMallCat> selectAllMallCat();	//查询类目列表
}
