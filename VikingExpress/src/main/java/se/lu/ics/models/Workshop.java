package se.lu.ics.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Workshop {

    private String name;
    private String address;
    private String workshopInfo; //Internal or external workshop
    private ObservableList<Vehicle> vehicles;

    public Workshop (String name, String address, String workshopInfo) {
        this.name = name;
        this.address = address;
        this.workshopInfo = workshopInfo;
        this.vehicles = FXCollections.observableArrayList();
    }

    //Getters and Setters
     public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName (String workshopName) {
        this.name = workshopName;
    }

    public String getWorkshopInfo() {
        return workshopInfo;
    }

    public void setWorkshopInfo (String workshopInfo) {
        this.workshopInfo = workshopInfo;
    }

    public ObservableList<Vehicle> getVehicles() {
        return FXCollections.unmodifiableObservableList(vehicles);
    }

    @Override
    public String toString() {
        return name + ": " + workshopInfo;
    }
}