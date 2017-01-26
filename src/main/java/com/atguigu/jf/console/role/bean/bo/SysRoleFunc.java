package com.atguigu.jf.console.role.bean.bo;

import java.util.Date;

public class SysRoleFunc {
	private Long roleFuncId;
	
	private Long funcId;
	
	private Long roleId;
	
	private Long creator;
	
	private String notes;
	
	private Long modifier;
	
	private Date modifyDate;

	public Long getRoleFuncId() {
		return roleFuncId;
	}

	public void setRoleFuncId(Long roleFuncId) {
		this.roleFuncId = roleFuncId;
	}

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getModifier() {
		return modifier;
	}

	public void setModifier(Long modifier) {
		this.modifier = modifier;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
