package es.deusto.otp.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.otp.protocol.OTPProtocol;

public class OTPProtocolTest {
	Logger logger = LoggerFactory.getLogger(OTPProtocolTest.class);	
	
	OTPProtocol otpprotocol;

	@Before
	public void setUp() throws Exception {
		otpprotocol = new OTPProtocol();
	}

	@Test
	public void test() {
		logger.info(otpprotocol.processInput("USER Asier"));
	}

}
