package ru.mbelin.sweater.bean;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MailGunSenderImpl implements MailGunSender {
    private String domain;
    private String api_key;
    private String from;


    @Override
    public JsonNode sendMail(String from, String to, String subject, String textMsg) throws UnirestException {
        String tmpFrom;
        if (from == null || from.isEmpty()) {
            tmpFrom = this.from;
        } else
        {
            tmpFrom = from;
        }

        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + domain + "/messages")
                .basicAuth("api", api_key)
                .field("from", tmpFrom)
                .field("to", to)
                .field("subject", subject)
                .field("text", textMsg)//"Your mail do not support HTML")
                .field("html", textMsg)
                //.field("attachment", new File("/temp/folder/test.txt"))
                .asJson();
        return request.getBody();
    }

    @Override
    public String sendMail(String to, String subject, String textMsg) {
        JsonNode request;
        try {
            request = sendMail(this.from, to, subject, textMsg);
            return request.toString();
        }
        catch (UnirestException e)
        {
            e.printStackTrace();
        }
       return null;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
