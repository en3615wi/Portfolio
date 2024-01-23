package se.lu.ics.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkshopRegister {

    private ObservableList<Workshop> workshops;

    public WorkshopRegister() {
       this.workshops = FXCollections.observableArrayList();
       addWorkshopTestData();
   }

    public ObservableList<Workshop> getWorkshops() {
        return FXCollections.unmodifiableObservableList(this.workshops);
    }

    public void addWorkshop(Workshop workshop) {
        this.workshops.add(workshop);
    }

    public void removeWorkshop(Workshop workshop) {
        this.workshops.remove(workshop);
    }

    private void addWorkshopTestData() {
            
        Workshop workshop1 = new Workshop("Viking Express Göteborg", "Clemens falafelgränd 2, Göteborg", "Internal");
        Workshop workshop2 = new Workshop("Viking Express Malmö", "Amiralsgatan 21, Malmö", "Internal");
        Workshop workshop3 = new Workshop("Viking Express Sundsvall", "Knutpunktsvägen 60, Sundsvall", "Internal");
        Workshop workshop4 = new Workshop("Speedy Bilservice", "Lundavägen 13, Lund", "External");
    
        addWorkshop(workshop1);
        addWorkshop(workshop2);
        addWorkshop(workshop3);
        addWorkshop(workshop4);
    }
}