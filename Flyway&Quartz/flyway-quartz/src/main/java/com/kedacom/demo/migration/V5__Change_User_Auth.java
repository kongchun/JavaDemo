
/**
 * @(#)V5__Change_User_Auth.java 2018年7月18日 Copyright 2018 it.kedacom.com, Inc.
 *                               All rights reserved.
 */

package com.kedacom.demo.migration;

import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 利用Spring的JdbcTemplate执行SQL
 * @author lihongguang
 * @date 2018年7月18日
 */

public class V5__Change_User_Auth implements SpringJdbcMigration {

	/**
	 * @see org.flywaydb.core.api.migration.spring.SpringJdbcMigration#migrate(org.springframework.jdbc.core.JdbcTemplate)
	 */

	@Override
	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		jdbcTemplate.execute("update tbl_user set auth=0 where auth is NULL");
	}

}
