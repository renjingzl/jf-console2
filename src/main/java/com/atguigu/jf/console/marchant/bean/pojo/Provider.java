package com.atguigu.jf.console.marchant.bean.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Provider {
    private Long providerId;

    private String providerName;

    private String providerShortName;

    private String providerCode;

    private String providerPicUrl;

    private String providerDesc;

    private BigDecimal providerPointRatio;

    private BigDecimal providerPointFee;

    private Short balanceTerm;

    private Short balanceType;

    private String bproviderLicense;

    private String bproviderContract;

    private Long provId;

    private Long cityId;

    private Long regionId;

    private String providerAddress;

    private String custsrvPhone;

    private String contactName;

    private String contactPhone;

    private String contactPhone2;

    private String contactEmail;

    private String providerRemark;

    private String providerPhone;

    private String providerWeburl;

    private Short providerStatus;

    private Short dataState;

    private Long creator;

    private Date createTime;

    private Long modifyer;

    private Date modifyTime;
    
    private Short providerKind;
    
    private String providerType;
    
    

    public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public Short getProviderKind() {
		return providerKind;
	}

	public void setProviderKind(Short providerKind) {
		this.providerKind = providerKind;
	}

	public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName == null ? null : providerName.trim();
    }

    public String getProviderShortName() {
        return providerShortName;
    }

    public void setProviderShortName(String providerShortName) {
        this.providerShortName = providerShortName == null ? null : providerShortName.trim();
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode == null ? null : providerCode.trim();
    }

    public String getProviderPicUrl() {
        return providerPicUrl;
    }

    public void setProviderPicUrl(String providerPicUrl) {
        this.providerPicUrl = providerPicUrl == null ? null : providerPicUrl.trim();
    }

    public String getProviderDesc() {
        return providerDesc;
    }

    public void setProviderDesc(String providerDesc) {
        this.providerDesc = providerDesc == null ? null : providerDesc.trim();
    }

    public BigDecimal getProviderPointRatio() {
        return providerPointRatio;
    }

    public void setProviderPointRatio(BigDecimal providerPointRatio) {
        this.providerPointRatio = providerPointRatio;
    }

    public BigDecimal getProviderPointFee() {
        return providerPointFee;
    }

    public void setProviderPointFee(BigDecimal providerPointFee) {
        this.providerPointFee = providerPointFee;
    }

    public Short getBalanceTerm() {
        return balanceTerm;
    }

    public void setBalanceTerm(Short balanceTerm) {
        this.balanceTerm = balanceTerm;
    }

    public Short getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(Short balanceType) {
        this.balanceType = balanceType;
    }

    public String getBproviderLicense() {
        return bproviderLicense;
    }

    public void setBproviderLicense(String bproviderLicense) {
        this.bproviderLicense = bproviderLicense == null ? null : bproviderLicense.trim();
    }

    public String getBproviderContract() {
        return bproviderContract;
    }

    public void setBproviderContract(String bproviderContract) {
        this.bproviderContract = bproviderContract == null ? null : bproviderContract.trim();
    }

    public Long getProvId() {
        return provId;
    }

    public void setProvId(Long provId) {
        this.provId = provId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress == null ? null : providerAddress.trim();
    }

    public String getCustsrvPhone() {
        return custsrvPhone;
    }

    public void setCustsrvPhone(String custsrvPhone) {
        this.custsrvPhone = custsrvPhone == null ? null : custsrvPhone.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getContactPhone2() {
        return contactPhone2;
    }

    public void setContactPhone2(String contactPhone2) {
        this.contactPhone2 = contactPhone2 == null ? null : contactPhone2.trim();
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public String getProviderRemark() {
        return providerRemark;
    }

    public void setProviderRemark(String providerRemark) {
        this.providerRemark = providerRemark == null ? null : providerRemark.trim();
    }

    public String getProviderPhone() {
        return providerPhone;
    }

    public void setProviderPhone(String providerPhone) {
        this.providerPhone = providerPhone == null ? null : providerPhone.trim();
    }

    public String getProviderWeburl() {
        return providerWeburl;
    }

    public void setProviderWeburl(String providerWeburl) {
        this.providerWeburl = providerWeburl == null ? null : providerWeburl.trim();
    }

    public Short getProviderStatus() {
        return providerStatus;
    }

    public void setProviderStatus(Short providerStatus) {
        this.providerStatus = providerStatus;
    }

    public Short getDataState() {
        return dataState;
    }

    public void setDataState(Short dataState) {
        this.dataState = dataState;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyer() {
        return modifyer;
    }

    public void setModifyer(Long modifyer) {
        this.modifyer = modifyer;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}