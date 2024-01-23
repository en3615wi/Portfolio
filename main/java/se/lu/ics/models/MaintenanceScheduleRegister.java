package se.lu.ics.models;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MaintenanceScheduleRegister {

    private ObservableList<MaintenanceSchedule> maintenanceSchedules;

    public MaintenanceScheduleRegister() {
        this.maintenanceSchedules = FXCollections.observableArrayList();
        addMaintenanceScheduleTestData();
    }

    public ObservableList<MaintenanceSchedule> getMaintenanceSchedules() {
        return this.maintenanceSchedules;
    }

    public void addMaintenanceSchedule (MaintenanceSchedule maintenanceSchedule) {
        this.maintenanceSchedules.add(maintenanceSchedule);
    }

    public void removeMaintenanceSchedule (MaintenanceSchedule maintenanceSchedule) {
        this.maintenanceSchedules.remove(maintenanceSchedule);
    }

    Vehicle vehicle1 = new Vehicle("ABC123", "MAN TGE", "Van", "Lund", 6);
    Vehicle vehicle2 = new Vehicle("DEF456", "Volvo FH", "Medium Truck", "Malmö", 36);
    Vehicle vehicle3 = new Vehicle("GHI789", "Volvo FM", "Medium Truck", "Helsingborg", 36);
    Vehicle vehicle4 = new Vehicle("JKL012", "Scania P-serie", "Large Truck", "Lund", 80);
    Vehicle vehicle5 = new Vehicle("MNO345", "Scania S-serie", "Large Truck", "Lund", 210);
    Vehicle vehicle6 = new Vehicle("SFK872", "MAN TGL", "Van", "Gävle", 8);
    Vehicle vehicle7 = new Vehicle("KFS238", "MAN TGX", "Medium Truck", "Sundsvall", 45);
    Vehicle vehicle8 = new Vehicle("IJF164", "Volvo FM Electric", "Medium Truck", "Göteborg", 44);

    Workshop workshop1 = new Workshop("Viking Express Göteborg", "Clemens falafelgränd 2, Göteborg", "Internal");
    Workshop workshop2 = new Workshop("Viking Express Malmö", "Amiralsgatan 21, Malmö", "Internal");
    Workshop workshop3 = new Workshop("Viking Express Sundsvall", "Knutpunktsvägen 60, Sundsvall", "Internal");
    Workshop workshop4 = new Workshop("Speedy Bilservice", "Lundavägen 13, Lund", "External");

    private void addMaintenanceScheduleTestData(){
        MaintenanceSchedule maintenanceSchedule1 = new MaintenanceSchedule(LocalDate.of(2024, 1, 15), "10:15", workshop1, vehicle1);
        MaintenanceSchedule maintenanceSchedule2 = new MaintenanceSchedule(LocalDate.of(2024, 8, 12), "14:30", workshop2, vehicle2);
        MaintenanceSchedule maintenanceSchedule3 = new MaintenanceSchedule(LocalDate.of(2025, 2, 22), "09:00", workshop3, vehicle3);
        MaintenanceSchedule maintenanceSchedule4 = new MaintenanceSchedule(LocalDate.of(2025, 8, 15), "10:00", workshop4, vehicle4);
        MaintenanceSchedule maintenanceSchedule5 = new MaintenanceSchedule(LocalDate.of(2025, 5, 28), "12:00", workshop4, vehicle5);
        MaintenanceSchedule maintenanceSchedule6 = new MaintenanceSchedule(LocalDate.of(2025, 6, 10), "14:45", workshop3, vehicle6);
        MaintenanceSchedule maintenanceSchedule7 = new MaintenanceSchedule(LocalDate.of(2025, 12, 1), "11:00", workshop1, vehicle7);
        MaintenanceSchedule maintenanceSchedule8 = new MaintenanceSchedule(LocalDate.of(2025, 11, 30), "10:30", workshop1, vehicle8);
    
        addMaintenanceSchedule(maintenanceSchedule1);
        addMaintenanceSchedule(maintenanceSchedule2);
        addMaintenanceSchedule(maintenanceSchedule3);
        addMaintenanceSchedule(maintenanceSchedule4);
        addMaintenanceSchedule(maintenanceSchedule5);
        addMaintenanceSchedule(maintenanceSchedule6);
        addMaintenanceSchedule(maintenanceSchedule7);
        addMaintenanceSchedule(maintenanceSchedule8);
    }
}