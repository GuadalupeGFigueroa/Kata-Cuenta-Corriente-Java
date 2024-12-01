package dev.guadalupe.kata_cuenta_corriente_java.account;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {

    @Test
    void testWithdrawWithoutOverdraft() {
        CheckingAccount account = new CheckingAccount(1L, 5000, 3.5f, 50);
        account.withdraw(3000); 
        assertEquals(2000, account.getBalance(), 0.01f);
        assertEquals(0, account.getOverdraft(), 0.01f);
    }

    @Test
    void testWithdrawWithOverdraft() {
        CheckingAccount account = new CheckingAccount(1L, 5000, 3.5f, 50);
        account.withdraw(7000); 
        assertEquals(0, account.getBalance(), 0.01f); 
        assertEquals(2000, account.getOverdraft(), 0.01f); 
    }

    @Test
    void testDepositWithoutOverdraft() {
        CheckingAccount account = new CheckingAccount(1L, 5000, 3.5f, 50);
        account.deposit(2000); 
        assertEquals(7000, account.getBalance(), 0.01f);
        assertEquals(0, account.getOverdraft(), 0.01f);
    }

    @Test
    void testDepositWithOverdraft() {
        CheckingAccount account = new CheckingAccount(1L, 0, 3.5f, 50);
        account.withdraw(2000); 
        account.deposit(1500); 
        assertEquals(0, account.getBalance(), 0.01f); 
        assertEquals(500, account.getOverdraft(), 0.01f); 
    }

    @Test
    void testGenerateMonthlyStatement() {
        CheckingAccount account = new CheckingAccount(1L, 5000, 3.5f, 50);
        account.generateMonthlyStatement();
        assertEquals(4950.15, account.getBalance(), 0.01f); 
    }

    @Test
    void testPrintAccountDetails() {
        CheckingAccount account = new CheckingAccount(1L, 5000, 3.5f, 50);
        String details = account.printAccountDetails();
        assertTrue(details.contains("Overdraft: $0.0"));
    }
}
