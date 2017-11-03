package com.ssilvaalexandre.desafioandroid.ObjectManager;

import com.ssilvaalexandre.desafioandroid.Model.PullRequestModel;
import com.ssilvaalexandre.desafioandroid.Model.UserModel;
import com.ssilvaalexandre.desafioandroid.NetworkManager.NetworkConnect;
import com.ssilvaalexandre.desafioandroid.NetworkManager.NetworkResponse;
import com.ssilvaalexandre.desafioandroid.Util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by alexandre on 31/10/17.
 * Nexaaas All rights reserved
 */

public class PullManager {
    private static final String PULLS = "/pulls";

    public static ArrayList<PullRequestModel> getPulls (String url) throws Exception {

        NetworkResponse response = NetworkConnect.connect(url + PULLS);

        if (response == null)
            throw new Exception("Não foi possível conectar ao servidor. Tente novamente mais tarde.");

        if (response.getStatusCode() != 200)
            throw new Exception("Ocorreu um erro ao recuperar Pull Requests. Error: " + response.getStatusCode());

        JSONArray pullJSONArray = new JSONArray(Util.convertStreamToString(response.getContent()));

        return parsePullJSON(pullJSONArray);
    }

    private static ArrayList<PullRequestModel> parsePullJSON (JSONArray jsonArray) throws Exception {

        ArrayList<PullRequestModel> pulls = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            PullRequestModel pull = new PullRequestModel();
            JSONObject pullJSON = jsonArray.getJSONObject(i);

            pull.setId(pullJSON.getInt("id"));
            pull.setPullBody(pullJSON.getString("body"));
            pull.setPullName(pullJSON.getString("title"));
            pull.setSelfUrl(pullJSON.getString("html_url"));

            UserModel owner = new UserModel();

            JSONObject ownerJSON = pullJSON.getJSONObject("user");

            owner.setId(ownerJSON.getInt("id"));
            owner.setType(ownerJSON.getString("type"));
            owner.setSelfUrl(ownerJSON.getString("url"));
            owner.setAvatarUrl(ownerJSON.getString("avatar_url"));
            owner.setLoginName(ownerJSON.getString("login"));

            pull.setOwner(owner);

            pulls.add(pull);
        }

        return pulls;
    }
}
