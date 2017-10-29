package com.ssilvaalexandre.desafioandroid.Model;

/**
 * Created by alexandre on 28/10/17.
 * Nexaaas All rights reserved
 */

public class UserModel {
    private int id;
    private String avatarUrl, loginName, selfUrl, type;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setSelfUrl(String selfUrl) {
        this.selfUrl = selfUrl;
    }

    public String getSelfUrl() {
        return selfUrl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
