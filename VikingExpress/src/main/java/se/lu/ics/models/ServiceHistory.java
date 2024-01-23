package se.lu.ics.models;

import java.time.LocalDate;

public class ServiceHistory {

    private Vehicle vehicle;
    private LocalDate date;
    private int partsReplaced;
    private double cost;
    private Workshop workshop;
    private String problemDescription;

    public ServiceHistory (Vehicle vehicle, LocalDate date, int partsReplaced, double cost, Workshop workshop, String problemDescription) { 
        this.vehicle = vehicle;
        this.date = date;
        this.partsReplaced = partsReplaced;
        this.cost = cost;
        this.workshop = workshop;
        this.problemDescription = problemDescription;
    }

    //getters and setters for all variables
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPartsReplaced() {
        return partsReplaced;
    }

    public void setPartsReplaced(int partsReplaced) {
        this.partsReplaced = partsReplaced;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

}

