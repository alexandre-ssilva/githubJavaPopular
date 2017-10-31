package com.ssilvaalexandre.desafioandroid.NetworkManager;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ssilvaalexandre on 20/10/17.
 * Nexaas All rights reserved
 */

public class NetworkConnect {

    private static final int TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 15000;

    public static NetworkResponse connect (String url) throws Exception {
        if (url.contains("https"))
            return connectHTTP(url);

        return connectHTTPS(url);
    }

    public static NetworkResponse connectHTTP (String url) throws Exception {

        URL uri = new URL(url);

        HttpURLConnection conn = (HttpsURLConnection) uri.openConnection();

        HttpURLConnection.setFollowRedirects(true);
        conn.setConnectTimeout(TIMEOUT);
        conn.setReadTimeout(READ_TIMEOUT);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);

        conn.connect();

        NetworkResponse response = new NetworkResponse();

        response.setStatusCode(conn.getResponseCode());
        response.setContent(conn.getInputStream());

        return response;
    }

    private static NetworkResponse connectHTTPS (String url) throws Exception {
        URL uri = new URL(url);

        HttpsURLConnection conn = (HttpsURLConnection) uri.openConnection();

        HttpsURLConnection.setFollowRedirects(true);
        conn.setConnectTimeout(TIMEOUT);
        conn.setReadTimeout(READ_TIMEOUT);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);

        conn.connect();

        NetworkResponse response = new NetworkResponse();

        response.setStatusCode(conn.getResponseCode());
        response.setContent(conn.getInputStream());

        return response;
    }

}
