module com.aptech.mavenproject2.petclinic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires java.desktop;
//    requires pdfbox;
    requires java.base;
    requires javafx.graphics;
    
    opens com.aptech.mavenproject2.petclinic to javafx.fxml;
    exports com.aptech.mavenproject2.petclinic;
    
    opens Models to javafx.fxml;
    exports Models;
    
    opens Controller to javafx.fxml;
    exports Controller;
    
    opens Controller.Admin to javafx.fxml;
    exports Controller.Admin;
    
    opens Controller.Customer to javafx.fxml;
    exports Controller.Customer;
    
    opens Controller.Doctor to javafx.fxml;
    exports Controller.Doctor;
    
    opens Controller.SignController to javafx.fxml;
    exports Controller.SignController;
    
    opens Validation to javafx.fxml;
    exports Validation;
}
