
/**
 * @(#)Log.java 2018年8月23日 Copyright 2018 it.kedacom.com, Inc. All rights
 *              reserved.
 */

package com.kedacom.cxf.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 日志实体类
 * @author lihongguang
 * @date 2018年8月23日
 */
@XmlRootElement(name = "log")
public class Log {

	private Long id;
	private Integer userid;
	private String description;

	public Log() {

	}

	public Log(Long id, Integer userid, String description) {
		this.id = id;
		this.userid = userid;
		this.description = description;
	}

	/** @return the id */
	public Long getId() {
		return id;
	}

	/** @param id the id to set */
	public void setId(Long id) {
		this.id = id;
	}

	/** @return the userid */
	public Integer getUserid() {
		return userid;
	}

	/** @param userid the userid to set */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	/** @return the description */
	public String getDescription() {
		return description;
	}

	/** @param description the description to set */
	public void setDescription(String description) {
		this.description = description;
	}

}
