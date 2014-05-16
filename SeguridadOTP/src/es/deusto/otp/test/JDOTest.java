package es.deusto.otp.test;

import org.junit.Before;
import org.junit.Test;

import es.deusto.otp.data.User;
import es.deusto.otp.server.db.DAO;
import es.deusto.otp.server.db.JDO;

public class JDOTest {

	private DAO jdo = JDO.getInstance();
	private User client;
	
	@Before
	public void setUp() {
		client = new User("Asier", "localhost");
	}
	
	@Test
	public void test() {
		jdo.addUser(client);
	}

}
