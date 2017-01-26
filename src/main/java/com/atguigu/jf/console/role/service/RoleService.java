package com.atguigu.jf.console.role.service;

import java.util.List;
import java.util.Map;


import com.atguigu.jf.console.role.bean.bo.SysRoleFunc;
import com.atguigu.jf.console.role.bean.pojo.SysRole;
import com.atguigu.jf.console.user.bean.bo.SysFuncBean;

public interface RoleService {
	
	//查询角色列表
	List<SysRole> selectSysRole(Map<String, Object> map);	
	
	
	//根据角色Id查询功能列表
    List<Long> selectFuncByRoleId(Map<String,Object> map) throws Exception;
    
    
    //查询所有功能
    List<SysFuncBean> selectAllSysFuncBean();
    
    
    //删除所有指定roleId的功能，即：清空树
    void deleteFuncByRoleId(Map<String, Object> map);
    
    
    //新增功能，用来更新树
    public int inertBatchSysRoleFunc(List<SysRoleFunc> list);
   
}
