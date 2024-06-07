module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens application to javafx.fxml; // Gives access to fxml files
    exports application;
    //opens application to javafx.fxml; // Exports the class inheriting from javafx.application.Application
}