package com.example.databasemysqlproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class StaffInformationController {
    DatabaseConnection db = new DatabaseConnection();

    @FXML
    private Label labelInfoDisplay;

    @FXML
    private TextField textFieldID, textFieldLastName, textFieldFirstName, textFieldMI,
            textFieldAddress, textFieldCity, textFieldSate, textFieldTelephone, textFieldEmail;

    @FXML
    private Button buttonUpdate, buttonClear, buttonView, buttonInsert;


    public StaffInformationController() throws SQLException {
    }

    @FXML
    public void initialize() {
        labelInfoDisplay.setText("Database Connected");
    }

    @FXML
    protected void onViewButtonClick(ActionEvent event) throws SQLException {
        try {
            int staffID = Integer.parseInt(textFieldID.getText());
            textFieldLastName.setText(db.viewInfo(staffID)[0]);
            textFieldFirstName.setText(db.viewInfo(staffID)[1]);
            textFieldMI.setText(db.viewInfo(staffID)[2]);
            textFieldAddress.setText(db.viewInfo(staffID)[3]);
            textFieldCity.setText(db.viewInfo(staffID)[4]);
            textFieldSate.setText(db.viewInfo(staffID)[5]);
            textFieldTelephone.setText(db.viewInfo(staffID)[6]);
            textFieldEmail.setText(db.viewInfo(staffID)[7]);
            labelInfoDisplay.setText("Displaying...");
        } catch (NumberFormatException e) {
            labelInfoDisplay.setText("Please Enter ID");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    protected void onInsertButtonClick(ActionEvent event) {
        try {
            db.insertInfo(getTextFieldInfo());
            clearTextFields();
            labelInfoDisplay.setText("Staff info added");
        } catch (NumberFormatException e) {
            labelInfoDisplay.setText("Please Enter ID");
        } catch (SQLIntegrityConstraintViolationException e) {
            labelInfoDisplay.setText("Duplicate ID");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    protected void onUpdateButtonClick(ActionEvent event) {
        try {
            db.updateInfo(getTextFieldInfo());
            clearTextFields();
            labelInfoDisplay.setText("Staff info updated");
        } catch (NumberFormatException e) {
            labelInfoDisplay.setText("Please Enter ID");
        } catch (SQLIntegrityConstraintViolationException e) {
            labelInfoDisplay.setText("Duplicate ID");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    protected void onClearButtonClick(ActionEvent event) {
        this.textFieldID.setText("");
        clearTextFields();
        labelInfoDisplay.setText("Cleared");
    }

    protected Staff getTextFieldInfo(){
        return new Staff(Integer.parseInt(textFieldID.getText()),textFieldLastName.getText(),textFieldFirstName.getText(),
                textFieldMI.getText(),textFieldAddress.getText(), textFieldCity.getText(),
                textFieldSate.getText(),textFieldTelephone.getText(),textFieldEmail.getText());
    }

    void clearTextFields(){
        this.textFieldLastName.setText("");
        this.textFieldFirstName.setText("");
        this.textFieldMI.setText("");
        this.textFieldAddress.setText("");
        this.textFieldCity.setText("");
        this.textFieldSate.setText("");
        this.textFieldTelephone.setText("");
        this.textFieldEmail.setText("");
    }
}