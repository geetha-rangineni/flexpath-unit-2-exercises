package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Account class.
 */
public class AccountTests {

    @Test
    public void testInitialBalance() {
        Account account = new Account(100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    public void testDeposit() {
        Account account = new Account(50.0);
        account.deposit(25.0);
        assertEquals(75.0, account.getBalance());
    }

    @Test
    public void testWithdrawSuccess() {
        Account account = new Account(100.0);
        boolean success = account.withdraw(40.0);
        assertTrue(success);
        assertEquals(60.0, account.getBalance());
    }

    @Test
    public void testWithdrawFailure() {
        Account account = new Account(30.0);
        boolean success = account.withdraw(50.0);
        assertFalse(success);
        assertEquals(30.0, account.getBalance());
    }
}
