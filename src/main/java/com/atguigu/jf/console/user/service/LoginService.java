package com.atguigu.jf.console.user.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;

public interface LoginService {
	
	/**
     * @方法名: selectSysOpByNameAndPwd  
     * @功能描述: 根据用户名和密码查询用户信息
     * @param record
     * @return
     * @throws Exception
     * @作者  
     * @日期 Nov 25, 2016
     */
    public SysOp selectSysOpByNameAndPwd(SysOp record) throws Exception;
    
    
    
    /**
     * @方法名: selectSysFuncByOpId  
     * @功能描述:根据opId查询菜单
     * @param map
     * @return
     * @throws Exception
     * @作者  
     * @日期 Nov 26, 2016
     */
    List<SysFuncBean> selectSysFuncByOpId(Map<String,Object> map) throws Exception;
    
    
}
