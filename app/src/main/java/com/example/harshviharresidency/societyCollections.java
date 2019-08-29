package com.example.harshviharresidency;

public class societyCollections {
    private String Name, HouseNumber, CollectionType, PaymentMode, TransactionNumber, collBy;
    private float Amount;
    private String CollDate;
    private String Month;
    private int Year;

    public String getCollBy() {
        return collBy;
    }

    public void setCollBy(String collBy) {
        this.collBy = collBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public societyCollections() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHouseNumber() {
        return HouseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        HouseNumber = houseNumber;
    }

    public String getCollectionType() {
        return CollectionType;
    }

    public void setCollectionType(String collectionType) {
        CollectionType = collectionType;
    }

    public String getPaymentMode() {
        return PaymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        PaymentMode = paymentMode;
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

    public String getCollDate() {
        return CollDate;
    }

    public void setCollDate(String collDate) {
        CollDate = collDate;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public societyCollections(String name,
                              String houseNumber,
                              String collectionType,
                              String paymentMode,
                              String transactionNumber,
                              float amount,
                              String collDate,
                              String month,
                              int year,
                              String id,
                              String collBy) {
        Name = name;
        HouseNumber = houseNumber;
        CollectionType = collectionType;
        PaymentMode = paymentMode;
        TransactionNumber = transactionNumber;
        Amount = amount;
        CollDate = collDate;
        Month = month;
        Year = year;
        this.id = id;
        this.collBy = collBy;
    }
}
