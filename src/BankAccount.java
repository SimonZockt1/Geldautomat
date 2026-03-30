import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank account with a holder name, PIN, and balance.
 * Stores a history of all transactions.
 */
public class BankAccount {

    private String holderName;
    private int pin;
    private double balance;
    private List<String> transactionHistory;

    /**
     * Creates a new BankAccount.
     *
     * @param holderName The name of the account holder.
     * @param pin        The 4-digit PIN for this account.
     * @param balance    The starting balance.
     */
    public BankAccount(String holderName, int pin, double balance) {
        this.holderName = holderName;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account opened with initial balance: " + formatAmount(balance));
    }

    /**
     * Checks whether the given PIN matches this account's PIN.
     *
     * @param inputPin The PIN entered by the user.
     * @return true if the PIN is correct, false otherwise.
     */
    public boolean checkPin(int inputPin) {
        return this.pin == inputPin;
    }

    /**
     * Returns the current balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns the account holder's name.
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * Deposits an amount into the account.
     *
     * @param amount The amount to deposit. Must be greater than 0.
     * @return true if the deposit was successful, false if the amount was invalid.
     */
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        transactionHistory.add("Deposit:    +" + formatAmount(amount) + "  |  Balance: " + formatAmount(balance));
        return true;
    }

    /**
     * Withdraws an amount from the account.
     *
     * @param amount The amount to withdraw. Must be greater than 0 and not exceed the balance.
     * @return true if the withdrawal was successful, false otherwise.
     */
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        transactionHistory.add("Withdrawal: -" + formatAmount(amount) + "  |  Balance: " + formatAmount(balance));
        return true;
    }

    /**
     * Prints the full transaction history to the console.
     */
    public void printTransactionHistory() {
        System.out.println("\n--- Transaction History for " + holderName + " ---");
        for (String entry : transactionHistory) {
            System.out.println("  " + entry);
        }
        System.out.println("------------------------------------------");
    }

    /**
     * Formats a double amount as a Euro string with 2 decimal places.
     */
    private String formatAmount(double amount) {
        return String.format("%.2f EUR", amount);
    }
}
