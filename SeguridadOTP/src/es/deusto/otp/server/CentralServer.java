package es.deusto.otp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.otp.protocol.SP2PSProtocol;
import es.deusto.otp.server.db.DAO;
import es.deusto.otp.server.db.JDO;
import es.deusto.otp.server.socket.SocketManager;
import es.deusto.otp.server.socket.UDPSocketManager;

public class CentralServer implements Runnable {
	protected Logger logger = LoggerFactory.getLogger(CentralServer.class);
	protected SocketManager socket;
	protected DAO dao = JDO.getInstance();
	
	public CentralServer() {
		socket = new UDPSocketManager();
	}
	
	public CentralServer(SocketManager socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		boolean running = true;
		String msg = null;
		
		while (running) {
			msg = socket.receive();
			msg = SP2PSProtocol.processInput(msg);
			socket.send(msg);
		}
		
		logger.info("Server Stopped.");		
	}
	
	public static void main(String[] args) {
		CentralServer s = new CentralServer();

		new Thread(s).start();

		try {
			Thread.sleep(50 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
