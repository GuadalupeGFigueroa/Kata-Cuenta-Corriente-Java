package dev.guadalupe.kata_cuenta_corriente_java.account;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {

    @Test
    void testInitialAccountStatus() {
        SavingAccount account = new SavingAccount(1L, 5000, 3.5f, 50);
        assertFalse(account.printAccountDetails().contains("Active")); 
    }

    @Test
    void testActivateAccountAfterDeposit() {
        SavingAccount account = new SavingAccount(1L, 5000, 3.5f, 50);
        account.deposit(6000); 
        assertTrue(account.printAccountDetails().contains("Active"));
    }

    @Test
    void testWithdrawFromInactiveAccount() {
        SavingAccount account = new SavingAccount(1L, 5000, 3.5f, 50);
        assertThrows(IllegalStateException.class, () -> account.withdraw(1000));
    }

    @Test
    void testWithdrawFromActiveAccount() {
        SavingAccount account = new SavingAccount(1L, 15000, 3.5f, 50);
        account.withdraw(5000);
        assertEquals(10000, account.getBalance(), 0.01f); 
    }

    @Test
    void testGenerateMonthlyStatementWithExtraWithdrawals() {
        SavingAccount account = new SavingAccount(1L, 20000, 3.5f, 50);
        account.withdraw(1000);
        account.withdraw(1000);
        account.withdraw(1000);
        account.withdraw(1000);
        account.withdraw(1000); 

        account.generateMonthlyStatement();
        assertEquals(19950.53, account.getBalance(), 0.01f); 
    }

    @Test
    void testPrintAccountDetails() {
        SavingAccount account = new SavingAccount(1L, 15000, 3.5f, 50);
        String details = account.printAccountDetails();
        assertTrue(details.contains("Account Status: Active"));
    }
}
