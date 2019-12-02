package ru.mbelin.sweater.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.mbelin.sweater.bean.BeanKeys;

@Configuration
@PropertySource("classpath:keys.properties")
public class KeysConfig {
    @Value("${google.recaptcha.secret}")
    private String googleRecaptchaSecret;

    @Value("${google.recaptcha.url}")
    private String googleRecaptchaUrl;

    @Bean
    public BeanKeys getBeanKeys() {
        BeanKeys beanKeys = new BeanKeys();
        beanKeys.setGoogleCaptchaSecret(googleRecaptchaSecret);
        beanKeys.setGoogleCaptchaURL(googleRecaptchaUrl);
        return beanKeys;
    }
}
