module com.restaurant {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.restaurant;
    exports com.restaurant;
    exports com.restaurant.restaurante;
    opens com.restaurant.restaurante;
}