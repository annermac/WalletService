package application;

import domain.Transaction;
import domain.User;
import infrastructure.InsufficientFundsException;
import infrastructure.Logging;

import java.util.*;

/**
 * Класс AccountService реализует консольный интерфейс для взаимодействия с игроками
 */
public class AccountService {
    public static Map<String, User> users = new HashMap<>();
    public static List<Logging> loggingList = new ArrayList<>();
    public static User currentUser = null;
    public static User user = null;
    public static int option;
    public static String login;
    public static String password;
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Метод используется для показа меню игрокам
     */
    public static void showMenu() {
        while (true) {
            printStartMenu();
            scanNumber();
            switch (option) {
                case 1 -> {
                    registerUser();
                }
                case 2 -> {
                    authorizeUser();
                    makesChoiceAuthorizedUser();
                }
                case 3 -> {
                    printLogging();
                }
                case 4 -> {
                    return;
                }
                default -> {
                    System.out.println("Wrong number!");
                }
            }
        }
    }

    /**
     * Метод выводит опции меню
     */
    public static void printStartMenu() {
        System.out.println("~~~~~~~~~~~~~~~~");
        System.out.println("Select an option: ");
        System.out.println("1. Player Registration");
        System.out.println("2. Player authorization");
        System.out.println("3. View logs");
        System.out.println("4. End the program");
        System.out.println("~~~~~~~~~~~~~~~~");
    }

    /**
     * Метод сканирует с клавиатуры число
     */
    public static void scanNumber() {
        option = Character.getNumericValue(scanner.next().charAt(0));
    }

    /**
     * Метод регистрирует игрока, используя логин и пароль
     */
    public static void registerUser() {
        System.out.println("Enter your username");
        login = scanner.next();
        System.out.println("Enter the password");
        password = scanner.next();
        user = new User(login, password);
        users.put(login, user);
        System.out.println("You have successfully registered!");
        addLogging("Player " + login + " registered");
    }

    /**
     * Метод добавляет логирование действия игрока
     *
     * @param message это сообщение для записи лога
     */
    public static void addLogging(String message) {
        Logging logging = new Logging(message);
        loggingList.add(logging);
    }

    /**
     * Метод авторизирует игрока по логину и паролю
     */
    public static void authorizeUser() {
        System.out.println("Enter your username");
        login = scanner.next();
        System.out.println("Enter the password");
        password = scanner.next();
        addLogging("Player " + login + " trying to log in");
        verificationUser();
    }

    /**
     * Метод проверяет есть ли в системе такой логин и
     * соответсвует ли введенный пароль логину
     */
    public static void verificationUser() {
        if (users.containsKey(login)) {
            if (!Objects.equals(users.get(login).getPassword(), password)) {
                System.out.println("Wrong password!");
                addLogging("Player " + login + " entered an incorrect password");
            }
        } else {
            System.out.println("There is no such login!");
            addLogging("The player " + login + " does not exist in the system");
        }
    }

    /**
     * Метод используется для показа меню авторизированному игроку
     */
    public static void makesChoiceAuthorizedUser() {
        while (true) {
            printMenuForAuthorizedUser();
            currentUser = users.get(login);
            scanNumber();
            switch (option) {
                case 1 -> {
                    showBalance();
                }
                case 2 -> {
                    depositBalance();
                }
                case 3 -> {
                    withdrawBalance();
                }
                case 4 -> {
                    printHistory();
                }
                case 5 -> {
                    logOut();
                    return;
                }
            }
        }
    }

    /**
     * Метод выводит на экран список логов
     */
    public static void printLogging() {
        if (loggingList.isEmpty()) {
            System.out.println("Logs are empty");
        } else {
            for (Logging logging : loggingList) {
                System.out.println(logging);
            }
        }
    }

    /**
     * Метод выводит опции меню авторизированному игроку
     */
    public static void printMenuForAuthorizedUser() {
        System.out.println("~~~~~~~~~~~~~~~~");
        System.out.println("1. View the balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. View history");
        System.out.println("5. Exit");
        System.out.println("~~~~~~~~~~~~~~~~");
    }

    /**
     * Метод выводит баланс счета игрока
     */
    public static void showBalance() {
        double balance = currentUser.getAccount().getBalance();
        System.out.println("Balance: " + balance);
        addLogging("Player " + login + " requested balance");
    }

    /**
     * Метод вносит сумму на счет игрока
     */
    public static void depositBalance() {
        System.out.println("Enter the deposit amount");
        if (currentUser != null) {
            double amount = scanner.nextDouble();
            currentUser.getAccount().deposit(amount);
            addLogging("Player " + login + " deposit " + amount);
        }

    }

    /**
     * Метод снимает сумму со счета игрока
     */
    public static void withdrawBalance() {
        System.out.println("Enter the withdraw amount");
        double amount = scanner.nextDouble();
        try {
            currentUser.getAccount().withdraw(amount);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
        addLogging("Player " + login + " withdraw " + amount);
    }

    /**
     * Метод выводит список транзакций
     */
    public static void printHistory() {
        for (Transaction transaction : user.getAccount().getTransactions().values()) {
            System.out.println(transaction);
        }
        addLogging("Player " + login + " requested transaction history");
    }

    /**
     * Метод выводит из системы игрока
     */
    public static void logOut() {
        currentUser = null;
        System.out.println("You are logged out");
        addLogging("Player " + login + " logged out");
    }
}
