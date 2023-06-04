package model;
import java.io.File;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentProfileScene{

    public static Scene showScene(Scene scene , Stage stage ,Student selectedStudent) {

        
         // Create the root pane
         GridPane root = new GridPane();
         root.setAlignment(Pos.TOP_CENTER);
         root.setHgap(20);
         root.setVgap(20);
         root.setPadding(new Insets(20));
         root.setStyle("-fx-background-color: #f4f4f4");
 
         // Set column and row constraints
         ColumnConstraints columnConstraints = new ColumnConstraints();
         columnConstraints.setHalignment(HPos.CENTER);
         root.getColumnConstraints().add(columnConstraints);
 
         RowConstraints rowConstraints = new RowConstraints();
         rowConstraints.setVgrow(Priority.ALWAYS);
         root.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints);
 
         // Create the profile picture
         String filePath = selectedStudent.getImagepath();
         System.out.println(filePath);
         String imageUrl = "file:" + filePath;
             
         // Create the Image
         Image image = new Image(imageUrl);


         ImageView profilePicture = new ImageView(image);
         profilePicture.setFitWidth(150);
         profilePicture.setFitHeight(150);
         GridPane.setRowIndex(profilePicture, 0);
 
         // Create the personal information section
         VBox personalInfoBox = new VBox(10);
         // personalInfoBox.setAlignment(Pos.CENTER);
         personalInfoBox.setPadding(new Insets(10));
         personalInfoBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 1px;");
         GridPane.setRowIndex(personalInfoBox, 1);
 
         Label nameLabel = new Label(selectedStudent.getFname() +" " +selectedStudent.getLname());
         nameLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");
 
         Label ageLabel = new Label("Age: "+selectedStudent.getAge());
         Label cinLabel = new Label("Cin: "+selectedStudent.getCin());
         Label class_gradeLabel = new Label("Class grade: "+selectedStudent.getClass_grade());
        //  Label genderLabel = new Label("Gender: ");
         Label contactLabel = new Label("Contact: "+selectedStudent.getEmail());
 
         personalInfoBox.getChildren().addAll(nameLabel, ageLabel,cinLabel,class_gradeLabel, contactLabel);
 
         // Create the content section
         VBox contentBox = new VBox(10);
         contentBox.setAlignment(Pos.TOP_LEFT);
         contentBox.setPadding(new Insets(10));
         contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 1px;");
         GridPane.setRowIndex(contentBox, 2);
 
         Label bioLabel = new Label("About Me");
         bioLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
 
         Label bioText = new Label(selectedStudent.getInfos());
         bioText.setWrapText(true);
 
         
 

 


 
         Button editButton = new Button("Edit Profile");
         Button prinButton = new Button("Print infos");
         Button backButton = new Button("back");
 
         // Handle button actions
         editButton.setOnAction(event -> {
            Update.display(selectedStudent);
         });

         backButton.setOnAction(event -> {
            stage.setScene(scene);
         });
         
        
 
         // Create an HBox for the buttons
         HBox buttonsHBox = new HBox(10);
         buttonsHBox.getChildren().addAll(editButton, prinButton ,backButton);
         GridPane.setRowIndex(buttonsHBox, 3);
 
         // Add the profile picture, personal information, and content to the root pane
         root.getChildren().addAll(profilePicture, personalInfoBox, contentBox,buttonsHBox);
 
         contentBox.getChildren().addAll(bioLabel, bioText);
 
         // Create the scene
         Scene scene2 = new Scene(root, 600, 700);
         prinButton.setOnAction(event -> {
            File file = new File("output.pdf");
            saveAsPDF(scene2, file);
        });
        scene2.getStylesheets().add("/vue/styles.css"); // Add the CSS file

        return scene2;
        
    }
    private static void saveAsPDF(Scene scene, File file) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.EQUAL);
        PrinterJob job = PrinterJob.createPrinterJob(printer);
    
        if (job != null) {
            job.getJobSettings().setPageLayout(pageLayout);
    
            boolean showDialog = job.showPrintDialog(scene.getWindow());
            if (showDialog) {
                job.getJobSettings().setJobName(file.getName());
                // Print the scaled and translated scene
                boolean success = job.printPage(scene.getRoot());
                if (success) {
                    job.endJob();
                    System.out.println("PDF generated successfully: " + file.getAbsolutePath());
                }
     
                // Reset the scene scaling and translation to their original state
                scene.getRoot().setScaleX(1.0);
                scene.getRoot().setScaleY(1.0);
                scene.getRoot().setTranslateX(0.0);
                scene.getRoot().setTranslateY(0.0);
            }
        }
    }


    

}
