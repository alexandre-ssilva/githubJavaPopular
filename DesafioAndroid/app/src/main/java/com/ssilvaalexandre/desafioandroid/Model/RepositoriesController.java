package com.ssilvaalexandre.desafioandroid.Model;

import java.util.ArrayList;

/**
 * Created by alexandre on 28/10/17.
 * Nexaaas All rights reserved
 */

public class RepositoriesController {
    private int repoCount;
    private ArrayList<RepositoryModel>repos;

    public void setRepoCount(int repoCount) {
        this.repoCount = repoCount;
    }

    public int getRepoCount() {
        return repoCount;
    }

    public void setRepos(ArrayList<RepositoryModel> repos) {
        this.repos = repos;
    }

    public ArrayList<RepositoryModel> getRepos() {
        return repos;
    }

    public RepositoryModel getRepositoryAtIndex(int index) {
        return repos.get(index);
    }

    public void addRepositories (ArrayList<RepositoryModel>items) {
        if (repos == null)
            repos = new ArrayList<>();

        repos.addAll(items);
    }

    public void clearRepositories () {
        repoCount = 0;
        repos.clear();
    }
}
