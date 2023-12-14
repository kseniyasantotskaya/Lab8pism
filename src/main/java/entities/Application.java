package entities;

import models.User;
import models.Administrator;
import models.Client;
import models.Operator;
import patterns.UserFactory;

import java.util.HashMap;
import java.util.Map;

public class Application {
    private static Map<String, User> usersDatabase = new HashMap<>();
    public static User currentUser;

    public static boolean registerUser(String username, String password, boolean isAdmin, boolean isOperator) {
        if (usersDatabase.containsKey(username)) {
            System.out.println("User with this username already exists.");
            return false;
        }

        User newUser;
        if (isAdmin) {
            newUser = UserFactory.createAdministrator(username, password);
        } else if (isOperator){
            newUser = UserFactory.createOperator("operator", "operator", username, password);
        } else {
            newUser = UserFactory.createClient("John", "Doe",12343, username, password);
        }

        usersDatabase.put(username, newUser);
        newUser.register();
        return true;
    }

    public static boolean loginUser(String username, String password) {
        if (usersDatabase.containsKey(username)) {
            User user = usersDatabase.get(username);
            if (user.login(password)) {
                currentUser = user;
                System.out.println("Login successful.");
                return true;
            }
        }
        System.out.println("Login failed.");
        return false;
    }

    public static void displayFunctionality() {
        if (currentUser == null) {
            System.out.println("Please login to access functionality.");
            return;
        }

        System.out.println("Available functionality for user " + currentUser.getUsername() + ":");

        currentUser.getFunctionality();

        if (currentUser instanceof Administrator) {
            System.out.println("Additional Admin Functionality");
        }
        if (currentUser instanceof Operator) {
            System.out.println("Additional Operator Functionality");
        }
        if (currentUser instanceof Client) {
            System.out.println("Additional Client Functionality");
        }
    }

    public static User getUser(String username) {
        return usersDatabase.get(username);
    }

}