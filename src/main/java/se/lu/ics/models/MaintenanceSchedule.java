package se.lu.ics.models;

import java.time.LocalDate;

    public class MaintenanceSchedule {
        
        private LocalDate date;
        private String time;
        private Workshop workshop;
        private Vehicle vehicle;

        public MaintenanceSchedule(LocalDate date, String time){
            this.date = date;
            this.time = time;
        }

        public MaintenanceSchedule(LocalDate date, String time, Workshop workshop, Vehicle vehicle){
            this.date = date;
            this.time = time;
            this.workshop = workshop;
            this.vehicle = vehicle;
        }

        //Getters and Setters
        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Workshop getWorkshop() {
            return workshop;
        }

        public void setWorkshop(Workshop workshop) {
            this.workshop = workshop;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
        }
}