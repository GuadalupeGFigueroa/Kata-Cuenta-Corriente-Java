package dev.guadalupe.kata_cuenta_corriente_java.account;

public class Account {
    public long id;
    private float balance;
    private int numDeposits;
    private int numWithdrawals;
    private float annualRate;
    private float monthlyFee;

    public Account(long id, float balance, int numDeposits, int numWithdrawals, float annualRate, float monthlyFee) {
        this.id = id;
        this.balance = balance;
        this.numDeposits = numDeposits;
        this.numWithdrawals = numWithdrawals;
        this.annualRate = annualRate;
        this.monthlyFee = monthlyFee;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getNumDeposits() {
        return numDeposits;
    }

    public void setNumDeposits(int numDeposits) {
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
    

}
