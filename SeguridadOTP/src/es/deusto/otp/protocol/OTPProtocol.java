package es.deusto.otp.protocol;

import java.util.List;

public class OTPProtocol {
	
	public static String processInput(String theInput) {
	/*	String theOutput = SP2PSProtocol.getCode(-1, null);
		String[] command = theInput.split(" ");
		String codeOP = command[0];

		ArrayList<String> arg0 = new ArrayList<String>(6);

		if (codeOP.equals("REGPEER")) {
			if (command.length > 1) {
				user = getUser(command[1]);

				arg0.add(command[1]);

				theOutput = SP2PSProtocol.getCode(200, null);
			}
			else {
				theOutput = SP2PSProtocol.getCode(400, null);
			}
		}
		else if (codeOP.equals("PASS")) {
			if (command.length > 1) {
				if (user != null && user.getPassword().equals(command[1])) {
					theOutput = SP2PSProtocol.getCode(202, null);
				}
				else {
					user = null;
					theOutput = SP2PSProtocol.getCode(402, null);
				}
			}
			else {
				theOutput = SP2PSProtocol.getCode(403, null);
			}
		}
		else {
			theOutput = SP2PSProtocol.getCode(-1, null);
		}

		arg0.clear();

		return theOutput;*/
		return null;
	}

	public static String getCode(int code, List<String> list) {
		String theOutput = null;

		switch (code) {
		case 200:
			theOutput = "200 OK";
			break;
		case 201:
			theOutput = "201 OK";
			break;
		case 202:
			theOutput = "202 OK";
			break;
		case 203:
			theOutput = "203 OK";
			break;
		case 300:
			theOutput = "300 END";
			break;
		case 400:
			theOutput = "400 ERROR INVALID USER";
			break;
		case 401:
			theOutput = "401 USER NOT FOUND";
			break;
		case 402:
			theOutput = "402 USERS NOT FOUND";
			break;
		case 403:
			theOutput = "403 FILES NOT FOUND";
			break;
		default:
			theOutput = "999 ERROR Comando desconocido.";
			break;
		}

		return theOutput;
	}

}
