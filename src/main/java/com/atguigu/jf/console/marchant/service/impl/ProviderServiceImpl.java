package com.atguigu.jf.console.marchant.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.marchant.AreaMapper;
import com.atguigu.jf.console.baseapi.marchant.ProviderMapper;
import com.atguigu.jf.console.marchant.bean.bo.Area;
import com.atguigu.jf.console.marchant.bean.pojo.Provider;
import com.atguigu.jf.console.marchant.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {
	
	@Autowired
	private ProviderMapper providerMapper;
	@Autowired
	private AreaMapper areaMapper;
	
	
	@Override
	public List<Provider> selectProviderList(Map<String, Object> map) {
		List<Provider> list = providerMapper.selectProviderList(map);
		
		for (Provider provider : list) {
			
			if(provider.getProviderKind() == 1) {
				provider.setProviderType("电信");
			}
			if(provider.getProviderKind() == 2) {
				provider.setProviderType("航空公司");
			}
			if(provider.getProviderKind() == 3) {
				provider.setProviderType("教育");
			}
			if(provider.getProviderKind() == 4) {
				provider.setProviderType("旅行社");
			}
		}
		return list;
	}



	@Override
	public int insertSelective(Provider record) {
		return providerMapper.insertSelective(record);
	}



	@Override
	public Provider selectByPrimaryKey(Long providerId) {
		return providerMapper.selectByPrimaryKey(providerId);
	}



	@Override
	public int updateByPrimaryKeySelective(Provider record) {
		return providerMapper.updateByPrimaryKeySelective(record);
	}



	@Override
	public int deleteProviderById(Map<String, Object> map) {
		return providerMapper.deleteProviderById(map);
	}



	@Override
	public List<Area> selectArea(Map<String, Object> map) {
		return areaMapper.selectArea(map);
	}



	@Override
	public Provider selectProviderById(Map<String, Object> map) {
		return providerMapper.selectProviderById(map);
	}




}
