package db;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static DBConnection instance;
    private List<Student> studentList;

    private DBConnection(){
        studentList = new ArrayList<>();
    }

    public static DBConnection getInstance(){
        if(instance == null){
            return instance = new DBConnection();
        }
        return instance;
    }

    public List<Student> getConnection(){
        return studentList;
    }
}
