package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Student;

import java.io.IOException;
import java.util.List;

public class LoginViewController {

    List<Student> studentList = DBConnection.getInstance().getConnection();

    @FXML
    private Button btnRegisterOnAction;

    @FXML
    private TextField txtEmailOrPhoneNumber;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) {

        String input = txtEmailOrPhoneNumber.getText();
        String password = txtPassword.getText();

        boolean isMatched = false;
        Student matchedStudent = null;

        for (Student student : studentList){
            if((student.getEmail().equals(input) || student.getStudentId().equals(input)) && student.getPassword().equals(password)){
                isMatched = true;
                matchedStudent = student;
                break;
            }
        }

        if(isMatched && matchedStudent != null){
            try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoardView.fxml"));
               Scene scene = new Scene(loader.load());

               DashBoardController controller = loader.getController();
               controller.setStudentData(matchedStudent);

               Stage stage = new Stage();
               stage.setScene(scene);
               stage.setTitle("Dashboard");
               stage.setResizable(false);
               stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Faild to load RegisterView.fxml");
            }
        }else{
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loin Failed");
            alert.setContentText("Invalid email/student ID or Password");
            alert.showAndWait();
        }

    }

    @FXML
    public void btnRegisterOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RegisterView.fxml"))));
            stage.setTitle("Student Registration Form");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Faild to load RegisterView.fxml");
        }
    }


}
