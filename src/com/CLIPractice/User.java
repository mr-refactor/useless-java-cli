package com.CLIPractice;

import java.util.ArrayList;

public class User extends BasicObject {
// Class Variables
    private static ArrayList<User> all = new ArrayList<User>();
    private static User nullUser = new User();

// Class Methods
    public static ArrayList<User> all() { return User.all; }
    public static User nullUser() { return User.nullUser; }

// Instance Variables
    private String email;
    private String name;
    private String id;

// Constructors
    private User() {
        this.name = "";
        this.id = "0";
    }

    public User(String name) {
        this.name = name;
        this.id = User.generateID();
        User.all().add(this);
    }

// Readers
    public String email() { return this.email; }
    public String id() { return this.id; }

    public Login login() {
        for (Login login : Login.all()) {
            if(login.userID() == this.id) {
                return login;
            }
        }
        return Login.nullLogin();
    }

    public String name() { return this.name; }

// Instance Methods
    public boolean addEmail(String email) {
        this.email = email;
        return this.email != ""; // check that this.email is an int and not null
    }

    public boolean createLogin(String password, String username) {
        Login login = new Login(password, username, this.id);
        if(login.isNotNull()) {
            return true;
        } else {
            return false;
        }
    }

}
