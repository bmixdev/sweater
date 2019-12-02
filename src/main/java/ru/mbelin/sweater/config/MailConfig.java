package ru.mbelin.sweater.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:email.properties")
public class MailConfig {
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.protocol}")
    private String protocol;
    @Value("${mail.debug}")
    private String debug;


    @Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", protocol);
        properties.setProperty("mail.debug", debug);
        properties.setProperty("mail.smtp.auth", "true");

        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.trust", host);

   //     System.setProperty("javax.net.ssl.trustStore","clientTrustStore.key");
   //     System.setProperty("javax.net.ssl.trustStorePassword","qwerty");

      //  String ENCODING = "UTF-8";
        //properties.setProperty("mail.smtp.starttls.enable", "true");
       // properties.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
       // properties.setProperty("mail.smtp.ssl.enable", "true");
       // properties.setProperty("mail.smtp.ssl.trust", "smtpserver");
      //
       // properties.setProperty("mail.mime.charset", ENCODING);)

        return mailSender;
    };
}
