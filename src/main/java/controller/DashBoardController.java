package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Student;

import java.io.IOException;

public class DashBoardController {

    @FXML
    private Label lblDateOfBirth;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFullName;

    @FXML
    private Label lblGender;

    @FXML
    private Label lblStudentId;

    @FXML
    private Label lblDashboard;

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setStudentData(Student student) {
        lblDashboard.setText("Hello "+student.getFullName()+"!");
        lblStudentId.setText(student.getStudentId());
        lblFullName.setText(student.getFullName());
        lblEmail.setText(student.getEmail());
        lblGender.setText(student.getGender());
        lblDateOfBirth.setText(student.getDateOfBirth());
    }
}
