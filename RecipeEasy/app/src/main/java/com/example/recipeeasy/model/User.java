package com.example.recipeeasy.model;

public class User {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean passwordMatch(String password) {
        return this.password.compareTo(password) == 0;
    }
}
