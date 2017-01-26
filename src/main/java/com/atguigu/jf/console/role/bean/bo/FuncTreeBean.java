package com.atguigu.jf.console.role.bean.bo;

public class FuncTreeBean {
	private Long funcId;
	private String name;
	private Long supFuncId;
	private Boolean open;
	private Boolean checked;
	
	
	public Long getFuncId() {
		return funcId;
	}
	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSupFuncId() {
		return supFuncId;
	}
	public void setSupFuncId(Long supFuncId) {
		this.supFuncId = supFuncId;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}
