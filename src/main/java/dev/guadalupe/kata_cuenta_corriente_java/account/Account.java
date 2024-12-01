package dev.guadalupe.kata_cuenta_corriente_java.account;

import java.math.BigDecimal;


public class Account {
    private long id; 
    private float balance;
    private int numDeposits;
    private int numWithdrawals;
    private float annualRate;
    private float monthlyFee;

    // Constructor
    public Account(long id, float balance, float annualRate, float monthlyFee) {
        this.id = id;
        this.balance = balance;
        this.numDeposits = 0; 
        this.numWithdrawals = 0; 
        this.annualRate = annualRate;
        this.monthlyFee = monthlyFee;
    }

   
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

    
    public void deposit(float amount) {
        if (amount > 0) {
            balance += amount;
            numDeposits++; 
        } else {
            throw new IllegalArgumentException("The deposit amount must be greater than zero.");
        }
    }
    

    public void withdraw(float amount) {
        if (amount > 0) { 
            if (amount <= balance) { 
                balance -= amount;
                numWithdrawals++; 
            } else {
                throw new IllegalArgumentException("Insufficient balance for this withdrawal.");
            }
        } else {
            throw new IllegalArgumentException("The withdrawal amount must be greater than zero.");
        }
    }
    

    public void calculateMonthlyInterest() {
        float monthlyRate = annualRate / 12 / 100; 
        balance += balance * monthlyRate;
        balance = round(balance, 2); 

    }

    public void generateMonthlyStatement() {
        balance -= monthlyFee;
        if (balance < 0) {
            balance = 0;
        }
        calculateMonthlyInterest();
    }

    @SuppressWarnings("deprecation")
    private float round(float value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
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
