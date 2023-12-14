package patterns;

import models.User;

public interface Visitor {
    void visit(User user);
}

