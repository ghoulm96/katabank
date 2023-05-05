package com.kataexaltit.bankaccount.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class ErrorDetails {
    private String message;
    private String uri;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    public ErrorDetails() {
        this.timestamp = new Date();
    }

    public String getMessage() {
        return message;
    }

    public String getUri() {
        return uri;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public ErrorDetails(String message, String uri) {
        this();
        this.message = message;
        this.uri = uri;
    }
}
