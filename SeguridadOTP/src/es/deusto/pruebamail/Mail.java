package es.deusto.pruebamail;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("alumnodeustoseguridad@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("cppapprentice@gmail.com"));
			message.setSubject("Contraseña OTP");
			message.setText("Esta es la contraseña que tienes que copiar,"
					+ "\n\n " + String.valueOf(new Random().nextInt(9999)));

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
