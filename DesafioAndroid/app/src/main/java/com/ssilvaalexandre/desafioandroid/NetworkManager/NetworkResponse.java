package com.ssilvaalexandre.desafioandroid.NetworkManager;

import java.io.InputStream;

/**
 * Created by ssilvaalexandre on 20/10/17.
 * Nexaas All rights reserved
 */

public class NetworkResponse {
    private int statusCode;
    private InputStream content;

    public void setContent(InputStream content) {
        this.content = content;
    }

    public InputStream getContent() {
        return content;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
