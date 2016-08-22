package com.consultancy.company.service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmailAttachment {

	public void sendEmail(String to, String filename) {
		
		final String user = "pradeepcharan999@gmail.com";
		final String password = "";
		String host = "smtp.gmail.com";

		// 1) get the session object
		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});

		// 2) compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject("Invoice");

			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Your project invoice is attached in this email. please find the attachment");

			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("message sent....");

		} catch (MessagingException ex) {
			ex.printStackTrace();
		}

	}
}
