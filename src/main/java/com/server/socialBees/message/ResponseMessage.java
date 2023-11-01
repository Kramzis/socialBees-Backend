package com.server.socialBees.message;

import lombok.Getter;

@Getter
public class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
