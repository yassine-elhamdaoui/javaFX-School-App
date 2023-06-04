package controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DatabaseManager;
import model.Student;
import model.AlertBox;

public class Handler {

        static DatabaseManager databaseManager;
        static TableView<Student> studentTableView;
        private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9]+@gmail+\\.com");
        // Method to save the student's information to the database or data storage
        public static void saveStudentToDatabase(String fname, String lname, int age, String cin ,String class_grade,String email,String infos,String imagePath) {

            databaseManager = new DatabaseManager();
            boolean isconnected = databaseManager.connect();
            System.out.println(isconnected);
            if (isconnected) {
                Connection connection = databaseManager.getConnection();
                System.out.println("hana dkhlt");
                if (connection != null) {
                    System.out.println("hani dkhalt tani");
                    try {
                        Statement stmt = connection.createStatement();
                        System.out.println("hiii");
                        // Prepare the SQL statement
                        String sql = "INSERT INTO students (fname,lname,age,cin,class_grade,email,infos,image) VALUES ('" + fname + "', '" + lname + "','" + age + "','" + cin + "','" + class_grade + "','" + email + "','" + infos + "','" + imagePath + "')";
                        System.out.println("hii tani");
                        stmt.executeUpdate(sql);
                        
                        // Clear the existing data in the TableView
                        studentTableView.getItems().clear();
        
                        // Close the statement and result set
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Saving student information to the database:");
                    System.out.println("Name: " + fname);
                    System.out.println("Age: " + age);
                    System.out.println("lname : " + lname);
                    System.out.println("class_grade : " + class_grade);
                    System.out.println("email : " + email);
                    System.out.println("path : " + imagePath);
                    System.out.println("Student information saved successfully!");
            }
    
    
            // Add the student to the TableView
            // Student student = new Student(fname, lname, age, cin, class_grade);
            // studentTableView.getItems().add(student);
        }else{
        }
    }

    public static void getStudentList(TableView<Student> studentTableView){//just when the user clicks on the show button
        databaseManager = new DatabaseManager();

        boolean isconnected = databaseManager.connect();
        System.out.println(isconnected);
        if (isconnected) {
            Connection connection = databaseManager.getConnection();
            if (connection != null) {

                try {
                    // Prepare the SQL statement
                    String sql = "SELECT * FROM students";
                    Statement stmt = connection.createStatement();
                    
                    // Execute the query and retrieve the result set
                    ResultSet resultSet = stmt.executeQuery(sql);
    
                    // Clear the existing data in the TableView
                    studentTableView.getItems().clear();
                    // Iterate over the result set and populate the TableView with the data
                    while (resultSet.next()) {
                        String fname = resultSet.getString("fname");
                        String lname = resultSet.getString("lname");
                        int age = resultSet.getInt("age");
                        String cin = resultSet.getString("cin");
                        String class_grade = resultSet.getString("class_grade");
                        String email = resultSet.getString("email");
                        String infos = resultSet.getString("infos");
                        String imagePath = resultSet.getString("image");
                        

                        
                        Student student = new Student(fname,lname, age, cin,class_grade,email,infos,imagePath);
                        studentTableView.getItems().add(student);
                    }
    
                    // Close the statement and result set
                    
                    resultSet.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("fuck u");
            }
        }else{
            System.out.println("wlah la chafti chi 7aja bareb");
        }

    }

        // Method to delete a student from the database or data storage
        public static void deleteStudentFromDatabase(Student student){
            databaseManager = new DatabaseManager();
            boolean isconnected = databaseManager.connect();
            System.out.println(isconnected);
            if (isconnected) {
                Connection connection = databaseManager.getConnection();
                System.out.println("connexion tdarat ajmi");
                if (connection != null) {
                    System.out.println("hani dkhalt makayn ta mouchkil");
                    try {
                        String sql = "DELETE FROM students WHERE cin ='"+student.getCin()+"'";
                        Statement stmt = connection.createStatement();
                        
                        // Execute the query and retrieve the result set
                        stmt.executeUpdate(sql);
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } 
            
                    
        
                }
                System.out.println("Deleting student from the database:");
                System.out.println("First Name: " + student.getFname());
                System.out.println("Last Name: " + student.getLname());
                System.out.println("Age: " + student.getAge());
                System.out.println("Cin: " + student.getCin());
                System.out.println("class_grade : " + student.getClass_grade());
                System.out.println("class_grade : " + student.getImagepath());
                System.out.println("Student deleted successfully!");
            }
    
        
        
    
        }

        //the submit button
        public static void submitButton(TextField nameField,TextField ageField,TextField cinField,TextField lnameField,TextField class_gradeField,TextField emailField,TextArea infosField,String imagePath){
            String fname = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String cin = cinField.getText();
            String lname = lnameField.getText();
            String class_grade = class_gradeField.getText();
            String email = emailField.getText();
            String infos = infosField.getText();
            String image_Path = imagePath;




            if (isValidEmail(email)) {
                System.out.println("kayna");
                emailField.setBorder(Border.EMPTY);
            } else {
                emailField.setBorder(Border.stroke(Paint.valueOf("red")));
                return;
            }
            // Save the student's information to the database or data storage
            saveStudentToDatabase(fname, lname, age, cin, class_grade,email,infos,image_Path);

            // Clear the form fields after submission
            nameField.clear();
            ageField.clear();
            cinField.clear();
            lnameField.clear();
            class_gradeField.clear();
            emailField.clear();
            infosField.clear();

            // Display a confirmation message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Student information saved successfully!");
            alert.showAndWait();
        }
        

        //delete button
        public static void deleteButton(TableView<Student> studentTableView){
        // Get the selected student from the TableView
        Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();
        Boolean answer;
        if (selectedStudent != null) {
            // Delete the student from the database or data storage
            answer = AlertBox.display();
            if (answer) {
                deleteStudentFromDatabase(selectedStudent);  
                // Refresh the TableView
                studentTableView.getItems().remove(selectedStudent);  
            }
        }
    }

    private static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static Image uploadImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                Image image = new Image(fileInputStream);
                fileInputStream.close();

                return image;
            } catch (IOException ex) {
                ex.printStackTrace();
                // Handle file reading errors
            }
        }

        return null;
    }


}
