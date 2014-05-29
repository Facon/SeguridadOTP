package es.deusto.otp.server.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.deusto.otp.protocol.Protocol;

public class SocketManager implements Runnable {
	private int idcounter = 0;
	private Protocol protocol;
	
	private int port;
	private int nconnections = 10;
	private ExecutorService es = Executors.newFixedThreadPool(nconnections);
	private ServerSocket serverSocket = null;
	private Thread runningThread = null;
	private boolean isStopped = false;
	
	public SocketManager(Protocol protocol, int port) {
		this.protocol = protocol;
		this.port = port;
		openServerSocket();
	}
	
	public int getNconnections() {
		return nconnections;
	}

	public void setNconnections(int nconnections) {
		this.nconnections = nconnections;
	}

	public Thread getRunningThread() {
		return runningThread;
	}

	public void setRunningThread(Thread runningThread) {
		this.runningThread = runningThread;
	}

	private synchronized boolean isStopped() {
		return this.isStopped;
	}

	public synchronized void stop() {
		this.isStopped = true;
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			throw new RuntimeException("Error closing server", e);
		}
	}

	private void openServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.port);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open port " + this.port, e);
		}
	}

	public void run() {
		synchronized (this) {
			this.setRunningThread(Thread.currentThread());
		}

		while (!isStopped()) {
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
			} catch (IOException e) {
				if (isStopped()) {
					System.err.println("Server Stopped.");
					return;
				}
				throw new RuntimeException("Error accepting client connection", e);
			}

			Handler handler = new Handler(clientSocket, idcounter++, this.protocol);

			//handlers.add(handler);

			this.es.execute(handler);
		}

		this.es.shutdownNow();
		System.out.println("Server Stopped.");
	}

	class Handler implements Runnable {
		private int id;
		private final Socket socket;
		private PrintWriter out = null;
		private BufferedReader in = null;
		private Protocol protocol;

		Handler(Socket socket, int id, Protocol protocol) {
			this.id = id;
			this.socket = socket;
			this.protocol = protocol;
			
			try {
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to: Localhost.");
			}
		}

		public void close() {
			try {
				socket.close();
				out.close();
				in.close();
			} catch (IOException e) {
				System.err.println("Couldn't close Socket.");
			}
		}

		public void run() {
			int code = 0;
			String command, output;
			String[] command2;

			while (code != 208) {
				try {
					command = in.readLine();

					output = protocol.processInput(command);

					out.println(output);

					command2 = output.split(" ");
					code = Integer.parseInt(command2[0]);
				} catch (SocketException e) {
					if (!e.getMessage().equals("Connection reset")) {
						e.printStackTrace();
					}
					code = 208;
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Couldn't read Socket.");	
					code = 208;
				}
			}

			close();
		}
	}
}
