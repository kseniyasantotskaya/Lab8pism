package models;

import entities.Record;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private String name;
    private String lastName;
    private int phoneNumber;
    private List<Record> reservations;

    public Client(String name, String lastName, int phoneNumber, String username, String password) {
        super(username, password, false, false);
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.reservations = new ArrayList<>();
    }

    @Override
    public void getFunctionality() {
        System.out.println("Client Functionality");
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void reserveRecord(Record record) {
        // Проверяем, не зарезервировано ли уже это время
        if (reservations.stream().anyMatch(r -> r.getRecordTime().equals(record.getRecordTime()))) {
            throw new IllegalStateException("This time slot is already reserved.");
        }

        // Если время не зарезервировано, добавляем запись в список резерваций
        reservations.add(record);
        System.out.println("Reservation successful for time: " + record.getRecordTime());
    }
}