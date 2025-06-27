package com.vts.model;

public class Schedule {
    private String scheduleId;
    private String date;
    private String from;
    private String to;
    private String vehicleId;
    private String driverId;

    public Schedule() {}
    
    public Schedule(String scheduleId, String date, String from, String to, String vehicleId, String driverId) {
        this.scheduleId = scheduleId;
        this.date = date;
        this.from = from;
        this.to = to;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
    }

    public String getScheduleId() {
        return scheduleId;
    }
    
    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getFrom() {
        return from;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    public String getTo() {
        return to;
    }
    
    public void setTo(String to) {
        this.to = to;
    }
    
    public String getVehicleId() {
        return vehicleId;
    }
    
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    public String getDriverId() {
        return driverId;
    }
    
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    
    @Override
    public String toString() {
        return "Schedule [scheduleId=" + scheduleId + ", date=" + date + ", from=" + from + ", to=" + to
                + ", vehicleId=" + vehicleId + ", driverId=" + driverId + "]";
    }
    
    public String toCSV() {
        return String.join(",",
                scheduleId,
                date,
                from,
                to,
                vehicleId,
                driverId
        );
    }

    public static Schedule fromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 6) return null;
        return new Schedule(
            parts[0], 
            parts[1], 
            parts[2], 
            parts[3], 
            parts[4], 
            parts[5]
        );
    }
}