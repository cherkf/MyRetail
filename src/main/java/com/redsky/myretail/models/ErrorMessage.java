package com.redsky.myretail.models;

import java.util.Date;

/**
 * Custom error message used by internal exceptions. The error message includes timestamp,
 * a custom message and details describing the error, and finally the HTTP response status code
 */

public class ErrorMessage {

    private Date timestamp;

    private String message;

    private String details;

    private int status;


    public ErrorMessage(Date timestamp, String message, String details, int status) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
