package com.example.harshviharresidency;

public class societyExpenses {
    private String BillerName, expensesType, paymentMode, TransactionNumber;
    private float Amount;
    private String ExpenseDate;
    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public societyExpenses() {
    }

    public String getBillerName() {
        return BillerName;
    }

    public void setBillerName(String billerName) {
        BillerName = billerName;
    }

    public String getExpensesType() {
        return expensesType;
    }

    public void setExpensesType(String expensesType) {
        this.expensesType = expensesType;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getTransactionNumber() {
        return TransactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        TransactionNumber = transactionNumber;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public String getExpenseDate() {
        return ExpenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        ExpenseDate = expenseDate;
    }

    public societyExpenses(String billerName, String expensesType, String paymentMode, String transactionNumber, float amount, String expenseDate, String id) {
        BillerName = billerName;
        this.expensesType = expensesType;
        this.paymentMode = paymentMode;
        TransactionNumber = transactionNumber;
        Amount = amount;
        ExpenseDate = expenseDate;
        Id = id;
    }
}
