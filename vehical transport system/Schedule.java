package com.vts;

public class Schedule {
    private String scheduleId;
    private String date;
    private String from;
    private String to;
    private String vehicleId;
    private String driverId;

    public Schedule(String scheduleId, String date, String from, String to, String vehicleId, String driverId) {
        this.scheduleId = scheduleId;
        this.date = date;
        this.from = from;
        this.to = to;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
    }

    public String getDate() {
        return date;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getDriverId() {
        return driverId;
    }
    
    

    @Override
	public String toString() {
		return "Schedule [scheduleId=" + scheduleId + ", date=" + date + ", from=" + from + ", to=" + to
				+ ", vehicleId=" + vehicleId + ", driverId=" + driverId + "]";
	}

	public void displaySchedule() {
        System.out.println("Schedule ID: " + scheduleId);
        System.out.println("Date: " + date);
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Driver ID: " + driverId);
        System.out.println("-----------------------------------");
    }
}
