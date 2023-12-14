package patterns;
import models.Client;
import models.Operator;
import models.Administrator;

public class UserFactory {
    public static Client createClient(String name, String lastName, int phoneNumber, String username, String password) {
        return new Client(name, lastName, phoneNumber, username, password);
    }

    public static Operator createOperator(String operatorName, String operatorLastName, String username, String password) {
        return new Operator(operatorName, operatorLastName, username, password);
    }

    public static Administrator createAdministrator(String username, String password) {
        return new Administrator(username, password);
    }
}
