package com.vts.model;

public abstract class User {
    protected String name;
    protected String contactInfo;

    public User() {}

    public User(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public abstract void displayInfo();
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getContactInfo() {
        return contactInfo;
    }
    
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}