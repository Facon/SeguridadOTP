package es.deusto.otp.client.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketFacade {
	private Socket sock;
	private PrintWriter out;
	private BufferedReader in;
	
	public SocketFacade(String ip, int port) throws UnknownHostException, IOException {
		this.sock = new Socket(ip, port);
		this.out = new PrintWriter(sock.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	}
	
	public void write(String msg) {
		out.println(msg);
	}
	
	public String read() throws IOException {
		return in.readLine();
	}
}
