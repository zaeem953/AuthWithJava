package com.sms.auth.entity;

public class apiData {
    private String to;
    private String subject;
    private String body;
    private String method;

    public apiData() {
    }

    public apiData(String to, String subject, String body, String method) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.method = method;
    }

    public String getTo() {
        return to="Zaeemmavia321@gmail.com";
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "apiData{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
