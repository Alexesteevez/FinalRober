module com.example.finalrober {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.finalrober to javafx.fxml;
    exports com.example.finalrober;
}