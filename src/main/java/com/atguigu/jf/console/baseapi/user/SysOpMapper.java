package com.atguigu.jf.console.baseapi.user;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.pojo.SysOp;

public interface SysOpMapper {
    int deleteByPrimaryKey(Long opId);

    int insert(SysOp record);

    int insertSelective(SysOp record);

    SysOp selectByPrimaryKey(Long opId);

    int updateByPrimaryKeySelective(SysOp record);

    int updateByPrimaryKey(SysOp record);
    /**
     * @方法名: selectSysOpByNameAndPwd  
     * @功能描述: 根据用户名和密码查询用户信息
     * @param record
     * @return
     * @throws Exception
     * @作者  
     * @日期 Nov 25, 2016
     */
    SysOp selectSysOpByNameAndPwd(SysOp record) throws Exception;
    
    
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
}