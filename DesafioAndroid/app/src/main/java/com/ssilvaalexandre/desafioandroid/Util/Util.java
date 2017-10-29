package com.ssilvaalexandre.desafioandroid.Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by alexandre on 28/10/17.
 * Nexaaas All rights reserved
 */

public class Util {
    public static String convertStreamToString(InputStream is) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {

            sb.append(line).append("\n");
        }

        is.close();

        return sb.toString();
    }
}
