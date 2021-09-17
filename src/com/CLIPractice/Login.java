package com.CLIPractice;

import java.util.ArrayList;

public class Login extends BasicObject {

// Class Variables
    private static ArrayList<Login> all = new ArrayList<>();
    private static Login nullLogin = new Login();


    // Class Methods
    public static ArrayList<Login> all() { return Login.all; }
    public static Login nullLogin() { return Login.nullLogin; }

// Instance Variables
    private String id;
    private String password;
    private String username;
    private String userID;

// Constructors
    public Login(String password, String username, String userID) {
        this.id = Login.generateID();
        this.password = password;
        this.username = username;
        this.userID = userID;
        Login.all().add(this);
    }

    private Login() {
        this.id = "0";
        this.password = "";
        this.username = "";
        this.userID = "";
    }

// Readers
    public String id() { return this.id; }
    public String password() { return this.password; }
    public String username() { return this.username; }
    public String userID() { return this.userID; }


// Instance Methods

    public boolean valid(String password, String username) {
        return this.password.equals(password) && this.username.equals(username);
    }
}
