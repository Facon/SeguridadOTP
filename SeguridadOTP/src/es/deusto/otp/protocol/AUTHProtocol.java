package es.deusto.otp.protocol;

import java.util.ArrayList;
import java.util.List;

import es.deusto.otp.data.User;
import es.deusto.otp.server.db.DAO;
import es.deusto.otp.server.db.JDO;

public class AUTHProtocol {
	private AUTHState state = AUTHState.WAITING;
	private User user;
	private DAO dao = JDO.getInstance();
	
	public AUTHProtocol() {
		
	}

	public String processInput(String theInput) {
		String theOutput = getCode(-1, null);
		String[] command = theInput.split(" ");
		String codeOP = command[0];

		List<String> arg0 = new ArrayList<String>(6);

		switch (state) {
		case WAITING:
			if (codeOP.equals("USER")) {
				if (command.length > 1) {
					user = dao.getUser(command[1]);

					arg0.add(command[1]);

					theOutput = getCode(201, arg0);
					state = AUTHState.AUTH;
				} else {
					theOutput = getCode(401, null);
				}
			} else {
				theOutput = getCode(-1, null);
			}

			break;

		case AUTH:
			if (codeOP.equals("PASS")) {
				if (command.length > 1) {
					if (user != null && user.getPassword().equals(command[1])) {
						theOutput = getCode(202, null);
						state = AUTHState.OTP;
					} else {
						user = null;
						theOutput = getCode(402, null);
						state = AUTHState.WAITING;
					}
				} else {
					theOutput = getCode(403, null);
					state = AUTHState.WAITING;
				}
			} else {
				theOutput = getCode(-1, null);
				state = AUTHState.WAITING;
			}

			break;

		case OTP:
			if (codeOP.equals("OTP")) {
				if (command.length > 1) {
					if (user != null && user.getPassword().equals(command[1])) {
						theOutput = getCode(203, null);
						state = AUTHState.READY;
					} else {
						user = null;
						theOutput = getCode(404, null);
						state = AUTHState.WAITING;
					}
				} else {
					theOutput = getCode(405, null);
					state = AUTHState.WAITING;
				}
			} else {
				theOutput = getCode(-1, null);
				state = AUTHState.WAITING;
			}

			break;

		case READY:
			break;
		default:
			break;
		}

		arg0.clear();

		return theOutput;
	}

	public String getCode(int code, List<String> list) {
		String theOutput = null;

		switch (code) {
		case 201:
			theOutput = "201 OK CORRECT USER";
			break;
		case 202:
			theOutput = "202 OK CORRECT PASSWORD";
			break;
		case 203:
			theOutput = "203 OK CORRECT OTP";
			break;
		case 401:
			theOutput = "401 ERROR INVALID USER";
			break;
		case 402:
			theOutput = "402 ERROR INCORRECT PASS";
			break;
		case 403:
			theOutput = "403 ERROR INCORRECT PARAMETERS";
			break;
		case 404:
			theOutput = "404 ERROR INCORRECT OTP";
			break;
		case 405:
			theOutput = "405 ERROR INCORRECT PARAMETERS";
			break;
		default:
			theOutput = "999 ERROR Comando desconocido";
			break;
		}

		return theOutput;
	}

}