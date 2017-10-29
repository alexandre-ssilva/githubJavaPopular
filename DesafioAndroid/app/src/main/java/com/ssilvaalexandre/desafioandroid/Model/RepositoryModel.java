package com.ssilvaalexandre.desafioandroid.Model;

/**
 * Created by ssilvaalexandre on 20/10/17.
 * Nexaas All rights reserved
 */

public class RepositoryModel {

    private String name, description, userName, fullName, pullsUrl;
    private int forksNumber, starsNumber, id;
    private UserModel owner;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getForksNumber() {
        return forksNumber;
    }

    public void setForksNumber(int forksNumber) {
        this.forksNumber = forksNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setStarsNumber(int starsNumber) {
        this.starsNumber = starsNumber;
    }

    public int getStarsNumber() {
        return starsNumber;
    }

    public void setPullsUrl(String pullsUrl) {
        this.pullsUrl = pullsUrl;
    }

    public String getPullsUrl() {
        return pullsUrl;
    }

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
}
