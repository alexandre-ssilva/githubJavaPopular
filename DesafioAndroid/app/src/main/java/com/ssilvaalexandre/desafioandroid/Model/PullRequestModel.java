package com.ssilvaalexandre.desafioandroid.Model;

/**
 * Created by alexandre on 28/10/17.
 * Nexaaas All rights reserved
 */

public class PullRequestModel {
    private int id;
    private UserModel owner;
    private String pullName, pullBody;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }

    public UserModel getOwner() {
        return owner;
    }

    public void setPullName(String pullName) {
        this.pullName = pullName;
    }

    public String getPullName() {
        return pullName;
    }

    public void setPullBody(String pullBody) {
        this.pullBody = pullBody;
    }

    public String getPullBody() {
        return pullBody;
    }
}
