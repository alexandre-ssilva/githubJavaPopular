package com.ssilvaalexandre.desafioandroid.NetworkManager;

/**
 * Created by ssilvaalexandre on 20/10/17.
 * Nexaas All rights reserved
 */

public class NetworkResponse {
    private int statusCode;
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
