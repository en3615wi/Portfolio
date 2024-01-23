package se.lu.ics.controllers;

    import se.lu.ics.models.MaintenanceSchedule;
    import se.lu.ics.models.MaintenanceScheduleRegister;
    import se.lu.ics.models.Vehicle;
    import se.lu.ics.models.VehicleRegister;
    import se.lu.ics.models.Workshop;
    import se.lu.ics.models.WorkshopRegister;

    import java.io.IOException;

    import java.time.LocalDate;
    import java.time.LocalTime;
    import java.time.format.DateTimeFormatter;
    import java.time.format.DateTimeParseException;

    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.stage.Stage;
    import javafx.scene.control.TextField;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Alert.AlertType;
    import javafx.scene.control.ComboBox;
    import javafx.scene.control.DatePicker;


    public class MaintenanceScheduleAddDialogController {

        private MaintenanceScheduleRegister maintenanceScheduleRegister;
        private VehicleRegister vehicleRegister;
        private WorkshopRegister workshopRegister;

    @FXML
    private ComboBox<Vehicle> comboBoxVehicle;
    @FXML
    private ComboBox<Workshop> comboBoxWorkshop;
    @FXML
    private DatePicker datePickerMaintenanceScheduleDate;
    @FXML
    private TextField textFieldMaintenanceScheduleTime;


    private void populateVehicleComboBox() {
        comboBoxVehicle.setItems(vehicleRegister.getVehicles());
        comboBoxVehicle.getSelectionModel().selectFirst();
    }
    
    private void populateWorkshopComboBox() {
        comboBoxWorkshop.setItems(workshopRegister.getWorkshops());
        comboBoxWorkshop.getSelectionModel().selectFirst();
    }

    public void setMaintenanceScheduleRegister(MaintenanceScheduleRegister maintenanceScheduleRegister) {
        this.maintenanceScheduleRegister = maintenanceScheduleRegister;
    }
    
    public void setVehicleRegister(VehicleRegister vehicleRegister) {
        this.vehicleRegister = vehicleRegister;
        populateVehicleComboBox();
    }

    public void setWorkshopRegister(WorkshopRegister workshopRegister) {
        this.workshopRegister = workshopRegister;
        populateWorkshopComboBox();
    }


    @FXML
    private void handleButtonMaintenanceScheduleSaveAction(ActionEvent event)
    throws IOException {
        Vehicle vehicle = comboBoxVehicle.getSelectionModel().getSelectedItem();
        Workshop workshop = comboBoxWorkshop.getSelectionModel().getSelectedItem();
        LocalDate date = datePickerMaintenanceScheduleDate.getValue();
        String time = textFieldMaintenanceScheduleTime.getText();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        if (date == null) {
            showErrorPopup("Date can not be empty!");
        } else if (date.isBefore(LocalDate.now())) {
            showErrorPopup("Date can not be in the past!");
        } else if (time.isEmpty()) {
            showErrorPopup("Time can not be empty!");
        } else {
            try {
                LocalTime.parse(time, timeFormatter);

                MaintenanceSchedule maintenanceSchedule = new MaintenanceSchedule(date, time, workshop, vehicle);
                maintenanceScheduleRegister.addMaintenanceSchedule(maintenanceSchedule);

                comboBoxVehicle.getSelectionModel().clearSelection();
                comboBoxVehicle.getSelectionModel().selectFirst();
                comboBoxWorkshop.getSelectionModel().clearSelection();
                comboBoxWorkshop.getSelectionModel().selectFirst();
                datePickerMaintenanceScheduleDate.setValue(null);
                textFieldMaintenanceScheduleTime.clear();
                
                Stage stage = (Stage) comboBoxVehicle.getScene().getWindow();
                stage.close();
                Stage stageWorkshop = (Stage) comboBoxWorkshop.getScene().getWindow();
                stageWorkshop.close();
            } catch (DateTimeParseException e) {
                showErrorPopup("Time must be in the format hh:mm!");
            }
        }
    }

    private void showErrorPopup(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}