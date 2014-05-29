package es.deusto.otp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.otp.protocol.OTPProtocol;
import es.deusto.otp.server.socket.SocketManager;

public class OTPServer implements Runnable {
	protected Logger logger = LoggerFactory.getLogger(OTPServer.class);
	protected SocketManager socket;
	
	public OTPServer() {
		socket = new SocketManager(new OTPProtocol(), 4446);
	}
	
	public OTPServer(SocketManager socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		socket.run();
		
		logger.info("Server Stopped.");		
	}
	
	public static void main(String[] args) {
		OTPServer s = new OTPServer();

		new Thread(s).start();

		try {
			Thread.sleep(100 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
