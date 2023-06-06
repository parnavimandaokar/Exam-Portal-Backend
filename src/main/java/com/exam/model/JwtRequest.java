package com.exam.model;

public class JwtRequest {

    String Username;
    String password;

    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        Username = username;
        this.password = password;
    }


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
