package com.employee.response;

import java.util.List;

public class PaginationResponse {
	private List<?>list;
	private Integer pagenumber;
	private Integer pagesize;
	private Integer currentpagedata;
	private Integer totalpage;
	private Long totaldata;
	private Boolean lastpage;
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public Integer getPagenumber() {
		return pagenumber;
	}
	public void setPagenumber(Integer pagenumber) {
		this.pagenumber = pagenumber;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getCurrentpagedata() {
		return currentpagedata;
	}
	public void setCurrentpagedata(Integer currentpagedata) {
		this.currentpagedata = currentpagedata;
	}
	public Integer getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(Integer totalpage) {
		this.totalpage = totalpage;
	}
	public Long getTotaldata() {
		return totaldata;
	}
	public void setTotaldata(Long totaldata) {
		this.totaldata = totaldata;
	}
	public Boolean getLastpage() {
		return lastpage;
	}
	public void setLastpage(Boolean lastpage) {
		this.lastpage = lastpage;
	}
	public PaginationResponse(List<?> list, Integer pagenumber, Integer pagesize, Integer currentpagedata,
			Integer totalpage, Long totaldata, Boolean lastpage) {
		super();
		this.list = list;
		this.pagenumber = pagenumber;
		this.pagesize = pagesize;
		this.currentpagedata = currentpagedata;
		this.totalpage = totalpage;
		this.totaldata = totaldata;
		this.lastpage = lastpage;
	}
	public PaginationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
