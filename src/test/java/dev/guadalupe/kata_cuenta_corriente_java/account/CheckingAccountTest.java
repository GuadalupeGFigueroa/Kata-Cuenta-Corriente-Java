package dev.guadalupe.kata_cuenta_corriente_java.account;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {

    @Test
    void testWithdrawWithoutOverdraft() {
        CheckingAccount account = new CheckingAccount(1L, 5000, 3.5f, 50);
        account.withdraw(3000); // Retirar una cantidad menor al saldo disponible
        assertEquals(2000, account.getBalance(), 0.01f);
        assertEquals(0, account.getOverdraft(), 0.01f);
    }

    @Test
    void testWithdrawWithOverdraft() {
        CheckingAccount account = new CheckingAccount(1L, 5000, 3.5f, 50);
        account.withdraw(7000); // Retirar una cantidad mayor al saldo
        assertEquals(0, account.getBalance(), 0.01f); // Saldo debe quedar en 0
        assertEquals(2000, account.getOverdraft(), 0.01f); // Sobregiro acumulado
    }

    @Test
    void testDepositWithoutOverdraft() {
        CheckingAccount account = new CheckingAccount(1L, 5000, 3.5f, 50);
        account.deposit(2000); // Depositar sin sobregiro
        assertEquals(7000, account.getBalance(), 0.01f);
        assertEquals(0, account.getOverdraft(), 0.01f);
    }

    @Test
    void testDepositWithOverdraft() {
        CheckingAccount account = new CheckingAccount(1L, 0, 3.5f, 50);
        account.withdraw(2000); // Crear un sobregiro
        account.deposit(1500); // Depositar menos del sobregiro
        assertEquals(0, account.getBalance(), 0.01f); // Saldo sigue en 0
        assertEquals(500, account.getOverdraft(), 0.01f); // Parte del sobregiro cubierto
    }

    @Test
    void testGenerateMonthlyStatement() {
        CheckingAccount account = new CheckingAccount(1L, 5000, 3.5f, 50);
        account.generateMonthlyStatement();
        assertEquals(4950.15, account.getBalance(), 0.01f); // Saldo después de interés y tarifa
    }

    @Test
    void testPrintAccountDetails() {
        CheckingAccount account = new CheckingAccount(1L, 5000, 3.5f, 50);
        String details = account.printAccountDetails();
        assertTrue(details.contains("Overdraft: $0.0"));
    }
}
