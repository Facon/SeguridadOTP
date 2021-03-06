package es.deusto.otp.test;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.otp.data.OTPCode;
import es.deusto.otp.data.User;
import es.deusto.otp.server.db.DAO;
import es.deusto.otp.server.db.ORMLite;

public class ORMLiteTest {
	Logger logger = LoggerFactory.getLogger(ClientTest.class);	
	
	private DAO jdo = ORMLite.getInstance();
	private User client;
	private OTPCode code1;
	
	@Before
	public void setUp() {
		client = new User("Asier", "a@a.com");
		jdo.createTables();
	}
	
	@Test
	public void addUser() {
		if (jdo.getUser(client.getNick()) == null) {
			jdo.addUser(client);
		}
	}
	
	@Test
	public void addOTPCode() {
		code1 = new OTPCode(jdo.getUser(client.getNick()));
		logger.info("{}", code1.getUser().getNick());
		jdo.addOTPCode(code1);
		System.out.println("OK");
		System.out.println(jdo.getUser(client.getNick()).getNick());
	}

}
