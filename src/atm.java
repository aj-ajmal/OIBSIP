import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account() {
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    // New method to set balance directly
    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
            transactionHistory.add("Balance set to: $" + balance);
            System.out.println("Balance updated successfully.");
        } else {
            System.out.println("Invalid balance amount.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            System.out.println("Withdrawn successfully.");
        } else {
            System.out.println("Invalid or insufficient funds for withdrawal.");
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
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Set Balance"); // New menu option for setting balance
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> user.getAccount().showTransactionHistory();
                case 2 -> performWithdrawal(scanner);
                case 3 -> performDeposit(scanner);
                case 4 -> performTransfer(scanner);
                case 5 -> setBalance(scanner); // New method to set balance
                case 6 -> System.out.println("Thank you for using the ATM. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private void performWithdrawal(Scanner scanner) {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        user.getAccount().withdraw(amount);
    }

    private void performDeposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        user.getAccount().deposit(amount);
    }

    private void performTransfer(Scanner scanner) {
        System.out.print("Enter amount to transfer: $");
        double amount = scanner.nextDouble();
        System.out.print("Enter recipient user ID: ");
        String recipientId = scanner.next();

        if (amount > 0 && amount <= user.getAccount().getBalance()) {
            user.getAccount().withdraw(amount);
            user.getAccount().addTransaction("Transferred: $" + amount + " to " + recipientId);
            System.out.println("Transferred $" + amount + " to user " + recipientId);
        } else {
            System.out.println("Invalid or insufficient funds for transfer.");
        }
    }

    private void setBalance(Scanner scanner) {
        System.out.print("Enter new balance amount: $");
        double newBalance = scanner.nextDouble();
        user.getAccount().setBalance(newBalance);
    }
}

class ATMSystem {
    private final Scanner scanner;
    private final User user;

    public ATMSystem() {
        this.scanner = new Scanner(System.in);
        this.user = new User("user123", "1234"); // Predefined user credentials
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
            System.out.println("Invalid credentials. Access denied.");
        }
    }
}

public class atm {
    public static void main(String[] args) {
        ATMSystem atmSystem =   new ATMSystem();
        atmSystem.start(); //completed

    }
}
