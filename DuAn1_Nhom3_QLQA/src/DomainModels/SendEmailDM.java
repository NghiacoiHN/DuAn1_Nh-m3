/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.net.Authenticator;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author TRONG NGHIA
 */
public class SendEmailDM {
    
    public static void send(QuenMKDM email) throws Exception {
        Properties prop = new Properties();
        prop.put("mail. smtp.host", "smtp.gmail.com");
        prop.put("mail. smtp .port", "587");
        prop.put("mail. smtp auth", "true");
        prop.put("mail. smtp. starttls.enable", "true");
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(email.getFrom(), email.getFromPassword());
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
            message.setContent(email.getContent(), "text/html; charset=utf-8");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
