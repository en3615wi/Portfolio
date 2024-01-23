package se.lu.ics.controllers;

    import java.io.IOException;
    import java.util.Random;

    import javafx.collections.FXCollections;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Alert.AlertType;
    import javafx.scene.control.ComboBox;
    import javafx.scene.control.TextField;
    import se.lu.ics.models.Vehicle;
    import se.lu.ics.models.VehicleRegister;
    import se.lu.ics.models.Workshop;
    import se.lu.ics.models.WorkshopRegister;


    public class VehicleAddDialogController {

        private VehicleRegister vehicleRegister;
        private WorkshopRegister workshopRegister;


    @FXML
    private TextField textFieldVehicleName;
    @FXML
    private ComboBox<String> comboBoxVehicleType;
    @FXML
    private TextField textFieldVehicleLocation;
    @FXML
    private TextField textFieldVehicleCapacity;
    @FXML
    private ComboBox<Workshop> comboBoxVehicleWorkshop;


    private void populateComboBox() {
        comboBoxVehicleWorkshop.setItems(workshopRegister.getWorkshops());
        comboBoxVehicleWorkshop.getSelectionModel().selectFirst();
    }

    public void setVehicleRegister(VehicleRegister vehicleRegister) {
        this.vehicleRegister = vehicleRegister;
    }

    public void setWorkshopRegister(WorkshopRegister workshopRegister) {
        this.workshopRegister = workshopRegister;
        populateComboBox();
    }


    public void initialize() {
        comboBoxVehicleType.setItems(FXCollections.observableArrayList("Large Truck", "Medium Truck", "Van"));
        comboBoxVehicleType.getSelectionModel().selectFirst();
    }
        

    @FXML
    private void handleButtonVehicleSaveAction(ActionEvent event) throws IOException {
        String vin = generateRandomVIN();
        String name = textFieldVehicleName.getText();
        String type = comboBoxVehicleType.getSelectionModel().getSelectedItem();
        String location = textFieldVehicleLocation.getText();
        String capacityText = textFieldVehicleCapacity.getText();
    
        if (capacityText.isEmpty()) {
            showErrorPopup("Capacity can not be empty");
        } else {
            try {
                int capacity = Integer.parseInt(capacityText);
    
                if (capacity < 1) {
                    showErrorPopup("Capacity can not be less than 1!");
                } else if (name.isEmpty()) {
                    showErrorPopup("Name can not be empty!");
                } else if (location.isEmpty()) {
                    showErrorPopup("Location can not be empty!");
                } else if (type.isEmpty()) {
                    showErrorPopup("Type can not be empty!");
                } else if (comboBoxVehicleWorkshop.getSelectionModel().isEmpty()) {
                    showErrorPopup("Workshop can not be empty!");
                } else {
                    Workshop selectedWorkshop = comboBoxVehicleWorkshop.getSelectionModel().getSelectedItem();
    
                    // Check if the selected workshop type is compatible with the vehicle type
                    if ((type.equals("Large Truck") && selectedWorkshop.getWorkshopInfo().equals("Internal"))) {
                        showErrorPopup("A large truck can not be serviced at an internal workshop!");
                    } else {
                        Vehicle vehicle = new Vehicle(vin, name, type, location, capacity);
                        vehicleRegister.addVehicle(vehicle);
    
                        textFieldVehicleName.clear();
                        textFieldVehicleLocation.clear();
                        textFieldVehicleCapacity.clear();
                        comboBoxVehicleType.getSelectionModel().clearSelection();
                        comboBoxVehicleType.getSelectionModel().selectFirst();
                    }
                }
            } catch (NumberFormatException e) {
                showErrorPopup("Invalid input for capacity. Please enter a valid number.");
            }
        }
    }
    //Helper method to show error popup in Add Vehicle popup
    private void showErrorPopup(String message) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
    }


    private String generateRandomVIN() {
        String VIN_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String VIN_DIGITS = "0123456789";
        int VIN_LETTER_COUNT = 3;
        int VIN_DIGIT_COUNT = 3;

        StringBuilder vinBuilder = new StringBuilder();

        // Add random letters
        for (int i = 0; i < VIN_LETTER_COUNT; i++) {
            vinBuilder.append(getRandomCharacter(VIN_LETTERS));
        }

        // Add random digits
        for (int i = 0; i < VIN_DIGIT_COUNT; i++) {
            vinBuilder.append(getRandomCharacter(VIN_DIGITS));
        }

        return vinBuilder.toString();
    }

    // Helper method to get a random character from a source string
    private char getRandomCharacter(String source) {
        Random random = new Random();
        int randomIndex = random.nextInt(source.length());
        return source.charAt(randomIndex);
    }
}