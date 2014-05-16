package es.deusto.otp.test;

import org.junit.Before;
import org.junit.Test;

import es.deusto.otp.data.OTPCode;
import es.deusto.otp.data.User;
import es.deusto.otp.server.db.DAO;
import es.deusto.otp.server.db.JDO;

public class JDOTest {

	private DAO jdo = JDO.getInstance();
	private User client;
	private OTPCode code1;
	
	@Before
	public void setUp() {
		client = new User("Asier", "localhost");
		code1 = new OTPCode(client);
	}
	
	@Test
	public void test() {
		jdo.addUser(client);
	}
	
	@Test
	public void addOTPCode() {
		jdo.addOTPCode(code1);
		System.out.println("OK");
		System.out.println(jdo.getUser(client.getNick()));
	}

}
