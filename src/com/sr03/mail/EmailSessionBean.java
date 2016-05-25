package com.sr03.mail;


import java.util.Date;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.net.httpserver.HttpExchange;

/**
 * Session Bean implementation class EmailSessionBean
 */
@Stateless
@LocalBean
public class EmailSessionBean {
	
	private int port = 587 ;
	private String host = "smtp-mail.outlook.com";
	private String from = "sr03_projet@outlook.com";
	private boolean auth = true;
	private String username = "sr03_projet@outlook.com";
	private String password = "projetSR03";
	private Protocol protocol = Protocol.SMTPS;
	private boolean debug = true;
	
	
    /**
     * Default constructor. 
     */
    public EmailSessionBean() {
        // TODO Auto-generated constructor stub
    	
    }
    
    public void sendEmail(String to,  String subject, String body){
    	Properties props = new Properties();
    	props.put("mail.smtp.host", host);
    	props.put("mail.smtp.port", port);
    	props.put("mail.smtp.starttls.enable", true);


    	// props.put("mail.smtp.socketFactory.fallback", "true"); // Should be true
    	

    	
        Authenticator authenticator = null;
        if (auth) {
            props.put("mail.smtp.auth", true);
            authenticator =  new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username,password);
                 }

              };
        }
        
        Session session = Session.getInstance(props, authenticator);
        session.setDebug(debug);
        
        System.out.println("About to send the message!!!");
        
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        
        
	}
    
  

}