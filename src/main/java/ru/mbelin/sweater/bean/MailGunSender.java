package ru.mbelin.sweater.bean;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface MailGunSender {
    JsonNode sendMail(String from, String to, String subject, String textMsg) throws UnirestException;

    String sendMail(String to, String subject, String textMsg);
}
