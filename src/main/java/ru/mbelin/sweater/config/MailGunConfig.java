package ru.mbelin.sweater.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.mbelin.sweater.bean.MailGunSender;
import ru.mbelin.sweater.bean.MailGunSenderImpl;

@Configuration
@PropertySource("classpath:mailgun.properties")
public class MailGunConfig {
    @Value("${mailgun.domain}")
    private String domain;
    @Value("${mailgun.api_key}")
    private String api_key;
    @Value("${mailgun.from}")
    private String from;

    @Bean
    public MailGunSender getMailGunSender(){
        MailGunSenderImpl sender = new MailGunSenderImpl();
        sender.setDomain(domain);
        sender.setApi_key(api_key);
        sender.setFrom(from);
        return sender;
    }

}
