module com.example.databasemysqlproject {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.databasemysqlproject to javafx.fxml;
    exports com.example.databasemysqlproject;
}