/**
 * 
 */
package com.kedacom.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kedacom.demo.model.User;

/**
 * 用户账户
 * @author lihongguang
 */
@Repository
public class UserDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 根据用户名获取用户
	 * @param name
	 * @return
	 */
	public User getUser(String username) {
		String sql = "select id, username, password, role from demo_user where username=?";
		List<User> list = this.jdbcTemplate.query(sql, new UserMapper(), username);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 获取所有用户
	 * @return
	 */
	public List<User> listUser() {
		String sql = "select id, username, password, role from demo_user";
		return this.jdbcTemplate.query(sql, new UserMapper());
	}

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public Integer insertUser(User user) {
		String sql = "insert into demo_user (username,password,role) values (?,?,?)";
		return this.jdbcTemplate.update(sql, new Object[] {
				user.getUsername(), user.getPassword(), user.getRole()
		});
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public Integer updateUser(User user) {
		String sql = "update demo_user set password=? where username=?";
		return this.jdbcTemplate.update(sql, new Object[] {
				user.getPassword(), user.getUsername()
		});
	}

	private static final class UserMapper implements RowMapper<User> {

		/*
		 * (non-Javadoc)
		 * @see
		 * org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
		 * int)
		 */
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {

			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setRole(rs.getString("role"));
			return user;

		}

	}

}
