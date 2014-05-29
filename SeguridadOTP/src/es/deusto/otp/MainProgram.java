package es.deusto.otp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import es.deusto.otp.server.CentralServer;
import es.deusto.otp.server.OTPServer;

public class MainProgram {

	public static void main(String[] args) {		
		try {			
			Socket sock = new Socket("127.0.0.1", 4445);
			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			out.println("USER Asier");
			System.out.println(in.readLine());
			out.println("PASS 123");
			System.out.println(in.readLine());
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader (isr);
			
			String code = br.readLine();
			
			out.println("OTP " + code);
			System.out.println(in.readLine());
			
			out.close();
			in.close();
			sock.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
