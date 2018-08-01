
/**
 * @(#)FlywayMigration.java 2018年7月18日 Copyright 2018 it.kedacom.com, Inc. All
 *                          rights reserved.
 */

package com.kedacom.demo.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Flyway操作
 * @author lihongguang
 * @date 2018年7月18日
 */
@Component
public class FlywayOperation {

	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@PostConstruct
	public void doMigrate() {

		Flyway flyway = new Flyway();
		flyway.setDataSource(dataSource);

		flyway.setSchemas("flyway_demo");
		// flyway.setTable("schema_version");
		// 修改flyway自动扫描SQL路径，默认classpath:db/migration和db.migration
		flyway.setLocations("classpath:flyway/demo", "com.kedacom.demo.migration");
		flyway.setEncoding("UTF-8");
		// 设置可以乱序（可以补充版本号）
		flyway.setOutOfOrder(true);
		// flyway.setSqlMigrationPrefix("V");
		// flyway.setSqlMigrationSeparator("__");
		// flyway.setSqlMigrationSuffixes("sql");
		// flyway.clean();
		// flyway.setBaselineVersionAsString("4");
		// flyway.baseline();
		try {
			flyway.migrate();
		} catch (FlywayException e) {
			System.out.println(e.getMessage());
			flyway.repair();
		}
	}

}
