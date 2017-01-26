package com.atguigu.jf.console.role.service.impl;

import java.util.List;





import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.role.SysRoleMapper;
import com.atguigu.jf.console.baseapi.user.SysFuncMapper;
import com.atguigu.jf.console.role.bean.bo.SysRoleFunc;
import com.atguigu.jf.console.role.bean.pojo.SysRole;
import com.atguigu.jf.console.role.service.RoleService;
import com.atguigu.jf.console.user.bean.bo.SysFuncBean;

@Service
public class RoleServiceImpl implements RoleService {
	
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysFuncMapper sysFuncMapper;
	
	

	@Override
	public List<SysRole> selectSysRole(Map<String, Object> map) {
		return sysRoleMapper.selectSysRole(map);
	}


	@Override
	public List<Long> selectFuncByRoleId(Map<String, Object> map)
			throws Exception {
		return sysFuncMapper.selectFuncByRoleId(map);
	}


	@Override
	public List<SysFuncBean> selectAllSysFuncBean() {
		return sysFuncMapper.selectAllSysFuncBean();
	}


	@Override
	public void deleteFuncByRoleId(Map<String, Object> map) {
		sysFuncMapper.deleteFuncByRoleId(map);
	}


	@Override
	public int inertBatchSysRoleFunc(List<SysRoleFunc> list) {
		return sysFuncMapper.inertBatchSysRoleFunc(list);
	}
}
