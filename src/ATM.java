import java.util.Scanner;

/**
 * ATM - A simple command-line ATM simulator.
 *
 * Features:
 *  - PIN login with max 3 attempts (account locks on failure)
 *  - Deposit money
 *  - Withdraw money
 *  - Check balance
 *  - View transaction history
 *  - Logout
 *
 * Author: SimonZockt1
 */
public class ATM {

    private static final int MAX_PIN_ATTEMPTS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- Create a demo account ---
        BankAccount account = new BankAccount("Simon", 1234, 500.00);

        System.out.println("==========================================");
        System.out.println("       Welcome to JavaBank ATM            ");
        System.out.println("==========================================");

        // --- PIN Login ---
        boolean loggedIn = false;
        int attempts = 0;

        while (!loggedIn && attempts < MAX_PIN_ATTEMPTS) {
            System.out.print("\nPlease enter your PIN: ");

            // Make sure the user actually enters a number
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a numeric PIN.");
                scanner.next(); // discard invalid input
                attempts++;
                continue;
            }

            int enteredPin = scanner.nextInt();
            attempts++;

            if (account.checkPin(enteredPin)) {
                loggedIn = true;
                System.out.println("\nLogin successful. Welcome, " + account.getHolderName() + "!");
            } else {
                int remaining = MAX_PIN_ATTEMPTS - attempts;
                if (remaining > 0) {
                    System.out.println("Wrong PIN. " + remaining + " attempt(s) remaining.");
                } else {
                    System.out.println("Too many wrong attempts. Your card has been blocked.");
                }
            }
        }

        if (!loggedIn) {
            scanner.close();
            return;
        }

        // --- Main Menu Loop ---
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Check balance
                    System.out.printf("%nCurrent balance: %.2f EUR%n", account.getBalance());
                    break;

                case 2:
                    // Deposit
                    System.out.print("Enter amount to deposit: ");
                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid amount.");
                        scanner.next();
                        break;
                    }
                    double depositAmount = scanner.nextDouble();
                    if (account.deposit(depositAmount)) {
                        System.out.printf("Successfully deposited %.2f EUR.%n", depositAmount);
                        System.out.printf("New balance: %.2f EUR%n", account.getBalance());
                    } else {
                        System.out.println("Invalid amount. Deposit must be greater than 0.");
                    }
                    break;

                case 3:
                    // Withdraw
                    System.out.print("Enter amount to withdraw: ");
                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid amount.");
                        scanner.next();
                        break;
                    }
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.printf("Please take your cash: %.2f EUR%n", withdrawAmount);
                        System.out.printf("Remaining balance: %.2f EUR%n", account.getBalance());
                    } else {
                        if (withdrawAmount <= 0) {
                            System.out.println("Invalid amount. Withdrawal must be greater than 0.");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    }
                    break;

                case 4:
                    // Transaction history
                    account.printTransactionHistory();
                    break;

                case 5:
                    // Logout
                    System.out.println("\nThank you for using JavaBank ATM. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1-5.");
            }
        }

        scanner.close();
    }

    /**
     * Prints the ATM main menu.
     */
    private static void printMenu() {
        System.out.println("\n------------------------------------------");
        System.out.println("  1. Check Balance");
        System.out.println("  2. Deposit");
        System.out.println("  3. Withdraw");
        System.out.println("  4. Transaction History");
        System.out.println("  5. Logout");
        System.out.println("------------------------------------------");
    }
}
