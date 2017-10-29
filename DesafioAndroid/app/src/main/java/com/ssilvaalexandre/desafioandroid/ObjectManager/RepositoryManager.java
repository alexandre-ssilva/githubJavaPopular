package com.ssilvaalexandre.desafioandroid.ObjectManager;

import com.ssilvaalexandre.desafioandroid.Model.RepositoriesController;
import com.ssilvaalexandre.desafioandroid.Model.RepositoryModel;
import com.ssilvaalexandre.desafioandroid.Model.UserModel;
import com.ssilvaalexandre.desafioandroid.NetworkManager.NetworkConnect;
import com.ssilvaalexandre.desafioandroid.NetworkManager.NetworkResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by ssilvaalexandre on 20/10/17.
 * Nexaas All rights reserved
 */

public class RepositoryManager {

    private static final String JAVA_REPO = "https://api.github.com/search/" +
            "repositories?q=language:Java&sort=stars&page=";

    public static RepositoriesController getRepositories (int page) throws Exception {

        String url = JAVA_REPO + page;

        NetworkResponse response = NetworkConnect.connect(url);

        if (response == null)
            throw new Exception("A conexão falhou.");

        if (response.getStatusCode() != 200)
            throw new Exception("Não foi possível recuperar repositórios");

        JSONObject repositoriesJSON = new JSONObject(response.getContent());


        RepositoriesController controller = new RepositoriesController();
        controller.setRepoCount(repositoriesJSON.getInt("total_count"));

        controller.addRepositories(getRepositoriesFromJSON(repositoriesJSON.getJSONArray("items")));

        return controller;
    }

    private static ArrayList<RepositoryModel> getRepositoriesFromJSON (JSONArray items) throws JSONException {

        ArrayList<RepositoryModel> repos = new ArrayList<>();

        for (int i = 0; i < items.length(); i++) {
            RepositoryModel repo = new RepositoryModel();
            JSONObject item = items.getJSONObject(i);

            repo.setId(item.getInt("id"));
            repo.setName(item.getString("name"));
            repo.setFullName(item.getString("full_name"));
            repo.setDescription(item.getString("description"));
            repo.setForksNumber(item.getInt("forks_count"));
            repo.setStarsNumber(item.getInt("stargazers_count"));
            repo.setPullsUrl(item.getString("pulls_url"));

            UserModel owner = new UserModel();

            JSONObject ownerJSON = item.getJSONObject("owner");

            owner.setId(ownerJSON.getInt("id"));
            owner.setLoginName(ownerJSON.getString("login"));
            owner.setType(ownerJSON.getString("type"));
            owner.setAvatarUrl(ownerJSON.getString("avatar_url"));
            owner.setSelfUrl(ownerJSON.getString("url"));

            repo.setOwner(owner);

            repos.add(repo);
        }

        return repos;
    }


}
