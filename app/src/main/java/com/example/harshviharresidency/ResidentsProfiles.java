package com.example.harshviharresidency;

public class ResidentsProfiles {

    private String firstName;
    private String lastName;
    private String houseNumber;
    private String mobileNumber;
    private String emailId;
    private String id;
    private int familyMember;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResidentsProfiles() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHouseNumber() {
        return houseNumber.length() == 1 ? "0" + houseNumber.toUpperCase() : houseNumber.toUpperCase();
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(int familyMember) {
        this.familyMember = familyMember;
    }

    public ResidentsProfiles(String firstName, String lastName, String houseNumber, String mobileNumber, String emailId, String id, int familyMember) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.houseNumber = houseNumber;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.id = id;
        this.familyMember = familyMember;
    }
}
