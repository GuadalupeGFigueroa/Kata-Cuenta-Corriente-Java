package dev.guadalupe.kata_cuenta_corriente_java.account;

public class Account {
    private long id; //Se cambia el id como private porque no debe ser modificado directamente por fuera de la clase
    private float balance;
    private int numDeposits;
    private int numWithdrawals;
    private float annualRate;
    private float monthlyFee;

    // Constructor
    public Account(long id, float balance, float annualRate, float monthlyFee) {
        this.id = id;
        this.balance = balance;
        this.numDeposits = 0; // Inicializar en 0
        this.numWithdrawals = 0; // Inicializar en 0
        this.annualRate = annualRate;
        this.monthlyFee = monthlyFee;
    }

    // Getters (no setters para atributos inmutables como id)
    public long getId() {
        return id;
    }

    public float getBalance() {
        return balance;
    }

    protected void setBalance(float balance) {
        this.balance = balance;
    }

    public int getNumDeposits() {
        return numDeposits;
    }

    protected void setNumDeposits(int numDeposits) {
        this.numDeposits = numDeposits;
    }
    

    public int getNumWithdrawals() {
        return numWithdrawals;
    }

    public void setNumWithdrawals(int numWithdrawals) {
        this.numWithdrawals = numWithdrawals;
    }

    public float getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(float annualRate) {
        this.annualRate = annualRate;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    // Métodos funcionales
    public void deposit(float amount) {
        if (amount > 0) { // Verificar que la cantidad sea válida
            balance += amount;
            numDeposits++; // Incrementar el contador de consignaciones
        } else {
            throw new IllegalArgumentException("The deposit amount must be greater than zero.");
        }
    }
    

    public void withdraw(float amount) {
        if (amount > 0) { // Verificar que la cantidad sea válida
            if (amount <= balance) { // Verificar si hay saldo suficiente
                balance -= amount;
                numWithdrawals++; // Incrementar el contador de retiros
            } else {
                throw new IllegalArgumentException("Insufficient balance for this withdrawal.");
            }
        } else {
            throw new IllegalArgumentException("The withdrawal amount must be greater than zero.");
        }
    }
    

    public void calculateMonthlyInterest() {
        float monthlyRate = annualRate / 12 / 100; //Convertir la tasa anual a mensual
        balance += balance * monthlyRate; //Actualizar el saldo

        /*Otra forma de hacerlo: 
         * 
        public void calculateMonthlyInterest() {
            float monthlyRate = annualRate / 12 / 100;
            float interest = balance * monthlyRate; // Calcular el interés
            balance += interest; // Actualizar el saldo
        }

        Ambas formas son válidas, pero depende de tu caso de uso:

        Si necesitas mostrar el interés calculado en un reporte o hacer operaciones adicionales con ese valor, es mejor usar la variable interest.
        Si el único propósito es actualizar el saldo, la forma compacta (sin variable intermedia) es suficiente.
        }   
         */
    }

    public void generateMonthlyStatement() {
        balance -= monthlyFee;
        if (balance < 0) {
            balance = 0;
        }
        calculateMonthlyInterest();
    }

    public String printAccountDetails() {
        return "Account ID: " + id +
               "\nBalance: $" + balance +
               "\nDeposits: " + numDeposits +
               "\nWithdrawals: " + numWithdrawals +
               "\nAnnual Rate: " + annualRate + "%" +
               "\nMonthly Fee: $" + monthlyFee;
    }
}
