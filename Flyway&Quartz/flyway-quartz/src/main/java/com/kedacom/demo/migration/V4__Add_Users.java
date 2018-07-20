
/**
 * @(#)V4__Add_Users.java 2018年7月18日 Copyright 2018 it.kedacom.com, Inc. All
 *                        rights reserved.
 */

package com.kedacom.demo.migration;

import java.sql.Connection;
import java.sql.Statement;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

/**
 * 用普通JDBC方式执行SQL
 * @author lihongguang
 * @date 2018年7月18日
 */

public class V4__Add_Users implements JdbcMigration {

	/**
	 * @see org.flywaydb.core.api.migration.jdbc.JdbcMigration#migrate(java.sql.Connection)
	 */

	@Override
	public void migrate(Connection connection) throws Exception {
		for (int i = 0; i < 3; i++) {
			Statement insert = connection.createStatement();
			insert.execute("insert into tbl_user (id,name) values (" + (i + 2) + ",'User" + (i + 2) + "')");
		}

	}

}
