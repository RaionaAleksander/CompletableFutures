module com.example.completablefutures {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.completablefutures to javafx.fxml;
    exports com.example.completablefutures;
}