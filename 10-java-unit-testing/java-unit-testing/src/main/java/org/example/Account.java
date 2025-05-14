package org.example;

/**
 * A simple account class.
 */
public class Account {
    /**
     * The balance of the account.
     */
    private double balance;

    /**
     * Constructs an account with a balance.
     *
     * @param balance The initial balance.
     */
    public Account(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the balance of the account.
     *
     * @return The balance of the account.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposits money into the account.
     *
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Withdraws money from the account.  This method should fail if the amount
     * to withdraw is greater than the balance.
     *
     * @param amount The amount to withdraw.
     * @return True if the withdrawal was successful, false otherwise.
     */
    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

}
