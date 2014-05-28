package es.deusto.otp.protocol;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import es.deusto.otp.data.User;
import es.deusto.otp.server.db.DAO;
import es.deusto.otp.server.db.ORMLite;
import es.deusto.otp.server.mail.Mail;

public class OTPProtocol {
	private OTPState state = OTPState.WAITING;
	private DAO dao = ORMLite.getInstance();
	
	public OTPProtocol() {
		
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
					User user = dao.getUser(command[1]);
					
					if (user != null && user.getPassword().equals(command[1])) {
						arg0.add(command[1]);
												
						// TODO Comunicación con OTP
						try {
							Mail.sendMail(user.getEmail(), "NADA");
						} catch (AddressException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						theOutput = getCode(201, arg0);
						state = OTPState.READY;
					} else {
						theOutput = getCode(401, null);
					}
				} else {
					theOutput = getCode(401, null);
				}
			} else {
				theOutput = getCode(-1, null);
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
		case 401:
			theOutput = "401 ERROR INVALID USER";
			break;
		default:
			theOutput = "999 ERROR Comando desconocido";
			break;
		}

		return theOutput;
	}

}
