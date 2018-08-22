/**
 * 
 */
package com.kedacom.demo.vo;

/**
 * 用户操作消息
 * @author lihongguang
 */
public class UserOperationVO {

	private Integer value;
	private String content;

	public UserOperationVO(Integer value, String content) {
		super();
		this.value = value;
		this.content = content;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserOperationVO [value=" + value + ", content=" + content + "]";
	}

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
