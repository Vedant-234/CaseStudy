package com.vts;

class Admin extends User {
    private String username;
    private String password;

    public Admin(String name, String contactInfo, String username, String password) {
        super(name, contactInfo);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String pass) {
        return this.password.equals(pass);
    }

    @Override
    public void displayInfo() {
        System.out.println("Admin Name: " + name);
        System.out.println("Contact: " + contactInfo);
    }
}