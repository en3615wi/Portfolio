package se.lu.ics.controllers;

import se.lu.ics.models.ServiceHistory;
import se.lu.ics.models.ServiceHistoryRegister;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.VehicleRegister;
import se.lu.ics.models.Workshop;
import se.lu.ics.models.WorkshopRegister;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ServiceHistoryAddDialogController {

    private ServiceHistoryRegister serviceHistoryRegister;
    private VehicleRegister vehicleRegister;
    private WorkshopRegister workshopRegister;

    @FXML
    private TextField textFieldVehicle;

    @FXML
    private ComboBox<Vehicle> comboBoxVehicle;

    @FXML
    private ComboBox<Workshop> comboBoxWorkshop;

    @FXML
    private DatePicker datePickerServiceHistoryDate;

    @FXML
    private TextField textFieldPartsReplaced;

    @FXML
    private TextField textFieldCost;

    @FXML
    private TextField textFieldProblemDescription;



    private void populateVehicleComboBox() {
        comboBoxVehicle.setItems(vehicleRegister.getVehicles());
        comboBoxVehicle.getSelectionModel().selectFirst();
    }

    private void populateWorkshopComboBox() {
        comboBoxWorkshop.setItems(workshopRegister.getWorkshops());
        comboBoxWorkshop.getSelectionModel().selectFirst();
    }

    void setServiceHistoryRegister(ServiceHistoryRegister serviceHistoryRegister) {
        this.serviceHistoryRegister = serviceHistoryRegister;
    }

    void setVehicleRegister(VehicleRegister vehicleRegister) {
        this.vehicleRegister = vehicleRegister;
        populateVehicleComboBox();
    }

    void setWorkshopRegister(WorkshopRegister workshopRegister) {
        this.workshopRegister = workshopRegister;
        populateWorkshopComboBox();
    }

    @FXML
    private void handleButtonServiceHistorySaveAction(ActionEvent event) throws IOException {
        try {
            Vehicle vehicle = comboBoxVehicle.getSelectionModel().getSelectedItem();
            Workshop workshop = comboBoxWorkshop.getSelectionModel().getSelectedItem();
            LocalDate serviceDate = datePickerServiceHistoryDate.getValue();

            String partsReplacedText = textFieldPartsReplaced.getText();
            String costText = textFieldCost.getText();

            if (serviceDate == null) {
                showErrorPopup("Date can not be empty!");
            } else if (serviceDate.isAfter(LocalDate.now())) {
                showErrorPopup("Date can not be in the future!");
            } else if (partsReplacedText.isEmpty()) {
                showErrorPopup("Must enter number of parts replaced!");
            } else if (costText.isEmpty()) {
                showErrorPopup("Must enter cost of service!");
            } else {
                Integer partsReplaced = Integer.parseInt(partsReplacedText);
                Double cost = Double.parseDouble(costText);
                String problemDescription = textFieldProblemDescription.getText();

                if (partsReplaced <= 0) {
                    showErrorPopup("Must enter a valid number of parts replaced!");
                } else if (partsReplaced > 100) {
                    showErrorPopup("A vehicle cannot have more than 100 parts replaced!");
                } else if (cost <= 0.0) {
                        showErrorPopup("Must enter a valid cost of service!");
                } else if (problemDescription.isEmpty()) {
                        showErrorPopup("Must make a description of the problem!");
                } else {
                    ServiceHistory serviceHistory = new ServiceHistory(vehicle, serviceDate, partsReplaced, cost, workshop, problemDescription);

                    if ((vehicle.getTotalPartsReplaced() + Integer.parseInt(textFieldPartsReplaced.getText())) > 100) {
                    showErrorPopup("This vehicle has exceeded the maximum amount of parts allowed to be replaced (100) by " + ((vehicle.getTotalPartsReplaced() + Integer.parseInt(textFieldPartsReplaced.getText())) - 100) + " parts. This vehicle should be decommissioned. Remember to remove the vehicle from the vehicle tab.");
                    } else if ((vehicle.getTotalServiceHistoryCost() + Integer.parseInt(textFieldCost.getText())) > 100000) {
                        boolean continueWithProcess = showConfirmationPopup("Warning: Total cost of servicing this vehicle exceeds 100,000");
                        if (continueWithProcess) {
                            serviceHistoryRegister.addServiceHistory(serviceHistory);

                            vehicle.addPartsReplaced(partsReplaced);
                            vehicle.addServiceHistoryCost(cost);

                            resetFieldsAndSelections(); //Method to clear all fields and selections after adding a vehicle
                        }
                    } else {
                        serviceHistoryRegister.addServiceHistory(serviceHistory);

                        vehicle.addPartsReplaced(partsReplaced);
                        vehicle.addServiceHistoryCost(cost);

                        resetFieldsAndSelections(); //Method to clear all fields and selections after adding a vehicle
                        } 
                    }
                }
            } catch (NumberFormatException e) {
                showErrorPopup("Invalid input for Parts Replaced or Cost. Please enter valid numbers.");
        }
    }

    private void showErrorPopup(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean showConfirmationPopup(String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType continueButton = new ButtonType("Continue");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(continueButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.orElse(cancelButton) == continueButton;
    }

    private void resetFieldsAndSelections() {
        comboBoxVehicle.getSelectionModel().clearSelection();
        comboBoxVehicle.getSelectionModel().selectFirst();
        comboBoxWorkshop.getSelectionModel().clearSelection();
        comboBoxWorkshop.getSelectionModel().selectFirst();
        datePickerServiceHistoryDate.setValue(null);
        textFieldPartsReplaced.clear();
        textFieldCost.clear();
        textFieldProblemDescription.clear();
    }
}