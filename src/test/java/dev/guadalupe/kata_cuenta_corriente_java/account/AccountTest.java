package dev.guadalupe.kata_cuenta_corriente_java.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
       
        account = new Account(1L, 5000f, 5.0f, 50f); 
    }

    @Test
    void testConstructorAndGetters() {
       
        assertEquals(1L, account.getId());
        assertEquals(5000f, account.getBalance());
        assertEquals(0, account.getNumDeposits());
        assertEquals(0, account.getNumWithdrawals());
        assertEquals(5.0f, account.getAnnualRate());
        assertEquals(50f, account.getMonthlyFee());
    }

    @Test
    void testDeposit() {
        
        account.deposit(1000f);
        assertEquals(6000f, account.getBalance());
        assertEquals(1, account.getNumDeposits());

        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-100f);
        });
        assertEquals("The deposit amount must be greater than zero.", exception.getMessage());
    }

    @Test
    void testWithdraw() {
        
        account.withdraw(1000f);
        assertEquals(4000f, account.getBalance());
        assertEquals(1, account.getNumWithdrawals());

        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(10000f);
        });
        assertEquals("Insufficient balance for this withdrawal.", exception.getMessage());
    }

    @Test
    void testCalculateMonthlyInterest() {
        
        account.calculateMonthlyInterest();
        assertEquals(5020.83f, account.getBalance(), 0.01f); 
    }

    @Test
    void testGenerateMonthlyStatement() {
        
        account.generateMonthlyStatement();
        assertEquals(4970.83f, account.getBalance(), 0.01f); // Saldo después de restar la comisión mensual y aplicar el interés
    }

    @Test
    void testPrintAccountDetails() {
       
        String details = account.printAccountDetails();
        assertThat(details, containsString("Account ID: 1"));
        assertThat(details, containsString("Balance: $5000.0"));
        assertThat(details, containsString("Annual Rate: 5.0%"));
        assertThat(details, containsString("Monthly Fee: $50.0"));
    }
}

    
