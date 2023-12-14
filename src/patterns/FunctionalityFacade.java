package patterns;

import models.*;
import models.User;

public class FunctionalityFacade {
    public static void displayFunctionality(User user) {
        System.out.println("Available functionality for user " + user.getUsername() + ":");

        user.getFunctionality();

        if (user instanceof Administrator) {
            System.out.println("Additional Admin Functionality");
        }
        if (user instanceof Operator) {
            System.out.println("Additional Operator Functionality");
        }
        if (user instanceof Client) {
            System.out.println("Additional Client Functionality");
        }
    }
}