package com.kedacom.cxf.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用户实体类
 * @author lihongguang
 * @date 2018年8月23日
 */
@XmlRootElement(name = "user")
public class User {

	private Integer id;
	private String name;

	/** @return the id */
	public Integer getId() {
		return id;
	}

	/** @param id the id to set */
	public void setId(Integer id) {
		this.id = id;
	}

	/** @return the name */
	public String getName() {
		return name;
	}

	/** @param name the name to set */
	public void setName(String name) {
		this.name = name;
	}

}
