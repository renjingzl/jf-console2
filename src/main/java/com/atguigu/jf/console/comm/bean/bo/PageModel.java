package com.atguigu.jf.console.comm.bean.bo;

import java.util.List;

public class PageModel<T> {
	
	private List<T> pagelist;
	private int pageNo;
	private int totalCount;
	private int pageSize;
	
	
	
	public List<T> getPagelist() {
		return pagelist;
	}
	public void setPagelist(List<T> pagelist) {
		this.pagelist = pagelist;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
