package es.deusto.otp.server.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mail {

	public static void sendMail(String email, String otp) throws AddressException, MessagingException {
		final String username = "alumnodeustoseguridad@gmail.com";
		final String password = "seguridaddelainformacion";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("alumnodeustoseguridad@gmail.com"));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
		message.setSubject("Contraseña OTP");
		message.setText("Esta es la contraseña que tienes que copiar," + "\n\n"
				+ otp);

		Transport.send(message);
	}

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(Mail.class);
		
		try {
			sendMail("cppapprentice@gmail.com", "LALELILOLU");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("Done :-) .");
	}

}
