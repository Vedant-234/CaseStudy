package com.vts;

import java.util.Scanner;

public class TransportSystemApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Admin admin = new Admin("Disha", "9876543210", "admin123", "pass@123");
        Driver driver = new Driver("Ravi", "9988776655", "driver123", "MH12AB1234");

        System.out.println("=========== Vehicle Transport System ===========");
        System.out.println("Login as:\n1. Admin\n2. Driver");
        System.out.print("Enter choice: ");
        int userType = sc.nextInt();
        sc.nextLine(); 

        if (userType == 1) {
            System.out.print("Enter Admin Username: ");
            String username = sc.nextLine();
            System.out.print("Enter Admin Password: ");
            String password = sc.nextLine();

            if (admin.getUsername().equals(username) && admin.checkPassword(password)) {
                System.out.println("\nAdmin Login Successful!");
                admin.displayInfo();
                int choice = -1;
                while (choice != 7) {
                    System.out.println("\n========= Admin Menu =========");
                    System.out.println("1. Register Vehicle");
                    System.out.println("2. Register Driver");
                    System.out.println("3. Plan Travel");
                    System.out.println("4. Add Maintenance Record");
                    System.out.println("5. Generate Monthly Expense Report");
                    System.out.println("6. Validate Due Dates");
                    System.out.println("7. Exit");
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    switch (choice) {
                        case 1 -> System.out.println("→ Register Vehicle functionality");
                        case 2 -> System.out.println("→ Register Driver functionality");
                        case 3 -> System.out.println("→ Plan Travel functionality");
                        case 4 -> System.out.println("→ Add Maintenance Record functionality");
                        case 5 -> System.out.println("→ Generate Expense Report functionality");
                        case 6 -> System.out.println("→ Validate Due Dates functionality");
                        case 7 -> System.out.println("Exiting Admin panel...");
                        default -> System.out.println(" Invalid choice. Try again.");
                    }
                }
            } else {
                System.out.println(" Invalid Admin Credentials!");
            }
        } else if (userType == 2) {
            System.out.print("Enter Driver ID: ");
            String driverId = sc.nextLine();
            if (driver.getDriverId().equals(driverId)) {
                System.out.println("\n Driver Login Successful!");
                driver.displayInfo();
                int choice = -1;
                while (choice != 4) {
                    System.out.println("\n========= Driver Menu =========");
                    System.out.println("1. Add Travel Entry");
                    System.out.println("2. Add Fuel Entry");
                    System.out.println("3. Report Accident");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();
                    3
                    sc.nextLine();
                    switch (choice) {
                        case 1 -> System.out.println("→ Add Travel Entry functionality");
                        case 2 -> System.out.println("→ Add Fuel Entry functionality");
                        case 3 -> System.out.println("→ Report Accident functionality");
                        case 4 -> System.out.println("Exiting Driver panel...");
                        default -> System.out.println(" Invalid choice. Try again.");
                    }
                }
            } else {
                System.out.println(" Invalid Driver ID!");
            }
        } else {
            System.out.println("Invalid user type selected.");
        }
        sc.close();
    }
}