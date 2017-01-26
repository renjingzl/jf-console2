package com.atguigu.jf.console.baseapi.marchant;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.marchant.bean.bo.Area;

public interface AreaMapper {
    int deleteByPrimaryKey(String areaId);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(String areaId);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
    
    List<Area> selectArea(Map<String, Object> map);	//三级联动查询地区信息
    
    Area selectAreaByAreaId(Map<String, Object> map);
}