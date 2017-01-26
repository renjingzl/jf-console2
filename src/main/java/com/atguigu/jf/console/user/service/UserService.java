package com.atguigu.jf.console.user.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.pojo.SysOp;

public interface UserService {
	
	/**
     * @方法名: selectSysOpPageList  
     * @功能描述: 查询分页后的用户list 
     * @param map
     * @return
     * @throws Exception
     * @作者  Rj
     * @日期 2016年11月27日
     */
    List<SysOp> selectSysOpPageList(Map<String,Object> map) throws Exception;
    
    
    
    /**
     * @方法名: selectSysOpListCount  
     * @功能描述: 查询用户的总记录数 
     * @param map
     * @return
     * @throws Exception
     * @作者  Rj
     * @日期 2016年11月27日
     */
    Integer selectSysOpListCount(Map<String,Object> map) throws Exception;
    
    
    /**
     * @方法名: insertSelective  
     * @功能描述: 添加用户  
     * @param record
     * @作者  Rj
     * @日期 2016年11月28日
     */
    int insertSelective(SysOp record);
    
    
    /**
     * @方法名: selectByPrimaryKey  
     * @功能描述: 修改用户回显  
     * @param opId
     * @return
     * @作者  Rj
     * @日期 2016年11月28日
     */
    SysOp selectByPrimaryKey(Long opId);
    
    
    /**
     * @方法名: updateByPrimaryKeySelective  
     * @功能描述: 修改用户
     * @param record
     * @return
     * @作者  Rj
     * @日期 2016年11月28日
     */
    int updateByPrimaryKeySelective(SysOp record);
    
}
