module com.example.railwaystation {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires annotations;


    opens com.example.railwaystation to javafx.fxml;
    exports com.example.railwaystation;
//    opens com.example.railwaystation.refactored_classes.UI to javafx.fxml;
    exports com.example.railwaystation.refactored_classes.UI.Controllers;
    opens com.example.railwaystation.refactored_classes.UI.Controllers to javafx.fxml;
}