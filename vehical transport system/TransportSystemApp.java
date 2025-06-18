
package com.vts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransportSystemApp {
	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in);

		Admin admin = new Admin("Disha", "9876543210", "admin123", "pass@123");		

		ArrayList<Vehicle> vehicles = new ArrayList<>();
		ArrayList<Driver> drivers = new ArrayList<>();
		ArrayList<TravelEntry> travelEntries = new ArrayList<>();
		ArrayList<Maintenance> maintenanceRecords = new ArrayList<>();
		ArrayList<Schedule> schedules = new ArrayList<>();
		ArrayList<FuelEntry> fuelEntries = new ArrayList<>();
		ArrayList<Accident> accidentRecords = new ArrayList<>();
		

		boolean exitSystem = false;

		while (!exitSystem) {
			System.out.println("=========== Vehicle Transport System ===========");
			System.out.println("Login as:\n1. Admin\n2. Driver\n3. Exit");
			System.out.print("Enter choice: ");
			int userType = sc.nextInt();
			sc.nextLine();

			if (userType == 1) {
				// existing admin logic here...

				System.out.print("Enter Admin Username: ");
				String username = sc.nextLine();
				System.out.print("Enter Admin Password: ");
				String password = sc.nextLine();

				if (admin.getUsername().equals(username) && admin.checkPassword(password)) {
					System.out.println("\nAdmin Login Successful!");
					admin.displayInfo();
					int choice = -1;
					while (choice != 7) {
						choice = printAdminMenu(sc);

						switch (choice) {

						case 0 -> {
							admin.displayDataMenu(vehicles, drivers, schedules, maintenanceRecords, travelEntries, fuelEntries, accidentRecords);
							break;
						}
						case 1 -> {
							vehicleRegister(sc, vehicles);
						}

						case 2 -> {
							callDriverRegister(sc, drivers);                               
						}

						case 3 -> {
							System.out.print("Enter Schedule ID: ");
							String sid = sc.nextLine();
							System.out.print("Enter Date (dd-mm-yyyy): ");
							String tdate = sc.nextLine();
							System.out.print("Enter From Location: ");
							String from = sc.nextLine();
							System.out.print("Enter To Location: ");
							String to = sc.nextLine();
							System.out.print("Enter Vehicle ID: ");
							String vId = sc.nextLine();
							System.out.print("Enter Driver ID: ");
							String dId = sc.nextLine();

							boolean vehicleExists = false;
							boolean driverExists = false;


							for (Vehicle v : vehicles) {
								if (v.getVehicleId().equals(vId)) {
									vehicleExists = true;
									break;
								}
							}

							for (Driver d : drivers) {
								if (d.getDriverId().equals(dId)) {
									driverExists = true;
									break;
								}
							}

							if (!vehicleExists || !driverExists) {
								System.out.println("Vehicle or Driver not found. Please register first.");
								break;
							}

							// Check for conflicts
							boolean conflict = false;
							for (Schedule s : schedules) {
								if (s.getDate().equals(tdate) &&
										(s.getVehicleId().equals(vId) || s.getDriverId().equals(dId))) {
									conflict = true;
									break;
								}
							}

							if (conflict) {
								System.out.println("Conflict! Either the vehicle or driver is already scheduled on this date.");
							} else {
								Schedule newSchedule = new Schedule(sid, tdate, from, to, vId, dId);
								schedules.add(newSchedule);
								System.out.println("Travel scheduled successfully!");
							}

							// Show all current schedules
							System.out.println("\nCurrent Schedules:");
							for (Schedule s : schedules) {
								s.displaySchedule();
							}
						}


						case 4 -> {
							System.out.print("Enter Vehicle ID: ");
							String vehicleId = sc.next();

							System.out.print("Enter Maintenance Type (PUC/Insurance): ");
							String type = sc.next();

							System.out.print("Enter Due Date (YYYY-MM-DD): ");
							String date = sc.next();

							System.out.print("Enter Maintenance Cost: ");
							double cost = sc.nextDouble();

							Maintenance m = new Maintenance(vehicleId, type, date, cost);
							maintenanceRecords.add(m);

							System.out.println("Maintenance record added!");

						}


						case 5 -> {
							System.out.print("Enter month for report (YYYY-MM): ");
							String reportMonth = sc.next();

							System.out.print("Enter Report ID: ");
							String reportId = sc.next();

							ExpenseReport report = admin.generateMonthlyReport(
									reportId, reportMonth, fuelEntries, maintenanceRecords, accidentRecords
									);
							report.displayReport();
							break;
						}


						case 6 -> {
							System.out.println("Due Dates Validation:");
							for (Maintenance m : maintenanceRecords) {
								m.displayMaintenance();
							}
						}

						case 7 -> System.out.println("Exiting Admin panel...");

						default -> System.out.println("Invalid choice. Try again.");
						}
					}
				} else {
					System.out.println("Invalid Admin Credentials!");
				}

			} else if (userType == 2) {
				

				System.out.print("Enter Driver ID: ");
				String driverId = sc.nextLine();

				boolean found = false;

				for (Driver d : drivers) {
					if (d.getDriverId().equals(driverId)) {
						System.out.println("\nDriver Login Successful!");
						d.displayInfo();
						found = true;

						int choice = -1;
						while (choice != 4) {
							System.out.println("\n========= Driver Menu =========");
							System.out.println("0. Display all data");
							System.out.println("1. Add Travel Entry");
							System.out.println("2. Add Fuel Entry");
							System.out.println("3. Report Accident");
							System.out.println("4. Exit");
							System.out.print("Enter your choice: ");
							choice = sc.nextInt();
							sc.nextLine();
							switch (choice) {
							case 0 -> {
							    System.out.print("Enter Driver ID: ");
							    String driverId1 = sc.nextLine();

							    Driver currentDriver = null;
							    for (Driver d1 : drivers) {
							        if (d1.getDriverId().equals(driverId1)) {
							            currentDriver = d1;
							            break;
							        }
							    }

							    if (currentDriver != null) {
							        currentDriver.driverMenu(travelEntries, fuelEntries, accidentRecords);
							    } else {
							        System.out.println("Driver not found.");
							    }
							}

							case 1 -> {
								System.out.print("Enter Travel Entry ID: ");
								String travelId = sc.nextLine();

								System.out.print("Enter Date (dd-mm-yyyy): ");
								String travelDate = sc.nextLine();

								System.out.print("Enter Kilometers Travelled: ");
								double kms = sc.nextDouble(); sc.nextLine();

								TravelEntry t = new TravelEntry(travelId, travelDate, kms, driverId);
								travelEntries.add(t);

								System.out.println("Travel Entry Added Successfully!");
								t.displayTravelEntry();
							}

							case 2 -> {
								System.out.print("Enter Fuel Entry ID: ");
								String fuelId = sc.nextLine();

								System.out.print("Enter Date (dd-mm-yyyy): ");
								String fuelDate = sc.nextLine();

								System.out.print("Enter Liters Filled: ");
								double liters = sc.nextDouble(); 
								sc.nextLine(); 

								System.out.print("Enter Fuel Cost: ");
								double fuelCost = sc.nextDouble(); 
								sc.nextLine(); 

								FuelEntry f = new FuelEntry(fuelId, fuelDate, liters, fuelCost, driverId);
								f.displayFuelEntry();
								fuelEntries.add(f);

								System.out.println("Fuel Entry Added Successfully!");
								f.displayFuelEntry();
							}


							case 3 -> {
								d.addAccidentEntry(sc, accidentRecords); 

							}

							case 4 -> System.out.println("Exiting Driver panel...");
							default -> System.out.println("Invalid choice. Try again.");
							}
						}
						break;
					}
				}

				if (!found) {
					System.out.println("Driver not found. Please register with Admin first.");
				}

			} else if (userType == 3) {
				exitSystem = true;
				System.out.println("Exiting System. Goodbye!");
			} else {
				System.out.println("Invalid choice. Try again.");
			}
		}
	}

	private static int printAdminMenu(Scanner sc) {
		int choice;
		System.out.println("\n========= Admin Menu =========");
		System.out.println("0. Display all data");
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
		return choice;
	}

	private static void callDriverRegister(Scanner sc, ArrayList<Driver> drivers) {
		System.out.print("Enter Driver Name: ");
		String driverName = sc.nextLine();
		System.out.print("Enter Contact Info: ");
		String drivercontact = sc.nextLine();
		System.out.print("Enter Driver ID: ");
		String driverid = sc.nextLine();
		System.out.print("Enter License No: ");
		String liscen = sc.nextLine();

		Driver driver = new Driver(driverName, drivercontact, driverid, liscen);
		drivers.add(driver);
		System.out.println("Driver registered successfully!");

		System.out.println(drivers.toString());
	}

	private static void vehicleRegister(Scanner sc, ArrayList<Vehicle> vehicles) {
		System.out.print("Enter Vehicle ID: ");
		String vehicalId = sc.nextLine();
		System.out.print("Enter Registration No: ");
		String vehicalRegisterationNo = sc.nextLine();
		System.out.print("Enter Type: ");
		String VehicalType = sc.nextLine();
		System.out.print("Enter Capacity: ");
		int VehicalCapacity = sc.nextInt(); sc.nextLine();
		System.out.print("Enter Insurance Details: ");
		String VehicalInsuranceDetails = sc.nextLine();
		System.out.print("Enter Insurance Expiry Date: ");
		String VehicalInsuranceDExpDate = sc.nextLine();
		System.out.print("Enter PUC Date: ");
		String VehicalPucDetails = sc.nextLine();
		System.out.print("Enter PUC Expiry Date: ");
		String VehicalPucExpirydate = sc.nextLine();

		Vehicle v = new Vehicle(vehicalId, vehicalRegisterationNo, VehicalType, VehicalCapacity, VehicalInsuranceDetails, VehicalInsuranceDExpDate, VehicalPucDetails, VehicalPucExpirydate);
		vehicles.add(v);
		System.out.println("Vehicle registered successfully!");

		System.out.println(vehicles.toString());

	}
}



