package com.kedacom.thrift.main;

import com.kedacom.thrift.gen.CustomException;
import com.kedacom.thrift.gen.DemoService;
import com.kedacom.thrift.gen.referencesConstants;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * (用一句话描述类的主要功能)
 * 
 * @author lihongguang
 * @date 2018年9月17日
 */

public class JavaClient {

	private static final Logger logger = LoggerFactory.getLogger(JavaClient.class);

	/**
	 * (用一句话描述方法的主要功能)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			TTransport transport = new TSocket("localhost", 9090);;
			transport.open();
			
			TProtocol protocol = new TBinaryProtocol(transport);
			DemoService.Client client = new DemoService.Client(protocol);

			perform(client);

			transport.close();
		} catch (TException x) {
			logger.error(x.getMessage());
		}

	}

	private static void perform(DemoService.Client client) throws TException {

		try {
			logger.info(client.throwException(referencesConstants.flag));
		} catch (CustomException e) {
			logger.warn("Catch CustomException: "+e.getName());
		}

		logger.info(client.helloWorld());
	}
}
