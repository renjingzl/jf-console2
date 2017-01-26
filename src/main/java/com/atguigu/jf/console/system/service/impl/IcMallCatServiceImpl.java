package com.atguigu.jf.console.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.system.IcMallCatMapper;
import com.atguigu.jf.console.system.bean.pojo.IcMallCat;
import com.atguigu.jf.console.system.service.IcMallCatService;


@Service
public class IcMallCatServiceImpl implements IcMallCatService {
	
	
	@Autowired
	private IcMallCatMapper icMallCatMapper;


	@Override
	public int insertSelective(IcMallCat record) {
		return icMallCatMapper.insertSelective(record);
	}

	@Override
	public IcMallCat selectByPrimaryKey(Long mallCatId) {
		return icMallCatMapper.selectByPrimaryKey(mallCatId);
	}

	@Override
	public int updateByPrimaryKeySelective(IcMallCat record) {
		return icMallCatMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<IcMallCat> selectAllMallCat() {
		return icMallCatMapper.selectAllMallCat();
	}

	@Override
	public int deleteMallCatById(Map<String, Object> map) {
		return icMallCatMapper.deleteMallCatById(map);
	}


}
