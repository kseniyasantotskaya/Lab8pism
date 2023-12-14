package patterns;

import models.User;

public class ReviewVisitor implements Visitor {
    @Override
    public void visit(User user) {
        System.out.println("Review added for user " + user.getUsername());
    }
}