module is.project {
    
    exports se.lu.ics;
    exports se.lu.ics.controllers;
    exports se.lu.ics.models;

    opens se.lu.ics.controllers to javafx.fxml;

    opens se.lu.ics.models to javafx.base;

    requires javafx.fxml;

    requires javafx.controls;

    requires transitive javafx.graphics;
}

