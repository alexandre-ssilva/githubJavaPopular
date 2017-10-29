package com.ssilvaalexandre.desafioandroid.NetworkManager;

import com.ssilvaalexandre.desafioandroid.Util.Util;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ssilvaalexandre on 20/10/17.
 * Nexaas All rights reserved
 */

public class NetworkConnect {

    public static NetworkResponse connect (String url) throws Exception {

        URL uri = new URL(url);

        HttpsURLConnection conn = (HttpsURLConnection) uri.openConnection();

        HttpsURLConnection.setFollowRedirects(true);
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);

        conn.connect();

        NetworkResponse response = new NetworkResponse();

        response.setStatusCode(conn.getResponseCode());
        response.setContent(Util.convertStreamToString(conn.getInputStream()));

        return response;
    }
}
