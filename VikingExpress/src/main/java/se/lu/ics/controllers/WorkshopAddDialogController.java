package se.lu.ics.controllers;
    
    import java.io.IOException;
    
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;

    import se.lu.ics.models.Workshop;
    import se.lu.ics.models.WorkshopRegister;
    import javafx.collections.FXCollections;
    import javafx.scene.control.TextField;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Alert.AlertType;
    import javafx.scene.control.ComboBox;

    public class WorkshopAddDialogController {
    
        private WorkshopRegister workshopRegister;

    @FXML
    private TextField textFieldWorkshopName;
    @FXML
    private TextField textFieldWorkshopAddress;
    @FXML
    private ComboBox<String> comboBoxWorkshopInfo;
    

    public void initialize() {
        comboBoxWorkshopInfo.setItems(FXCollections.observableArrayList("Internal", "External"));
        comboBoxWorkshopInfo.getSelectionModel().selectFirst();
    }
    
    
    @FXML
    private void handleButtonWorkshopSaveAction(ActionEvent event) 
    throws IOException { 
        String name = textFieldWorkshopName.getText();
        String address = textFieldWorkshopAddress.getText();
        String workshopInfo = comboBoxWorkshopInfo.getSelectionModel().getSelectedItem();

        if (name.isEmpty()) {
            showErrorPopup("Name can not be empty!");
        } else if (address.isEmpty()) {
            showErrorPopup("Address can not be empty!");
        } else {
        Workshop workshop = new Workshop(name, address, workshopInfo);
        workshopRegister.addWorkshop(workshop);
        
        textFieldWorkshopName.clear();
        textFieldWorkshopAddress.clear();
        comboBoxWorkshopInfo.getSelectionModel().clearSelection();
        comboBoxWorkshopInfo.getSelectionModel().selectFirst();
        }
    } 
    
    //Helper method to show error popup in Add Workshop popup
    private void showErrorPopup(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public void setWorkshopRegister(WorkshopRegister workshopRegister) {
        this.workshopRegister = workshopRegister;
    }   
}