package com.example.keeprawteach.csi.Model;

public class SendFeed {

    String message,sender,id,response;

    public SendFeed() {
    }

    public SendFeed(String message, String sender, String id, String response) {
        this.message = message;
        this.sender = sender;
        this.id = id;
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
