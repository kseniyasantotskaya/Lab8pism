package main;

import entities.Application;
import entities.Record;
import models.Client;
import patterns.ReviewComponent;
import patterns.ReviewVisitor;

import java.util.Scanner;

import static entities.Application.currentUser;

public class Main {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        boolean isRunning = true;
        Application.registerUser("admin", "admin", true, false);
        Application.registerUser("operator", "operator", false, true);


        while (isRunning) {
            System.out.println("Главное меню:");
            System.out.println("1. Войти в аккаунт");
            System.out.println("2. Зарегистрироваться");
            System.out.println("3. Выйти из приложения");
            System.out.println("4. Бронирование поездки");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    loginMenu();
                    break;
                case 2:
                    registerMenu();
                    break;
                case 3:
                    isRunning = false;
                    System.out.println("Выход из приложения.");
                    break;
                case 4:
                    newRecordMenu();
                    break;
                default:
                    System.out.println("Некорректный ввод. Пожалуйста, выберите существующую опцию.");
            }
        }
    }

    private static void loginMenu() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (Application.loginUser(username, password)) {
            System.out.println("Вход выполнен успешно.");
            Application.displayFunctionality();
        } else {
            System.out.println("Вход не выполнен. Переход к регистрации.");
            registerMenu();
        }
    }

    private static void registerMenu() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        boolean isAdmin = false;
        boolean isBarber = false;

        if (Application.registerUser(username, password, isAdmin, isBarber)) {
            System.out.println("Регистрация выполнена успешно. Вход в аккаунт.");
            Application.loginUser(username, password);
            Application.displayFunctionality();
        } else {
            System.out.println("Регистрация не выполнена. Возвращение в главное меню.");
        }
    }
    private static void newRecordMenu(){
        if (Application.getUser(currentUser.getUsername()) instanceof Client) {
            System.out.print("Выберите дату и время для бронирования (например, 2023-01-01 10:00): ");
            String dateTime = scanner.nextLine();
            System.out.print("Выберите город ОТКУДА едете: ");
            String pointA = scanner.nextLine();
            System.out.print("Выберите город, КУДА едете: ");
            String pointB = scanner.nextLine();
            Client client = (Client) currentUser;
            Record record = patterns.BookingFacade.makeReservation(client, dateTime, pointA, pointB);

            if (record != null) {
                System.out.println("Бронирование выполнено успешно.");
                System.out.println("Детали бронирования:");
                System.out.println("ID записи: " + record.getRecordID());
                System.out.println("Дата и время: " + record.getRecordTime());
                System.out.println("Отправление из: " + record.getPointA());
                System.out.println("Прибытие в: " + record.getPointB());
            } else {
                System.out.println("Бронирование не выполнено. Возможно, время занято.");
            }
        } else {
            System.out.println("Недостаточно прав для выполнения этой операции.");
        }
    }
}
