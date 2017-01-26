package com.atguigu.jf.console.baseapi.user;

import java.util.List;
import java.util.Map;


import com.atguigu.jf.console.role.bean.bo.SysRoleFunc;
import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.pojo.SysFunc;

public interface SysFuncMapper {
    int deleteByPrimaryKey(Long funcId);

    int insert(SysFunc record);

    int insertSelective(SysFunc record);

    SysFunc selectByPrimaryKey(Long funcId);

    int updateByPrimaryKeySelective(SysFunc record);

    int updateByPrimaryKey(SysFunc record);
    
    
    //根据opId查询功能菜单
    List<SysFuncBean> selectSysFuncByOpId(Map<String,Object> map) throws Exception;
    
    
    //根据角色Id查询功能Id
    List<Long> selectFuncByRoleId(Map<String,Object> map) throws Exception;
    
    
    //查询所有功能
    List<SysFuncBean> selectAllSysFuncBean();
    
    
    //删除所有指定roleId的功能，即：清空树
    void deleteFuncByRoleId(Map<String, Object> map);
    
    
    //新增功能
    public int inertBatchSysRoleFunc(List<SysRoleFunc> list); 
}