package model;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicReference;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Update{

    private static TextField firstNameField;
    private static TextField lastNameField;
    private static TextField ageField;
    private static TextField cinField;
    private static TextField classGradeField;
    private static TextField emailField;
    private static TextArea infoTextArea;

    static DatabaseManager databaseManager;
    static Stage primaryStage ;
    public static void display(Student selectedStudent) {
        primaryStage = new Stage();
        primaryStage.setTitle("Student Information App");

        // Create UI elements
        Label firstNameLabel = new Label("First Name:");
        firstNameField = new TextField(selectedStudent.getFname());

        Label lastNameLabel = new Label("Last Name:");
        lastNameField = new TextField(selectedStudent.getLname());

        Label ageLabel = new Label("Age:");
        ageField = new TextField(""+selectedStudent.getAge());

        Label cinLabel = new Label("CIN:");
        cinField = new TextField(selectedStudent.getCin());
        String oldCinValue = cinField.getText();

        Label classGradeLabel = new Label("Class Grade:");
        classGradeField = new TextField(selectedStudent.getClass_grade());

        Label emailLabel = new Label("Email:");
        emailField = new TextField(selectedStudent.getEmail());

        infoTextArea = new TextArea(selectedStudent.getInfos());
        infoTextArea.setMinHeight(150);
        infoTextArea.setWrapText(true);
        infoTextArea.getStyleClass().add("info-text-area");

        Button uploadButton = new Button("Upload Image");
        uploadButton.setStyle("-fx-background-color:  #4169E1; -fx-text-fill: white;");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        AtomicReference<String> imagePathRef = new AtomicReference<>();

        Button submitButton = new Button("Submit");
        submitButton.getStyleClass().add("submit-button");
        uploadButton.setOnAction(e -> {
            // Show the file chooser dialog
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
        
            if (selectedFile != null) {
                // Get the selected file path
                String imagePath = selectedFile.getAbsolutePath();
        
                // Store the image path in the AtomicReference
                imagePathRef.set(imagePath);
        
                // Print a confirmation message
                // System.out.println("Image path stored in the database: " + imagePathRef.get());
                String image_Path = imagePathRef.get();
                System.out.println("Image path stored in the database: " + image_Path);
            }
        });
        submitButton.setOnAction(e -> submitButtonClicked(firstNameField,lastNameField,ageField,cinField,classGradeField,emailField,infoTextArea,imagePathRef.get().replace("\\","\\\\"),oldCinValue));



        // Create a grid pane and add UI elements
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addColumn(0, firstNameLabel, lastNameLabel, ageLabel, cinLabel, classGradeLabel, emailLabel);
        gridPane.addColumn(1, firstNameField, lastNameField, ageField, cinField, classGradeField, emailField);

        // Create a VBox to hold the grid pane and submit button
        VBox vBox = new VBox(gridPane,uploadButton, submitButton, infoTextArea);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20));

        Scene scene = new Scene(vBox, 450, 550);
        scene.getStylesheets().add("/vue/styleUpdate.css"); // Add CSS styles
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void submitButtonClicked(TextField firstNameField, TextField lastNameField, TextField ageField,
            TextField cinField, TextField classGradeField, TextField emailField, TextArea infoTextArea ,String imagePath , String oldCinValue) {

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String cin = cinField.getText();
        String classGrade = classGradeField.getText();
        String email = emailField.getText();
        String infos = infoTextArea.getText();
        
        databaseManager = new DatabaseManager();
        boolean isconnected = databaseManager.connect();
        System.out.println("wech a la famille \n\n\n");
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
                    String sql = "UPDATE students SET fname = '" + firstName + "', lname = '" + lastName + "', age = '" + age + "', cin = '" + cin + "', class_grade = '" + classGrade + "', email = '" + email + "', infos = '" + infos + "' , image = '" + imagePath + "' WHERE cin = '" + oldCinValue + "'";
                    System.out.println("hii tani");
                    stmt.executeUpdate(sql);

                    connection.close();

                    System.out.println("Saving the new  student information to the database:");
                    System.out.println("Name: " + firstName);
                    System.out.println("Age: " + age);
                    System.out.println("lname : " + lastName);
                    System.out.println("class_grade : " + classGrade);
                    System.out.println("email : " + email);
                    System.out.println("email : " + infos);
                    System.out.println("Student information changed  successfully!");
                    primaryStage.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                

        }
    }else{
        System.out.println("wlah la dkhaltiha hna");
    }

    }
}
