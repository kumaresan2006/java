import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class User {
    private String name;
    private String address;
    private String contactInfo;
    private String accountNumber;
    private double accountBalance;

    public User(String name, String address, String contactInfo, String accountNumber, double initialDeposit) {
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
        this.accountNumber = accountNumber;
        this.accountBalance = initialDeposit;
    }

    // Getters and setters

    public void deposit(double amount) {
        accountBalance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > accountBalance) {
            System.out.println("Insufficient funds!");
            return false;
        }
        accountBalance -= amount;
        return true;
    }

    public void transfer(User recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
        }
    }

    public String generateAccountStatement() {
        // Generate and return the account statement as a string
        return "Account Statement for " + name + "\n" +
                "Transaction History:\n" +
                "Date\t\tAmount\t\tBalance\n" +
                "------------------------------------\n" +
                // Add transaction details here
                "------------------------------------";
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}

public class BankingSystemPrototype {
    private static Map<String, User> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Banking System Prototype");
            System.out.println("1. User Registration");
            System.out.println("2. Account Management");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Fund Transfer");
            System.out.println("6. Account Statements");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    performUserRegistration();
                    break;
                case 2:
                    performAccountManagement();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performWithdrawal();
                    break;
                case 5:
                    performFundTransfer();
                    break;
                case 6:
                    performAccountStatements();
                    break;
                case 0:
                    System.out.println("Exiting the Banking System Prototype. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void performUserRegistration() {
        System.out.println("User Registration");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your contact information: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        String accountNumber = generateAccountNumber();
        User newUser = new User(name, address, contactInfo, accountNumber, initialDeposit);
        users.put(accountNumber, newUser);

        System.out.println("Registration successful!");
        System.out.println("Your account number is: " + accountNumber);
    }

    private static void performAccountManagement() {
        System.out.println("Account Management");
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (users.containsKey(accountNumber)) {
            User user = users.get(accountNumber);
            System.out.println("Account Information:\n" + user);

            // Additional account management options can be added here
        } else {
            System.out.println("Invalid account number. Please try again.");
        }
    }

    private static void performDeposit() {
        // Implement deposit functionality
    }

    private static void performWithdrawal() {
        // Implement withdrawal functionality
    }

    private static void performFundTransfer() {
        // Implement fund transfer functionality
    }

    private static void performAccountStatements() {
        System.out.println("Account Statements");
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (users.containsKey(accountNumber)) {
            User user = users.get(accountNumber);
            String accountStatement = user.generateAccountStatement();
            System.out.println(accountStatement);
        } else {
            System.out.println("Invalid account number. Please try again.");
        }
    }

    private static String generateAccountNumber() {
        // Generate a unique account number (e.g., a random string or incremental number)
        return "ACC" + System.currentTimeMillis();
    }
}
