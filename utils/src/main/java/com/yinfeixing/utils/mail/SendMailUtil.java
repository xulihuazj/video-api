package com.yinfeixing.utils.mail;

import java.util.List;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.yinfeixing.utils.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendMailUtil {
    private static final Logger logger = LogManager.getLogger(SendMailUtil.class);
    private static transient Properties props = System.getProperties();
    private static transient MailAuthenticator authenticator;
    private static transient Session session;

    public SendMailUtil(String smtpHostName, String username, String password, String port) {
        init(username, password, smtpHostName, port);
    }

    public static final void init(String username, String password, String smtpHostName, String port) {
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", smtpHostName);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        authenticator = new MailAuthenticator(username, password);
        session = Session.getInstance(props, authenticator);
    }

    public static void send(String recipient, String subject, Object content) throws AddressException, MessagingException {
        LogHelper.info(logger, "发送邮件开始", new Object[0]);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(authenticator.getUsername()));
        message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setContent(content.toString(), "text/html;charset=utf-8");
        Transport.send(message);
        LogHelper.info(logger, "发送邮件结束", new Object[0]);
    }

    public static void send(List<String> recipients, String subject, Object content) throws AddressException, MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(authenticator.getUsername()));
        int num = recipients.size();
        InternetAddress[] addresses = new InternetAddress[num];

        for(int i = 0; i < num; ++i) {
            addresses[i] = new InternetAddress((String)recipients.get(i));
        }

        message.setRecipients(RecipientType.TO, addresses);
        message.setSubject(subject);
        message.setContent(content.toString(), "text/html;charset=utf-8");
        Transport.send(message);
    }

    public static void send(String recipient, MailEntity mail) throws AddressException, MessagingException {
        send((String)recipient, mail.getSubject(), mail.getContent());
    }

    public static void send(List<String> recipients, MailEntity mail) throws AddressException, MessagingException {
        send((List)recipients, mail.getSubject(), mail.getContent());
    }
}