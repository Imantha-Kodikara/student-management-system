package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Student;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistrationFormController implements Initializable {

    List<Student> studentList = DBConnection.getInstance().getConnection();

    @FXML
    private Label lblStudentId;

    @FXML
    private RadioButton radioBtnFemale;

    @FXML
    private RadioButton radioBtnMale;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private DatePicker txtDateOfBirth;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String studentId = generateStudentId();
        lblStudentId.setText(studentId);
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String studentId = lblStudentId.getText();
        String fullName = txtFullName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        LocalDate birthday = txtDateOfBirth.getValue();
        boolean isMale = radioBtnMale.isSelected();
        boolean isFemale = radioBtnFemale.isSelected();

        if(!isValidName(fullName)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Required");
            alert.setContentText("Full name is required");
            alert.showAndWait();
            return;
        }

        if(!isValidEmail(email)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Valid Email Required");
            alert.setContentText("Please enter an valid email");
            alert.showAndWait();
            txtEmail.setText("");
            return;
        }

        if(!isValidPassword(password, confirmPassword)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Passwords do not match");
            alert.setContentText("Oops! The passwords don't match.");
            alert.showAndWait();
            txtPassword.setText("");
            txtConfirmPassword.setText("");
            return;
        }

        if(!isValidBirthday(birthday)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Valid Birthday required");
            alert.setContentText("Please add a valid Birthday");
            alert.showAndWait();
            return;
        }

        if(!isValidGender(isMale, isFemale)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Select Gender");
            alert.setContentText("Please add your gender");
            alert.showAndWait();
            return;
        }
        String gender = "Female";
        if(isMale){
            gender = "Male";
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you want to proceed?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Student student = new Student(studentId, fullName, email, password,confirmPassword, birthday.toString(),gender);
            studentList.add(student);

            lblStudentId.setText(generateStudentId());
            txtFullName.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            txtConfirmPassword.setText("");
        }else{
            lblStudentId.setText(generateStudentId());
            txtFullName.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            txtConfirmPassword.setText("");
        }

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setContentText("Student Added Successfully");
        alert1.showAndWait();

        System.out.println(studentList);
    }



    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


    public String generateStudentId(){
        int newIdNumber = studentList.size() + 1;
        String studentId = String.format("STU%04d", newIdNumber);
        return studentId;
    }

    public boolean isValidName(String fullName){
        return (fullName != null && !fullName.isEmpty());
    }

    public boolean isValidEmail(String email){
        if(email == null || email.isEmpty()) return false;
        if(!email.contains("@") || !email.contains(".")) return false;
        return true;
    }

    public boolean isValidPassword(String password, String confirmPassword){
        if(password == null || confirmPassword == null) return false;
        if(!(password.equals(confirmPassword))) return false;
        return true;
    }

    public boolean isValidBirthday(LocalDate dateOfBirth){
        if(dateOfBirth == null) return false;
        if(dateOfBirth.isAfter(LocalDate.now())) return false;
        return true;
    }

    private boolean isValidGender(boolean isMale, boolean isFemale) {
        if(isMale && isFemale) return false;
        if(!isMale && !isFemale) return false;
        return true;
    }



}
