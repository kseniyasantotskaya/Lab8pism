package models;

public class Administrator extends User{
    private int adminID;
    public Administrator(String username, String password) {
        super(username, password, true, false);
    }

    @Override
    public void getFunctionality() {
        System.out.println("Administrator Functionality");
    }

    private void deleteRecord(){

    }

    private void editRecord(){

    }

}
