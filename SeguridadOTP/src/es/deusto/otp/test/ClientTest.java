package es.deusto.otp.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.otp.data.User;

public class ClientTest {
	Logger logger = LoggerFactory.getLogger(ClientTest.class);
	
	@Test
	public void test() {
		User client = new User("Asier", "localhost");
		
		logger.info("{} {} {}", client.getNick(), client.getEmail());
		
		User client2 = new User("5678", "Pescado", "127.0.0.1");
		
		logger.info("{} {} {}", client2.getNick(), client2.getEmail());
	}

}
