package com.example.recipeeasy.model;

import java.util.HashMap;

public class UserService {
    private static UserService instance = null;

    private HashMap<String, User> users;
    private int autoIncrement;
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    private UserService() {
        users = new HashMap<>();
        autoIncrement = 0;

        currentUser = null;

        // seeding data
        users.put("a", new User(autoIncrement, "a", "a"));
        autoIncrement++;

        users.put("b", new User(autoIncrement, "b", "b"));
        autoIncrement++;
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }

        return instance;
    }

    public boolean logIn(String username, String password) {
        User user = users.get(username);

        if (user != null && user.passwordMatch(password)) {
            currentUser = user;

            return true;
        } else {
            return false;
        }
    }

    public boolean signUp(String username, String password) {
        if (username.isEmpty() || password.isEmpty() || users.containsKey(username)) {
            return false;
        } else {
            User newUser = new User(autoIncrement, username, password);

            users.put(username, newUser);
            autoIncrement++;

            currentUser = newUser;

            return true;
        }
    }

    public void logOut() {
        currentUser = null;
    }
}
