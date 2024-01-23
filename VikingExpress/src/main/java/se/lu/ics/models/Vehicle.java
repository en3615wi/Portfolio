package se.lu.ics.models;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    private String vin;
    private String name;
    private String type; //Large truck, Medium truck, Van
    private String location;
    private int capacity;
    private Workshop workshop;

    private int totalPartsReplaced;

    private double totalServiceHistoryCost;

    private List<Integer> partsReplacedList = new ArrayList<>();

    private List<Double> serviceHistoryCostList = new ArrayList<>();


    public Vehicle(String vin, String name, String type, String location, int capacity) {
        this.vin = vin;
        this.name = name;
        this.type = type;
        this.location = location;
        this.capacity = capacity;
    }
     
    //Code to ServiceHistory parts replaced
    public void addPartsReplaced(int partsReplaced) {
        this.partsReplacedList.add(partsReplaced);
        totalPartsReplaced += partsReplaced;
    }

    public int getTotalPartsReplaced() {
        return totalPartsReplaced;
    }

    public void setTotalPartsReplaced(int totalPartsReplaced) {
        this.totalPartsReplaced = totalPartsReplaced;
    }

    //Code to ServiceHistory cost
    public void addServiceHistoryCost(double serviceHistoryCost) {
        this.serviceHistoryCostList.add(serviceHistoryCost);
        totalServiceHistoryCost += serviceHistoryCost;
    }

    public double getTotalServiceHistoryCost() {
        return totalServiceHistoryCost;
    }

    public void setTotalServiceHistoryCost(double totalServiceHistoryCost) {
        this.totalServiceHistoryCost = totalServiceHistoryCost;
    }

    //Getters and Setters
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getName() {
        return name;
    }

    public void setName(String model) {
        this.name = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public Workshop getWorkshop(){
        return workshop;
    }

    public void setWorkshop(Workshop workshop){
        this.workshop = workshop;
    }

    @Override
    public String toString() {
        return name + ": " + type;    
    }   
}