package com.atguigu.jf.console.user.service.impl;

import java.util.List;
import java.util.Map;








import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.user.SysOpMapper;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private SysOpMapper sysOpMapper;

	
	
	@Override
	public List<SysOp> selectSysOpPageList(Map<String, Object> map)
			throws Exception {
		List<SysOp> sysOpPageList = sysOpMapper.selectSysOpPageList(map);
		
		for (SysOp sysOp : sysOpPageList) {
			Short opKind = sysOp.getOpKind();
			if(opKind != null) {
				if(opKind == 1) {
					sysOp.setOpType("超级管理员");
				}
				if(opKind == 2) {
					sysOp.setOpType("管理员");
				}
				if(opKind == 3) {
					sysOp.setOpType("普通用户");
				}
			}
		}
		return sysOpPageList;
	}

	
	@Override
	public Integer selectSysOpListCount(Map<String, Object> map)
			throws Exception {
		return sysOpMapper.selectSysOpListCount(map);
	}

	
	
	@Override
	public int insertSelective(SysOp record) {
		return sysOpMapper.insertSelective(record);
	}

	
	
	@Override
	public SysOp selectByPrimaryKey(Long opId) {
		return sysOpMapper.selectByPrimaryKey(opId);
	}

	
	@Override
	public int updateByPrimaryKeySelective(SysOp record) {
		return sysOpMapper.updateByPrimaryKeySelective(record);
	}

}
