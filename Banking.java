import java.util.Scanner;

// Base Class
class Account {
    protected String cust_name;
    protected int accno;
    protected double balance;
    Scanner sc = new Scanner(System.in);

    public Account(String cust_name, int accno, double balance) {
        this.cust_name = cust_name;
        this.accno = accno;
        this.balance = balance;
    }

    public void viewBalance() {
        System.out.println("Available Balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (balance < amount) {
            System.out.println("Insufficient Funds!");
        } else {
            balance -= amount;
            System.out.println("$" + amount + " withdrawn successfully!");
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("$" + amount + " deposited successfully!");
    }

    public void displayDetails() {
        System.out.println("\n----- Bank Account Details -----");
        System.out.println("Customer Name: " + cust_name);
        System.out.println("Account Number: " + accno);
        System.out.println("Available Balance: $" + balance);
    }
}

// Savings Account Subclass
class SavingsAccount extends Account {
    private final double interestRate = 0.06;

    public SavingsAccount(String cust_name, int accno, double balance) {
        super(cust_name, accno, balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Withdrawal failed! Overdraft not allowed in Savings Account.");
        } else {
            balance -= amount;
            System.out.println("$" + amount + " withdrawn successfully!");
        }
    }

    public void deposit(double amount) {
        double interest = amount * interestRate;
        balance += amount + interest;
        System.out.println("$" + amount + " deposited successfully with $" + interest + " interest added!");
    }
}

// Current Account Subclass
class CurrentAccount extends Account {
    private final double serviceCharge = 0.035;

    public CurrentAccount(String cust_name, int accno, double balance) {
        super(cust_name, accno, balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            double overdraft = amount - balance;
            double charge = overdraft * serviceCharge;
            balance -= (amount + charge);
            System.out.println("Overdraft! $" + amount + " withdrawn with service charge of $" + charge);
        } else {
            balance -= amount;
            System.out.println("$" + amount + " withdrawn successfully!");
        }
    }

    public void deposit(double amount) {
        balance += amount; // No interest for Current Account
        System.out.println("$" + amount + " deposited successfully! No interest added.");
    }
}

// Main Banking Class
public class Banking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account account = null;

        System.out.println("=== Welcome to Banking System ===");
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Account Number: ");
        int accno = sc.nextInt();
        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        System.out.println("\nChoose Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            account = new SavingsAccount(name, accno, balance);
        } else if (choice == 2) {
            account = new CurrentAccount(name, accno, balance);
        } else {
            System.out.println("Invalid choice! Exiting...");
            System.exit(0);
        }

        int option;
        do {
            System.out.println("\n=== Banking Menu ===");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Withdraw Amount");
            System.out.println("4. Display Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    account.viewBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    account.displayDetails();
                    break;
                case 5:
                    System.out.println("Thank you for using our banking system!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (option != 5);

        sc.close();
    }
}
