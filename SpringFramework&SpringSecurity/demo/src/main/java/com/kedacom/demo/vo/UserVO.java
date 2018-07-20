/**
 * 
 */
package com.kedacom.demo.vo;

/**
 * 用户类前台对象
 * @author lihongguang
 */
public class UserVO {

	private String id;
	private String username;
	private String role;

	public UserVO(String id, String username, String role) {
		this.id = id;
		this.username = username;
		this.role = role;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", username=" + username + ", role=" + role + "]";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
