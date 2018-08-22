/**
 * 
 */
package com.kedacom.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author lihongguang
 */
public class EncodeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("1"));

	}

}
