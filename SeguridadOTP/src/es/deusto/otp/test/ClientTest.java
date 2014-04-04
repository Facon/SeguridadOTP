package es.deusto.otp.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.otp.data.Client;

public class ClientTest {
	Logger logger = LoggerFactory.getLogger(ClientTest.class);
	
	@Test
	public void test() {
		Client client = new Client("Facon", "localhost");
		
		logger.info("{} {} {}", client.getId(), client.getNick(), client.getIp());
		
		Client client2 = new Client("5678", "Pescado", "127.0.0.1");
		
		logger.info("{} {} {}", client2.getId(), client2.getNick(), client2.getIp());
	}

}
