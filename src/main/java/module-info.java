module com.example.game2048 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.game2048 to javafx.graphics, javafx.fxml;
    exports com.example.game2048.model;
    exports com.example.game2048.controller;
    opens com.example.game2048.controller to javafx.fxml;
    exports com.example.game2048.view;
    opens com.example.game2048.view to javafx.fxml;
}