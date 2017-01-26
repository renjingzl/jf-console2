package com.atguigu.jf.console.marchant.bean.bo;


public class Area {
    private String areaId;

    private String areaCode;

    private String areaName;

    private String areaDesc;

    private Integer areaLevel;

    private String supAreaTree;

    private String supAreaId;

   
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc == null ? null : areaDesc.trim();
    }

    public Integer getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getSupAreaTree() {
        return supAreaTree;
    }

    public void setSupAreaTree(String supAreaTree) {
        this.supAreaTree = supAreaTree == null ? null : supAreaTree.trim();
    }

    public String getSupAreaId() {
        return supAreaId;
    }

    public void setSupAreaId(String supAreaId) {
        this.supAreaId = supAreaId == null ? null : supAreaId.trim();
    }
}