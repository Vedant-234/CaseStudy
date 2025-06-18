package com.vts;

import java.util.ArrayList;

import java.util.Scanner;

class Driver extends User {
	private String driverId;
	private String licenseNumber;

	private ArrayList<Accident> accidents = new ArrayList<>();

	public Driver(String name, String contactInfo, String driverId, String licenseNumber) {
		super(name, contactInfo);
		this.driverId = driverId;
		this.licenseNumber = licenseNumber;
	}

	public String getDriverId() {
		return driverId;
	}

	public void addAccidentEntry(Scanner sc, ArrayList<Accident> globalAccidentList) {
		System.out.print("Enter Accident ID: ");
		String accId = sc.nextLine();
		System.out.print("Enter Date (dd-mm-yyyy): ");
		String date = sc.nextLine();
		System.out.print("Enter Description: ");
		String desc = sc.nextLine();
		System.out.print("Enter Cost: ");
		double cost = sc.nextDouble(); sc.nextLine();

		Accident a = new Accident(accId, date, desc, cost, driverId);
		accidents.add(a);
		globalAccidentList.add(a);
		System.out.println("Accident Reported Successfully!");
		a.displayAccident();
	}

	public void displayAccidentHistory() {
		System.out.println("Accident History for Driver: " + driverId);
		for (Accident a : accidents) {
			a.displayAccident();
		}
	}

	@Override
	public void displayInfo() {
		System.out.println("Driver Name: " + name);
		System.out.println("Contact Info: " + contactInfo);
		System.out.println("Driver ID: " + driverId);
		System.out.println("License No: " + licenseNumber);
	}

	@Override
	public String toString() {
		return "[Driver ID: " + driverId + ", Name: " + name + ", Contact: " + contactInfo + "]";
	}




	public void driverMenu(ArrayList<TravelEntry> travelEntries, ArrayList<FuelEntry> fuelEntries,
			ArrayList<Accident> accidentRecords) {
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\n--- Driver Data Display Menu ---");
			System.out.println("1. Display Travel Entries");
			System.out.println("2. Display Fuel Entries");
			System.out.println("3. Display Accident Entries");
			System.out.println("4. Back to Driver Menu");
			System.out.print("Enter choice: ");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				for (TravelEntry t : travelEntries) {
					if (t.getDriverId().equals(this.driverId)) {
						System.out.println(t);
					}
				}
				break;
			case 2:
				for (FuelEntry f : fuelEntries) {
					if (f.getDriverId().equals(this.driverId)) {
						System.out.println(f);
					}
				}
				break;
			case 3:
				for (Accident a : accidentRecords) {
					if (a.getDriverId().equals(this.driverId)) {
						System.out.println(a);
					}
				}
				break;
			case 4:
				System.out.println("Returning to Driver Menu...");
				break;
			default:
				System.out.println("Invalid choice.");
			}
		} while (choice != 4);
	}
} 


