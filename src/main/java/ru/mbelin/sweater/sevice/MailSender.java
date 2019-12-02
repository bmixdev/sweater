package ru.mbelin.sweater.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailSender {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    public void send(String emailTo, String subject, String message)
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlMsg = message;
        try {
            helper.setText(htmlMsg, true); // Use this or above line.
            helper.setTo(emailTo);
            helper.setSubject(subject);
            helper.setFrom(username);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);
    }
}
