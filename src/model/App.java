package model;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

import controller.Handler;


public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private TableView<Student> studentTableView;

    @Override
    public void start(Stage stage) {
        // code for profile scene

        // Connect to the database

        stage.setTitle("ENSAO Student Management");
        Image img = new Image("/vue/logo_ensa.png");
        // Create a VBox layout to hold the content
        VBox vbox = new VBox(30);
        vbox.setPadding(new Insets(20));
        vbox.setSpacing(30);

        // Set a background color for the VBox
        vbox.setStyle("-fx-background-color: #f4f4f4;");

        // Create a label for the ENSAO heading
        Label ensaoLabel = new Label("ENSAO");
        ensaoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(ensaoLabel);

        // Create a GridPane layout to hold the form elements
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create form labels and fields
        Label nameLabel = new Label("Name:");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField nameField = new TextField();

        Label lnameLabel = new Label("last name :");
        lnameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField lnameField = new TextField();


        Label ageLabel = new Label("Age:");
        ageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField ageField = new TextField();


        Label cinLabel = new Label("Cin:");
        cinLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField cinField = new TextField();


        Label class_gradeLabel = new Label("class grade :");
        class_gradeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField class_gradeField = new TextField();


        Label emailLabel = new Label("email :");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField emailField = new TextField();
        // Create a FileChooser object for selecting the image file
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));

        // Create an upload button
        Button uploadButton = new Button("Upload Image");
        uploadButton.setStyle("-fx-background-color:  #4169E1; -fx-text-fill: white;");
        // Add an event handler to handle button clicks
        AtomicReference<String> imagePathRef = new AtomicReference<>();
        uploadButton.setOnAction(e -> {
            // Show the file chooser dialog
            File selectedFile = fileChooser.showOpenDialog(stage);
        
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




        // Add the upload button to the gridPane
        gridPane.add(uploadButton, 0, 6, 5, 1);


        

        // Add form elements to the gridPane
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(lnameLabel, 3, 0);
        gridPane.add(lnameField, 4, 0);
        gridPane.add(ageLabel, 0, 2);
        gridPane.add(ageField, 1, 2);
        gridPane.add(cinLabel, 3, 2);
        gridPane.add(cinField, 4, 2);
        gridPane.add(class_gradeLabel, 0, 4);
        gridPane.add(class_gradeField, 1, 4);
        gridPane.add(emailLabel, 3, 4);
        gridPane.add(emailField, 4, 4);
        gridPane.setAlignment(Pos.CENTER);

        TextArea infosField = new TextArea();
        infosField.setWrapText(true);
        infosField.setMaxHeight(120);
        infosField.setMinHeight(120);
        infosField.setMinWidth(450);
        infosField.setMaxWidth(450);

        // Create a submit button
        Button submitButton = new Button("Add student");
        submitButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        GridPane.setMargin(submitButton, new Insets(10, 0, 0, 0));
        // gridPane.add(submitButton, 1, 7, 2, 1);

        // Create a TableView to display the list of registered students
        studentTableView = new TableView<>();
        studentTableView.setMaxHeight(500);

        // Create columns for the TableView
        TableColumn<Student, String> nameColumn = new TableColumn<>("first name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));
        nameColumn.setMaxWidth(140);
        nameColumn.setMinWidth(140);

        TableColumn<Student, String> lnameColumn = new TableColumn<>("last name");
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
        lnameColumn.setMaxWidth(140);
        lnameColumn.setMinWidth(140);

        TableColumn<Student, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageColumn.setMaxWidth(100);
        ageColumn.setMinWidth(100);

        TableColumn<Student, String> cinColumn = new TableColumn<>("Cin");
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
        cinColumn.setMaxWidth(100);
        cinColumn.setMinWidth(100);

        TableColumn<Student, String> class_gradeColumn = new TableColumn<>("class grade");
        class_gradeColumn.setCellValueFactory(new PropertyValueFactory<>("class_grade"));
        class_gradeColumn.setMaxWidth(160);
        class_gradeColumn.setMinWidth(160);

        TableColumn<Student, String> email_column = new TableColumn<>("email");
        email_column.setCellValueFactory(new PropertyValueFactory<>("email"));
        email_column.setMaxWidth(220);
        email_column.setMinWidth(220);

        studentTableView.getStylesheets().add("/vue/style_table.css");
        addColumns(nameColumn, lnameColumn, ageColumn, cinColumn, class_gradeColumn, email_column);
        // studentTableView.getColumns().addAll(nameColumn, lnameColumn, ageColumn,cinColumn, class_gradeColumn,email_column);
        vbox.getChildren().add(studentTableView);
        HBox buttons = new HBox(10);
        // Create a delete button
        Button deleteButton = new Button("Delete student");
        deleteButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
        buttons.getChildren().add(deleteButton);

        Button showButton = new Button("Show students");
        showButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
        buttons.getChildren().add(showButton);

        Button profileButton = new Button("Show profile");
        profileButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
        buttons.getChildren().add(profileButton);

        buttons.setAlignment(Pos.CENTER);

        vbox.getChildren().add(buttons);
        showButton.setOnAction(e -> Handler.getStudentList(studentTableView));
        // Define the action to be performed when the submit button is clicked
        submitButton.setOnAction(e -> Handler.submitButton(nameField, ageField, cinField, lnameField, class_gradeField, emailField, infosField,imagePathRef.get().replace("\\", "\\\\")));

        // Define the action to be performed when the delete button is clicked
        deleteButton.setOnAction(e -> Handler.deleteButton(studentTableView));

        Scene scene = new Scene(vbox, 900, 700);

        // code for scene2

        profileButton.setOnAction(e -> {
            // Get the selected student from the TableView
            Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                // Update.display(selectedStudent);
                stage.setScene(StudentProfileScene.showScene(scene, stage, selectedStudent));
            }
        });
        // Add the gridPane to the VBox
        vbox.getChildren().addAll(gridPane, infosField, submitButton);
        // Create a scene with the VBox layout
        stage.setScene(scene);
        stage.getIcons().add(img);
        stage.show();
    }

    @SafeVarargs
    private void addColumns(TableColumn<Student, ?>... columns) {
        studentTableView.getColumns().addAll(columns);
    }
}
