

package com.vts;

abstract class User {
    protected String name;
    protected String contactInfo;

    public User(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public abstract void displayInfo();
}