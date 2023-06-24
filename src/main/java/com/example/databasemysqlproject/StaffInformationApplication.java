package com.example.databasemysqlproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class StaffInformationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StaffInformationApplication.class.getResource("staffInformation.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);
        stage.setResizable(false);
        stage.setTitle("StaffInfo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}


