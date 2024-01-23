package se.lu.ics.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VehicleRegister {

    private ObservableList<Vehicle> vehicles;

    public VehicleRegister() {
        this.vehicles = FXCollections.observableArrayList();
        addVehicleTestData();
    }

    public ObservableList<Vehicle> getVehicles() {
        return FXCollections.unmodifiableObservableList(this.vehicles);
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
    }

    private void addVehicleTestData(){

        Vehicle vehicle1 = new Vehicle("ABC123", "MAN TGE", "Van", "Lund", 6);
        Vehicle vehicle2 = new Vehicle("DEF456", "Volvo FH", "Medium Truck", "Malmö", 36);
        Vehicle vehicle3 = new Vehicle("GHI789", "Volvo FM", "Medium Truck", "Helsingborg", 36);
        Vehicle vehicle4 = new Vehicle("JKL012", "Scania P-serie", "Large Truck", "Lund", 80);
        Vehicle vehicle5 = new Vehicle("MNO345", "Scania S-serie", "Large Truck", "Lund", 210);
        Vehicle vehicle6 = new Vehicle("SFK872", "MAN TGL", "Van", "Gävle", 8);
        Vehicle vehicle7 = new Vehicle("KFS238", "MAN TGX", "Medium Truck", "Sundsvall", 45);
        Vehicle vehicle8 = new Vehicle("IJF164", "Volvo FM Electric", "Medium Truck", "Göteborg", 44);

        addVehicle(vehicle1);
        addVehicle(vehicle2);
        addVehicle(vehicle3);
        addVehicle(vehicle4);
        addVehicle(vehicle5);
        addVehicle(vehicle6);
        addVehicle(vehicle7);
        addVehicle(vehicle8);

        vehicle1.addPartsReplaced(6);
        vehicle2.addPartsReplaced(10);
        vehicle3.addPartsReplaced(4);
        vehicle4.addPartsReplaced(12);
        vehicle5.addPartsReplaced(6);
        vehicle6.addPartsReplaced(16);
        vehicle7.addPartsReplaced(4);
        vehicle8.addPartsReplaced(10);

        vehicle1.addServiceHistoryCost(10000);
        vehicle2.addServiceHistoryCost(40000);
        vehicle3.addServiceHistoryCost(15000);
        vehicle4.addServiceHistoryCost(25000);
        vehicle5.addServiceHistoryCost(13000);
        vehicle6.addServiceHistoryCost(50000);
        vehicle7.addServiceHistoryCost(15000);
        vehicle8.addServiceHistoryCost(20000);
    }
}