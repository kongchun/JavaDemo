
/**
 * @(#)FlywayTest.java 2018年7月18日 Copyright 2018 it.kedacom.com, Inc. All rights
 *                     reserved.
 */

package com.kedacom.demo.test;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

/**
 * 手动控制Flyway
 * @author lihongguang
 * @date 2018年7月18日
 */

public class FlywayTest {

	public static void main(String[] args) {

		Flyway flyway = new Flyway();

		flyway.setDataSource("jdbc:mysql://localhost:3306/flyway_demo?serverTimezone=GMT%2B8", "root", "root");
		// flyway.setTable("schema_version");
		// flyway.setSqlMigrationPrefix("V");
		// flyway.setSqlMigrationSeparator("__");
		// flyway.setSqlMigrationSuffixes("sql");
		flyway.setLocations("classpath:flyway/demo", "com.kedacom.demo.migration");
		// flyway.clean();
		// flyway.setBaselineVersionAsString("4");
		// flyway.baseline();
		try {
			// flyway.validate();
			flyway.migrate();
		} catch (FlywayException e) {
			System.out.println(e.getMessage());
			flyway.repair();
		}

	}

}
