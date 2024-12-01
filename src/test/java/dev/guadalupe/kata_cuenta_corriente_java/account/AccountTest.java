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
        // Configuración inicial: crear una cuenta con valores predefinidos
        account = new Account(1L, 5000f, 5.0f, 50f); // ID: 1, Saldo: 5000, Tasa anual: 5%, Comisión mensual: 50
    }

    @Test
    void testConstructorAndGetters() {
        // Verifica que los valores iniciales sean correctos
        assertEquals(1L, account.getId());
        assertEquals(5000f, account.getBalance());
        assertEquals(0, account.getNumDeposits());
        assertEquals(0, account.getNumWithdrawals());
        assertEquals(5.0f, account.getAnnualRate());
        assertEquals(50f, account.getMonthlyFee());
    }

    @Test
    void testDeposit() {
        // Depositar dinero válido
        account.deposit(1000f);
        assertEquals(6000f, account.getBalance());
        assertEquals(1, account.getNumDeposits());

        // Depositar una cantidad no válida
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-100f);
        });
        assertEquals("The deposit amount must be greater than zero.", exception.getMessage());
    }

    @Test
    void testWithdraw() {
        // Retirar una cantidad válida
        account.withdraw(1000f);
        assertEquals(4000f, account.getBalance());
        assertEquals(1, account.getNumWithdrawals());

        // Intentar retirar más del saldo
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(10000f);
        });
        assertEquals("Insufficient balance for this withdrawal.", exception.getMessage());
    }

    @Test
    void testCalculateMonthlyInterest() {
        // Calcula el interés mensual y verifica el saldo actualizado
        account.calculateMonthlyInterest();
        assertEquals(5020.83f, account.getBalance(), 0.01f); // Saldo después del interés (5% anual -> ~0.42% mensual)
    }

    @Test
    void testGenerateMonthlyStatement() {
        // Generar extracto mensual
        account.generateMonthlyStatement();
        assertEquals(4970.83f, account.getBalance(), 0.01f); // Saldo después de restar la comisión mensual y aplicar el interés
    }

    @Test
    void testPrintAccountDetails() {
        // Verifica que los detalles de la cuenta se imprimen correctamente
        String details = account.printAccountDetails();
        assertThat(details, containsString("Account ID: 1"));
        assertThat(details, containsString("Balance: $5000.0"));
        assertThat(details, containsString("Annual Rate: 5.0%"));
        assertThat(details, containsString("Monthly Fee: $50.0"));
    }
}

    
