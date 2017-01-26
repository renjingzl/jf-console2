package com.atguigu.jf.console.pageManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.marchant.AreaMapper;
import com.atguigu.jf.console.baseapi.pageManagement.AdvMapper;
import com.atguigu.jf.console.marchant.bean.bo.Area;
import com.atguigu.jf.console.pageManagement.bean.pojo.Adv;
import com.atguigu.jf.console.pageManagement.service.AdvService;

@Service
public class AdvServiceImpl implements AdvService {
	
	@Autowired
	private AdvMapper advMapper;
	@Autowired
	private AreaMapper areaMapper;
	
	

	@Override
	public int insertSelective(Adv record) {
		return advMapper.insertSelective(record);
	}

	@Override
	public Adv selectByPrimaryKey(Long advId) {
		return advMapper.selectByPrimaryKey(advId);
	}

	@Override
	public int updateByPrimaryKeySelective(Adv record) {
		return advMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Adv> selectAllAdv(Map<String, Object> map) {
		List<Adv> list = advMapper.selectAllAdv(map);
		
		for (Adv adv : list) {
			//处理广告位区域
			if(adv.getAdvPos()==1) {
				adv.setAdvPosName("首页");
			}else if(adv.getAdvPos()==2){
				adv.setAdvPosName("特价区");
			}
			
			//处理广告位状态
			if (adv.getAdvState()==1) {
				adv.setAdvStateName("待发布");
			}
			if (adv.getAdvState()==2) {
				adv.setAdvStateName("已上架");
			}
			if (adv.getAdvState()==3) {
				adv.setAdvStateName("已下架");
			}
			
			//处理地区
			Long areaId = adv.getAdvAreaId();
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("areaId", areaId.toString());
			Area area = areaMapper.selectAreaByAreaId(map2);
			
			adv.setAdvAreaName(area.getAreaName());
		}
		return list;
	}

	@Override
	public int deleteAdv(Map<String, Object> map) {
		return advMapper.deleteAdv(map);
	}

	@Override
	public int updateState(Map<String, Object> map) {
		return advMapper.updateState(map);
	}

	@Override
	public int updateAdvOrder(Map<String, Object> map) {
		return advMapper.updateAdvOrder(map);
	}

	@Override
	public List<Adv> selectSmailAdvOrderList(Map<String, Object> map) {
		return advMapper.selectSmailAdvOrderList(map);
	}

	@Override
	public List<Adv> selectBigAdvOrderList(Map<String, Object> map) {
		return advMapper.selectBigAdvOrderList(map);
	}

	@Override
	public Long maxAdvOrder() {
		return advMapper.maxAdvOrder();
	}

	@Override
	public Long minAdvOrder() {
		return advMapper.minAdvOrder();
	}
}
