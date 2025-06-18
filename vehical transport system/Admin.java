package com.vts;

import java.util.ArrayList;
import java.util.Scanner;

class Admin extends User {
	private String username;
	private String password;

	public Admin(String name, String contactInfo, String username, String password) {
		super(name, contactInfo);
		this.username = username;
		this.password = password;
	}

	public ExpenseReport generateMonthlyReport(
			String reportId,
			String month,

			ArrayList<FuelEntry> fuelEntries,
			ArrayList<Maintenance> maintenanceRecords,
			ArrayList<Accident> accidentRecords) {

		double totalFuel = 0, totalMaintenance = 0, totalAccident = 0;

		for (FuelEntry f : fuelEntries) {


			String[] parts = f.getFuelDate().split("-");
			if (parts.length == 3) {
				String fuelMonthYear = parts[2] + "-" + parts[1];
				if (fuelMonthYear.equals(month)) {
					totalFuel += f.getFuelCost();
				}
			}

			System.out.println(totalFuel);
		}

		for (Maintenance m : maintenanceRecords) {

			String[] parts = m.getDueDate().split("-");
			if (parts.length == 3) {
				String maintMonthYear = parts[0] + "-" + parts[1];
				if (maintMonthYear.equals(month)) {
					totalMaintenance += m.getCost();
				}
			}

			System.out.println(totalMaintenance);
		}

		for (Accident a : accidentRecords) {

			String[] parts = a.getDate().split("-");
			if (parts.length == 3) {
				String accMonthYear = parts[2] + "-" + parts[1];
				if (accMonthYear.equals(month)) {
					totalAccident += a.getCost();
				}
			}

			System.out.println(totalAccident);
		}

		return new ExpenseReport(reportId, month, totalFuel, totalMaintenance, totalAccident);
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


	///////////////////////////////////////////////

	public void displayDataMenu(ArrayList<Vehicle> vehicles, ArrayList<Driver> drivers, ArrayList<Schedule> schedules,
			ArrayList<Maintenance> maintenanceRecords, ArrayList<TravelEntry> travelEntries,
			ArrayList<FuelEntry> fuelEntries, ArrayList<Accident> accidentRecords) {
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\n--- Admin Data Display Menu ---");
			System.out.println("1. Display Vehicles");
			System.out.println("2. Display Drivers");
			System.out.println("3. Display Schedules");
			System.out.println("4. Display Maintenance Records");
			System.out.println("5. Display Travel Entries");
			System.out.println("6. Display Fuel Entries");
			System.out.println("7. Display Accident Entries");
			System.out.println("8. Back to Admin Menu");
			System.out.print("Enter choice: ");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				for (Vehicle v : vehicles) {
					System.out.println(v);
				}
				break;
			case 2:
				for (Driver d : drivers) {
					System.out.println(d);
				}
				break;
			case 3:
				for (Schedule s : schedules) {
					System.out.println(s);
				}
				break;
			case 4:
				for (Maintenance m : maintenanceRecords) {
					System.out.println(m);
				}
				break;
			case 5:
				for (TravelEntry t : travelEntries) {
					System.out.println(t);
				}
				break;
			case 6:
				for (FuelEntry f : fuelEntries) {
					System.out.println(f);
				}
				break;
			case 7:
				for (Accident a : accidentRecords) {
					System.out.println(a);
				}
				break;
			case 8:
				System.out.println("Returning to Admin Menu...");
				break;
			default:
				System.out.println("Invalid choice.");
			}
		} while (choice != 8);
	}
}
