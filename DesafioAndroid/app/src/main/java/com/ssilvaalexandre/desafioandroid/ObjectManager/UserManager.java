package com.ssilvaalexandre.desafioandroid.ObjectManager;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.ssilvaalexandre.desafioandroid.NetworkManager.NetworkConnect;
import com.ssilvaalexandre.desafioandroid.NetworkManager.NetworkResponse;

/**
 * Created by alexandre on 30/10/17.
 * Nexaaas All rights reserved
 */

public class UserManager {

    public static Drawable getUserAvatar (String url) throws Exception {
        NetworkResponse response = NetworkConnect.connect(url);

        if (response == null)
            throw new Exception("A conexão falhou.");

        if (response.getStatusCode() != 200)
            throw new Exception("Não foi possível recuperar avatar.");

        return BitmapDrawable.createFromStream(response.getContent(), "avatar");

    }
}
