package com.CLIPractice;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.InputMismatchException;

public class App {

    private Scanner scanner;
    private User currentUser;
    private String invalidInputMessage;

    public static void start() {
        App app = new App();
            app.welcome();
            app.createUser();
            app.addUserEmail();
            app.addUserLogin();
            app.logIn();
            app.goodbye();
    }

    private App() {
        this.scanner = new Scanner(System.in);
    }

    private void addUserEmail() {
        String email = this.getInput("email");
        this.currentUser.addEmail(email);
    }

    private void addUserLogin() {
        String password = this.getInput("password");
        String username = this.getInput("username");
        new Login(password, username, this.currentUser.id());
        if( this.currentUser.login().password().equals(password) &&
                this.currentUser.login().username().equals(username) ) {
            System.out.println("Your login credentials have been saved!");
        }
    }

    private void createUser() {
        String name = this.getInput("name");
        this.currentUser = new User(name);
        System.out.println("Hi " + currentUser.name() + "!");
    }

    private void exit() {
        System.exit(0);
    }

    private String getInput(String type) {
        String input = "";
        while( input == "") {
            System.out.println("Please enter your " + type + ":");
            try {
                input = this.scanner.nextLine();
                if (input.equals("exit"))
                    exit();
                if (notValid(type, input)) {
                    input = ""; // reset to continue looping until valid
                    throw new InputMismatchException("Invalid Input: " + this.invalidInputMessage); //REFACTOR to include specific error
                }
            } catch (InputMismatchException e) {
                if(e.getMessage() != null) {
                    System.out.println(e.getMessage());
                } else {
                    System.out.println("Invalid Input.");
                }
                this.invalidInputMessage = ""; // reset the invalidInputMessage just in case
            }
        }
        return input;
    }

    private void goodbye() {
        System.out.println("Welp, that's it. There's really nothing to do here.");
        System.out.println("Sorry to waste your time " + this.currentUser.name());
        System.out.println("Goodbye!");
        System.exit(0);
    }

    private void logIn() {
        System.out.println("Welcome " + this.currentUser.name() + " please enter your credentials to log in.");
        String password = "";
        String username = "";
        while (!this.currentUser.login().valid(password, username)) {
            username = this.getInput("username");
            password = this.getInput("password");
        }
        System.out.println("You've successfully logged in!");
    }

    private boolean notValid(String type, String input) {
        String regex;
        switch(type) {
            case "name":
                regex = "[^a-z]";
                this.invalidInputMessage = "Name must only contain Alpha characters.";
                break;
            case "age":
                regex = "[^0-9]";
                this.invalidInputMessage = "Age must only contain Numeric characters.";
                break;
            case "email":
                regex = "[^a-z0-9@.]"; // REFACTOR for better validation. Only checks that it contains no other chars than alpha num @ and .
                this.invalidInputMessage = "Email must be a valid.";
                break;
            case "password":
                if (input.length() < 8 || input.length() > 24) {
                    this.invalidInputMessage = "Password must be between 8 - 24 characters.";
                    return true;
                }
                regex = "[^a-z0-9@._*!$#+?]";
                this.invalidInputMessage = "Only Alphanumeric and special characters (@._*!$#+?) allowed in password";
                break;
            case "username":
                if (input.length() < 6 || input.length() > 12) {
                    this.invalidInputMessage = "Password must be between 6 - 12 characters.";
                    return true;
                }
                regex = "[^a-z0-9@._*!$#+?]";
                this.invalidInputMessage = "Only Alphanumeric and special characters (@._*!$#+?) allowed in username.";
                break;
            default:
                regex = ""; // REFACTOR research regex that would effectively invalidate everything
        }
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        return m.find();
    }

    private void welcome() {
        System.out.println("Welcome to the User Creator! Type 'exit' at any time to exit.");
    }
}
