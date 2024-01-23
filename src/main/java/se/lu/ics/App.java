package se.lu.ics;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.lu.ics.controllers.MainViewController;
import se.lu.ics.models.MaintenanceScheduleRegister;
import se.lu.ics.models.ServiceHistoryRegister;
import se.lu.ics.models.VehicleRegister;
import se.lu.ics.models.WorkshopRegister;

public class App extends Application {

    public static void main(String[] args) {
        launch();        
    }

    // The start method is the main entry point for all JavaFX applications.
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        VehicleRegister vehicleRegister = new VehicleRegister();
        WorkshopRegister workshopRegister = new WorkshopRegister();
        MaintenanceScheduleRegister maintenanceScheduleRegister = new MaintenanceScheduleRegister();
        ServiceHistoryRegister serviceHistoryRegister = new ServiceHistoryRegister();

        // Get the location of the MainView.fxml file which defines the layout of the GUI
        // The location is relative to the root of the classpath (target/classes)
        URL location = getClass().getResource("/fxml/MainView.fxml");

        // Create a new FXMLLoader with the location of the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        // Create a scene with the root node and set it on the primary stage
        Scene scene = new Scene(fxmlLoader.load());

        // Get the controller associated with the FXML file
        // that has been automatically instantiated by the FXMLLoader
        MainViewController controller = fxmlLoader.getController();

        controller.setVehicleRegister(vehicleRegister);
        controller.setWorkshopRegister(workshopRegister);
        controller.setMaintenanceScheduleRegister(maintenanceScheduleRegister);
        controller.setServiceHistoryRegister(serviceHistoryRegister);

        primaryStage.setScene(scene);
        // Set the title of the primary stage (window title) and display the stage
        primaryStage.setTitle("Viking Express");
        primaryStage.show();
    }
}