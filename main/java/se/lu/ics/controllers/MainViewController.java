package se.lu.ics.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.util.StringConverter;

import javafx.event.ActionEvent;

import se.lu.ics.models.Vehicle;
import se.lu.ics.models.VehicleRegister;
import se.lu.ics.models.Workshop;
import se.lu.ics.models.WorkshopRegister;
import se.lu.ics.models.MaintenanceSchedule;
import se.lu.ics.models.MaintenanceScheduleRegister;
import se.lu.ics.models.ServiceHistory;
import se.lu.ics.models.ServiceHistoryRegister;

    
    public class MainViewController {

        private VehicleRegister vehicleRegister;
        private WorkshopRegister workshopRegister;
        private MaintenanceScheduleRegister maintenanceScheduleRegister;
        private ServiceHistoryRegister serviceHistoryRegister;
    

    //FXML for Vehicle tab
    @FXML
    private TableView<Vehicle> tableViewVehicle;
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleId;
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleName;
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleType;
    @FXML
    private TableColumn<Vehicle, String> tableColumnVehicleLocation;
    @FXML
    private TableColumn<Vehicle, Integer> tableColumnVehicleCapacity;

    //FXML for Workshop tab
    @FXML
    private TableView<Workshop> tableViewWorkshop;
    @FXML
    private TableColumn<Workshop, String> tableColumnWorkshopName;
    @FXML
    private TableColumn<Workshop, String> tableColumnWorkshopAddress;
    @FXML
    private TableColumn<Workshop, String> tableColumnWorkshopInfo;

    //FXML for Maintenance tab
    @FXML
    private TableView<MaintenanceSchedule> tableViewMaintenanceSchedule; 
    @FXML
    private TableColumn<MaintenanceSchedule, Vehicle> tableColumnMaintenanceScheduleVehicle;
    @FXML
    private TableColumn<MaintenanceSchedule, Workshop> tableColumnMaintenanceScheduleWorkshop;
    @FXML
    private TableColumn<MaintenanceSchedule, String> tableColumnMaintenanceScheduleDate;
    @FXML
    private TableColumn<MaintenanceSchedule, String> tableColumnMaintenanceScheduleTime;

    //FXML for Service History tab
    @FXML
    private TableView<ServiceHistory> tableViewServiceHistory;
    @FXML
    private TableColumn<ServiceHistory, String> tableColumnServiceHistoryVehicle;
    @FXML
    private TableColumn<ServiceHistory, String> tableColumnServiceHistoryWorkshop;
    @FXML
    private TableColumn<ServiceHistory, String> tableColumnServiceHistoryDate;
    @FXML
    private TableColumn<ServiceHistory, Integer> tableColumnServiceHistoryPartsReplaced;
    @FXML
    private TableColumn<ServiceHistory, Double> tableColumnServiceHistoryCost;
    @FXML
    private TableColumn<ServiceHistory, String> tableColumnServiceHistoryProblemDescription;


    //Vehicle tab
    @FXML 
    private Button buttonAddVehicle;
    @FXML
    private AnchorPane deleteVehiclePopup;

    //Workshop tab
    @FXML
    private Button buttonAddWorkshop;
    @FXML
    private AnchorPane deleteWorkshopPopup;

    //Maintenance tab
    @FXML
    private Button buttonAddMaintenance;
    @FXML
    private AnchorPane deleteMaintenancePopup;

    //Servie History tab
    @FXML
    private Button buttonAddService;
    @FXML
    private AnchorPane deleteServicePopup;

    @FXML
    private Label labelResponse;


public void initialize() {
    //Vehicle table
    tableColumnVehicleId.setCellValueFactory(new PropertyValueFactory<>("vin"));
    tableColumnVehicleName.setCellValueFactory(new PropertyValueFactory<>("name"));
    tableColumnVehicleType.setCellValueFactory(new PropertyValueFactory<>("type"));
    tableColumnVehicleLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
    tableColumnVehicleLocation.setCellFactory(TextFieldTableCell.forTableColumn());
    tableColumnVehicleLocation.setOnEditCommit(event -> { 
        Vehicle vehicle = event.getRowValue();
        vehicle.setLocation(event.getNewValue());
    });
    tableColumnVehicleCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

    //Workshop table
    tableColumnWorkshopName.setCellValueFactory(new PropertyValueFactory<>("name"));
    tableColumnWorkshopAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    tableColumnWorkshopAddress.setCellFactory(TextFieldTableCell.forTableColumn());
    tableColumnWorkshopAddress.setOnEditCommit(event -> { 
        Workshop workshop = event.getRowValue();
        workshop.setAddress(event.getNewValue());
    });
    tableColumnWorkshopInfo.setCellValueFactory(new PropertyValueFactory<>("workshopInfo"));

    //MaintenanceSchedule table
    tableColumnMaintenanceScheduleVehicle.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
    tableColumnMaintenanceScheduleWorkshop.setCellValueFactory(new PropertyValueFactory<>("workshop"));
    tableColumnMaintenanceScheduleDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    tableColumnMaintenanceScheduleTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    tableColumnMaintenanceScheduleTime.setCellFactory(TextFieldTableCell.forTableColumn());
    tableColumnMaintenanceScheduleTime.setOnEditCommit(event -> {
        MaintenanceSchedule maintenanceSchedule = event.getRowValue();
        maintenanceSchedule.setTime(event.getNewValue());
    });

    //ServiceHistory table
    tableColumnServiceHistoryVehicle.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
    tableColumnServiceHistoryWorkshop.setCellValueFactory(new PropertyValueFactory<>("workshop"));
    tableColumnServiceHistoryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    tableColumnServiceHistoryPartsReplaced.setCellValueFactory(new PropertyValueFactory<>("partsReplaced"));
    tableColumnServiceHistoryCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
    tableColumnServiceHistoryCost.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
        @Override
        public String toString(Double object) {
            return String.format("%.2f", object);
        }

        @Override
        public Double fromString(String string) {
            try {
                labelResponse.setVisible(false);
                return Double.parseDouble(string);
            } catch (NumberFormatException e) {
                String errorMessage = "Invalid input format. Please enter a number.";
                labelResponse.setText(errorMessage);
                labelResponse.setVisible(true);
                return 0.0;
            }
        }
    }));
    
    tableColumnServiceHistoryCost.setOnEditCommit(event -> {
        ServiceHistory serviceHistory = event.getRowValue();
        Double newCost = event.getNewValue();

        // Check if a new cost was entered
        if (newCost != null) {
            serviceHistory.setCost(newCost);
        }
    });

    tableColumnServiceHistoryProblemDescription.setCellValueFactory(new PropertyValueFactory<>("problemDescription"));
    tableColumnServiceHistoryProblemDescription.setCellFactory(TextFieldTableCell.forTableColumn());
    tableColumnServiceHistoryProblemDescription.setOnEditCommit(event -> {
        ServiceHistory serviceHistory = event.getRowValue();
        serviceHistory.setProblemDescription(event.getNewValue());
    });


    //Delete button popups
    deleteVehiclePopup.setVisible(false);
    deleteWorkshopPopup.setVisible(false);
    deleteServicePopup.setVisible(false);
    deleteMaintenancePopup.setVisible(false);
}



    //FXML for Vehicle tab
    //Add vehicle
    @FXML
    private void handleButtonAddVehicleAction(ActionEvent event) {
        try { 
            // Load the FXML file (the layout of the GUI)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/VehicleAddDialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(fxmlLoader.load()));

            VehicleAddDialogController controller = fxmlLoader.getController();
            controller.setVehicleRegister(vehicleRegister);
            controller.setWorkshopRegister(workshopRegister);
    
            dialogStage.setTitle("Add Vehicle");
            Stage currentStage = (Stage) buttonAddVehicle.getScene().getWindow();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(currentStage);
            dialogStage.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Delete vehicle
    @FXML
    private void handleButtonDeleteVehicleAction(ActionEvent event) {
        deleteVehiclePopup.setVisible(true);
    }

    @FXML
    private void handleButtonDeleteVehicleYes(ActionEvent event) {
        Vehicle vehicle = tableViewVehicle.getSelectionModel().getSelectedItem();
        vehicleRegister.removeVehicle(vehicle);
        
        deleteVehiclePopup.setVisible(false);
    }

    @FXML
    private void handleButtonDeleteVehicleNo(ActionEvent event) {
        deleteVehiclePopup.setVisible(false);
    }


    //FXML for Workshop tab
    //Add workshop
    @FXML
    private void handleButtonAddWorkshopAction(ActionEvent event) {
        try { 
            // Load the FXML file (the layout of the GUI)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/WorkshopAddDialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(fxmlLoader.load()));

            WorkshopAddDialogController controller = fxmlLoader.getController();
            controller.setWorkshopRegister(workshopRegister);
    
            dialogStage.setTitle("Add Workshop");
            Stage currentStage = (Stage) buttonAddWorkshop.getScene().getWindow();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(currentStage);
            dialogStage.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Delete workshop
    @FXML
    private void handleButtonDeleteWorkshopAction(ActionEvent event) {
        deleteWorkshopPopup.setVisible(true);
    }

    @FXML
    private void handleButtonDeleteWorkshopYes(ActionEvent event) {
        Workshop workshop = tableViewWorkshop.getSelectionModel().getSelectedItem();
        workshopRegister.removeWorkshop(workshop);
        
        deleteWorkshopPopup.setVisible(false);
    }

    @FXML
    private void handleButtonDeleteWorkshopNo(ActionEvent event) {
        deleteWorkshopPopup.setVisible(false);
    }


    //FXML for Maintenance tab
    //Add maintenance
    @FXML
    private void handleButtonAddMaintenanceAction(ActionEvent event) {
        try { 
            // Load the FXML file (the layout of the GUI)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MaintenanceScheduleAddDialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(fxmlLoader.load()));

            MaintenanceScheduleAddDialogController controller = fxmlLoader.getController();
            controller.setMaintenanceScheduleRegister(maintenanceScheduleRegister);
            controller.setVehicleRegister(vehicleRegister);
            controller.setWorkshopRegister(workshopRegister);
    
            dialogStage.setTitle("Add Maintenance");
            Stage currentStage = (Stage) buttonAddMaintenance.getScene().getWindow();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(currentStage);
            dialogStage.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Delete maintenance
    @FXML
    private void handleButtonDeleteMaintenanceAction(ActionEvent event) {
        deleteMaintenancePopup.setVisible(true);
    }

    @FXML
    private void handleButtonDeleteMaintenanceYes(ActionEvent event) {
        MaintenanceSchedule maintenanceSchedule = tableViewMaintenanceSchedule.getSelectionModel().getSelectedItem();
        maintenanceScheduleRegister.removeMaintenanceSchedule(maintenanceSchedule);

        deleteMaintenancePopup.setVisible(false);
    }

    @FXML
    private void handleButtonDeleteMaintenanceNo(ActionEvent event) {
        deleteMaintenancePopup.setVisible(false);
    }


    //FXML for Service History tab
    //Add service
    @FXML
    private void handleButtonAddServiceHistoryAction(ActionEvent event) {
        try {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ServiceHistoryAddDialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(fxmlLoader.load()));

            ServiceHistoryAddDialogController controller = fxmlLoader.getController();
            controller.setServiceHistoryRegister(serviceHistoryRegister);
            controller.setVehicleRegister(vehicleRegister);
            controller.setWorkshopRegister(workshopRegister);
    
            dialogStage.setTitle("Add Service History");
            Stage currentStage = (Stage) buttonAddService.getScene().getWindow();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(currentStage);
            dialogStage.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML //Button to calculate total cost of a vehicle by selecting a vehicle
    private void handleButtonTotalVehicleCostAction(ActionEvent event) {
        ServiceHistory selectedServiceHistory = tableViewServiceHistory.getSelectionModel().getSelectedItem();
    
        if (selectedServiceHistory != null) {
            double totalCost = selectedServiceHistory.getVehicle().getTotalServiceHistoryCost();
            labelResponse.setText("Total cost for this vehicle is: " + totalCost);
        } else {
            labelResponse.setText("Please select a vehicle.");
        }
    }

    @FXML //Button to calculate total cost of all vehicles
    private void handleButtonTotalAllVehiclesCostAction(ActionEvent event) {
        double totalCost = 0;
        for (Vehicle vehicle : vehicleRegister.getVehicles()) {
            totalCost += vehicle.getTotalServiceHistoryCost();
        }
        labelResponse.setText("Total cost for all vehicles is: " + totalCost);
    }

    @FXML //Button to calculate total parts replaced for a vehicle by selecting a vehicle
    private void handleButtonTotalPartsReplacedVehicleAction(ActionEvent event) {
        ServiceHistory selectedServiceHistory = tableViewServiceHistory.getSelectionModel().getSelectedItem();
    
        if (selectedServiceHistory != null) {
            int totalPartsReplaced = selectedServiceHistory.getVehicle().getTotalPartsReplaced();
            labelResponse.setText("Total parts replaced for this vehicle is: " + totalPartsReplaced);
        } else {
            labelResponse.setText("Please select a vehicle.");
        }
    }

    @FXML //Button to calculate average cost of all service history
    private void handleButtonAverageServiceHistoryCostAction(ActionEvent event) {
        double totalCost = 0;
        for (ServiceHistory serviceHistory : serviceHistoryRegister.getServiceHistory()) {
            totalCost += serviceHistory.getCost();
        }
        double averageCost = totalCost / serviceHistoryRegister.getServiceHistory().size();
        labelResponse.setText("Average cost for all service history is: " + averageCost);
    }
    

    //Delete service
    @FXML
    private void handleButtonDeleteServiceHistoryAction(ActionEvent event) {
        deleteServicePopup.setVisible(true);
    }

    @FXML
    private void handleButtonDeleteServiceHistoryYes(ActionEvent event) {
        ServiceHistory serviceHistory = tableViewServiceHistory.getSelectionModel().getSelectedItem();
        serviceHistoryRegister.removeServiceHistory(serviceHistory);

        deleteServicePopup.setVisible(false);
    }

    @FXML
    private void handleButtonDeleteServiceHistoryNo(ActionEvent event) {
        deleteServicePopup.setVisible(false);
    }


    //Populate TableView
    private void populateTableView() {
        tableViewVehicle.getItems().clear();
        tableViewVehicle.setItems(vehicleRegister.getVehicles());
    }

    private void populateWorkshopTableView() {
        tableViewWorkshop.getItems().clear();
        tableViewWorkshop.setItems(workshopRegister.getWorkshops());
    }

    private void populateMaintenanceScheduleTableView() {
        tableViewMaintenanceSchedule.getItems().clear();
        tableViewMaintenanceSchedule.setItems(maintenanceScheduleRegister.getMaintenanceSchedules());
    }

    private void populateServiceHistoryTableView() {
        tableViewServiceHistory.getItems().clear();
        tableViewServiceHistory.setItems(serviceHistoryRegister.getServiceHistory());
    }

    //Setters for all registers
    public void setVehicleRegister(VehicleRegister vehicleRegister) {
        this.vehicleRegister = vehicleRegister;
        populateTableView();
    }   

    public void setWorkshopRegister(WorkshopRegister workshopRegister) {
        this.workshopRegister = workshopRegister;
        populateWorkshopTableView();
    }

    public void setMaintenanceScheduleRegister(MaintenanceScheduleRegister maintenanceScheduleRegister) {
        this.maintenanceScheduleRegister = maintenanceScheduleRegister;
        populateMaintenanceScheduleTableView();
    }

    public void setServiceHistoryRegister(ServiceHistoryRegister serviceHistoryRegister) {
        this.serviceHistoryRegister = serviceHistoryRegister;
        populateServiceHistoryTableView();
    }
} 