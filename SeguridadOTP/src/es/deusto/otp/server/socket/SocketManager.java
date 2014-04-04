package es.deusto.otp.server.socket;

public interface SocketManager {

	public String receive();

	public void send(String msg);

}
