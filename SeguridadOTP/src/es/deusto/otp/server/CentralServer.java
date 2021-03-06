package es.deusto.otp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.otp.protocol.AUTHProtocol;
import es.deusto.otp.server.socket.SocketManager;

public class CentralServer implements Runnable {
	protected Logger logger = LoggerFactory.getLogger(CentralServer.class);
	protected SocketManager socket;
	
	public CentralServer() {
		socket = new SocketManager(new AUTHProtocol(), 4445);
	}
	
	public CentralServer(SocketManager socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		socket.run();
		
		logger.info("Server Stopped.");		
	}
	
	public static void main(String[] args) {
		CentralServer s = new CentralServer();

		new Thread(s).start();

		try {
			Thread.sleep(100 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
