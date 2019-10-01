package com.udemy.backendninja.Model;

public class UserCredential {

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserCredential{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserCredential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserCredential(){
    }

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
