package com.galvanize.crudapi.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Authentication {

    public Authentication(User user, boolean authenticated) {
        this.user = user;
        this.authenticated = authenticated;
    }

    public Authentication(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    private User user;
    private boolean authenticated;
}
