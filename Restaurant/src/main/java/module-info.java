module com.restaurant {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.restaurant to javafx.fxml;
    exports com.restaurant;
}