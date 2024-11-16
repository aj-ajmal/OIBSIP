import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Account {
    private double balance;
    private final ArrayList<String> transactionHistory;
    public Account() {
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: Rs." + amount);
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdraw: Rs." + amount);
            System.out.println("Amount withdrawn successfully.");
        } else {
            System.out.println("Invalid or insufficient balance for withdrawal.");
        }
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}
class User {
    private final String userId;
    private final String userPin;
    private final Account account;
    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.account = new Account();
    }

    public boolean validateCredentials(String enteredUserId, String enteredPin) {
        return userId.equals(enteredUserId) && userPin.equals(enteredPin);
    }

    public Account getAccount() {
        return account;
    }
}

class ATM {
    private final User user;

    public ATM(User user) {
        this.user = user;
    }

    public void showMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            do {
                System.out.println("\nATM Menu:");
                System.out.println("1. Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Check Balance");
                System.out.println("6. Quit");
                System.out.print("Choose an option: ");
                choice = getValidIntInput(scanner);

                switch (choice) {
                    case 1 -> user.getAccount().showTransactionHistory();
                    case 2 -> performWithdrawal(scanner);
                    case 3 -> performDeposit(scanner);
                    case 4 -> performTransfer(scanner);
                    case 5 -> checkBalance();
                    case 6 -> System.out.println("Thank you for using the ATM. Goodbye!");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 6);
        }
    }

    private void performWithdrawal(Scanner scanner) {
        System.out.print("Enter amount to withdraw: Rs.");
        double amount = getValidDoubleInput(scanner);
        user.getAccount().withdraw(amount);
    }

    private void performDeposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: Rs.");
        double amount = getValidDoubleInput(scanner);
        user.getAccount().deposit(amount);
    }

    private void performTransfer(Scanner scanner) {
        System.out.print("Enter amount to transfer: Rs.");
        double amount = getValidDoubleInput(scanner);
        System.out.print("Enter recipient user ID (simulated): ");
        String recipientId = scanner.next();

        if (amount > 0 && amount <= user.getAccount().getBalance()) {
            user.getAccount().withdraw(amount);
            user.getAccount().addTransaction("Transferred: Rs." + amount + " to user " + recipientId);
            System.out.println("Transferred Rs." + amount + " to user " + recipientId);
        } else {
            System.out.println("Invalid or insufficient balance for transfer.");
        }
    }

    private void checkBalance() {
        double balance = user.getAccount().getBalance();
        System.out.printf("Your current balance is: Rs.%.2f%n", balance);
    }

    private int getValidIntInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
    }

    private double getValidDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next();
            }
        }
    }
}

class ATMSystem {
    private final Scanner scanner;
    private final User user;

    public ATMSystem() {
        this.scanner = new Scanner(System.in);
        this.user = new User("Ajmal17", "2006");
    }

    public void start() {
        System.out.print("Enter User ID: ");
        String userId = scanner.next();
        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        if (user.validateCredentials(userId, pin)) {
            ATM atm = new ATM(user);
            atm.showMenu();
        } else {
            System.out.println("Invalid User. Access denied.");
        }
    }
}

public class Banking {
    public static void main(String[] args) {
        ATMSystem atmSystem = new ATMSystem();
        atmSystem.start();
    }
}
