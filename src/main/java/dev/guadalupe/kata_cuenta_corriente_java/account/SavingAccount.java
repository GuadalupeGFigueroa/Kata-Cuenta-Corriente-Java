package dev.guadalupe.kata_cuenta_corriente_java.account;

public class SavingAccount extends Account {
    private boolean isActive; // Determina si la cuenta está activa

    public SavingAccount(long id, float balance, float annualRate, float monthlyFee) {
        super(id, balance, annualRate, monthlyFee);
        updateAccountStatus(); // Determinar si la cuenta está activa
    }

    // Método privado para actualizar el estado de la cuenta
    private void updateAccountStatus() {
        this.isActive = getBalance() >= 10000;
    }

    // Redefinir método withdraw
    @Override
    public void withdraw(float amount) {
        if (!isActive) {
            throw new IllegalStateException("Cannot withdraw: The account is inactive.");
        }
        if (amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            setNumWithdrawals(getNumWithdrawals() + 1);
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }
        updateAccountStatus(); // Actualizar el estado de la cuenta
    }

    // Redefinir método deposit
    @Override
    public void deposit(float amount) {
        if (amount > 0) {
            setBalance(getBalance() + amount);
            setNumDeposits(getNumDeposits() + 1);
            updateAccountStatus(); // Actualizar el estado de la cuenta
        } else {
            throw new IllegalArgumentException("The deposit amount must be greater than zero.");
        }
    }

    // Redefinir método generateMonthlyStatement
    @Override
    public void generateMonthlyStatement() {
        if (getNumWithdrawals() > 4) {
            int extraWithdrawals = getNumWithdrawals() - 4;
            setMonthlyFee(getMonthlyFee() + (extraWithdrawals * 1000)); // Cobrar $1000 por retiro adicional
        }
        super.generateMonthlyStatement(); // Aplicar la lógica general
        updateAccountStatus(); // Actualizar el estado de la cuenta
    }

    // Redefinir método printAccountDetails
    @Override
    public String printAccountDetails() {
        return super.printAccountDetails() +
               "\nAccount Status: " + (isActive ? "Active" : "Inactive");
    }
}
