package com.javaweb.web.eo.user;

public class UserSearch {
	
	private String userName;
	
	private String personName;
	
	private Long currentPage = 1L;//默认当前第1页
	
	private Long pageSize = 10L;//默认每页显示10条 

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	
}
