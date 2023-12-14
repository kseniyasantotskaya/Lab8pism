package models;

public class Operator extends User {
    private String operatorName;
    private String operatorLastName;

    public Operator(String operatorName, String operatorLastName, String username, String password) {
        super(username, password, false, true);
        this.operatorName = operatorName;
        this.operatorLastName = operatorLastName;
    }

    @Override
    public void getFunctionality() {
        System.out.println("Operator Functionality");
    }

    private void checkRecord(){

    }

    private void acceptRecord(){

    }

    private void editRecord(){

    }
}
