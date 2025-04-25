package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String studentId;
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
    private String dateOfBirth;
    private String gender;
}
