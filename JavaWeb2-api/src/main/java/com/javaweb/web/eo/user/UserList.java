package com.javaweb.web.eo.user;

public class UserList {
	
	private String userName;//用户名
	
	private String personName;//用户姓名
	
	private String createStartDate;//用户创建的开始日期
	
	private String createEndDate;//用户创建的结束日期
	
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

	public String getCreateStartDate() {
		return createStartDate;
	}

	public void setCreateStartDate(String createStartDate) {
		this.createStartDate = createStartDate;
	}

	public String getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(String createEndDate) {
		this.createEndDate = createEndDate;
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
