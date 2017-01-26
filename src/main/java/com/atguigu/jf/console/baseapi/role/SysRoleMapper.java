package com.atguigu.jf.console.baseapi.role;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.role.bean.pojo.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> selectSysRole(Map<String, Object> map);	//查询角色列表
}