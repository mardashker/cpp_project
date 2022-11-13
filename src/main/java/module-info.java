module com.example.railwaystation {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires annotations;


    opens com.example.railwaystation to javafx.fxml;
    exports com.example.railwaystation.controllers;
    opens com.example.railwaystation.controllers to javafx.fxml;
    exports com.example.railwaystation;
}