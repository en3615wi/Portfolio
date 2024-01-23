package se.lu.ics.models;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServiceHistoryRegister {

    private ObservableList<ServiceHistory> serviceHistory;

    public ServiceHistoryRegister() {
        this.serviceHistory = FXCollections.observableArrayList();
        addServiceHistoryTestData();
    }

    public ObservableList<ServiceHistory> getServiceHistory() {
        return this.serviceHistory;
    }

    public void addServiceHistory(ServiceHistory serviceHistory) {
        this.serviceHistory.add(serviceHistory);
    }

    public void removeServiceHistory(ServiceHistory serviceHistory) {
        this.serviceHistory.remove(serviceHistory);
    }

    private void addServiceHistoryTestData() {

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

        ServiceHistory serviceHistory1 = new ServiceHistory(vehicle1, LocalDate.of(2021, 12, 10), 6, 10000,  workshop1, "Fixed Tires, changed oil");
        ServiceHistory serviceHistory2 = new ServiceHistory(vehicle2, LocalDate.of(2022, 3, 11), 10, 40000, workshop2, "Front cylinders needed maintenance");
        ServiceHistory serviceHistory3 = new ServiceHistory(vehicle3, LocalDate.of(2022, 11, 28), 4, 15000, workshop3, "Steering wheel needed maintenance");
        ServiceHistory serviceHistory4 = new ServiceHistory(vehicle4, LocalDate.of(2023, 4, 22), 12, 25000, workshop4, "Ball bearings needed a check-up");
        ServiceHistory serviceHistory5 = new ServiceHistory(vehicle5, LocalDate.of(2023, 1, 15), 6, 13000, workshop4, "Fixed Tires, changed oil");
        ServiceHistory serviceHistory6 = new ServiceHistory(vehicle6, LocalDate.of(2023, 3, 12), 16, 50000, workshop3, "Front cylinders needed maintenance");
        ServiceHistory serviceHistory7 = new ServiceHistory(vehicle7, LocalDate.of(2023, 11, 2), 4, 15000, workshop1, "Steering wheel needed maintenance");
        ServiceHistory serviceHistory8 = new ServiceHistory(vehicle8, LocalDate.of(2023, 12, 28), 10, 20000, workshop1, "Ball bearings needed a check-up");

        addServiceHistory(serviceHistory1);
        addServiceHistory(serviceHistory2);
        addServiceHistory(serviceHistory3);
        addServiceHistory(serviceHistory4);
        addServiceHistory(serviceHistory5);
        addServiceHistory(serviceHistory6);
        addServiceHistory(serviceHistory7);
        addServiceHistory(serviceHistory8);
    }

}