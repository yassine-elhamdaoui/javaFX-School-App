package model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    private static boolean answer;

    public static boolean display() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Confirmation");

        Text messageText = new Text("Are you sure you want to delete that student?");
        messageText.setFont(Font.font(14));

        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            answer = true;
            stage.close();
        });

        Button noButton = new Button("No");
        noButton.setOnAction(e -> {
            answer = false;
            stage.close();
        });

        HBox buttonsBox = new HBox(10);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.getChildren().addAll(noButton, yesButton);

        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(messageText, buttonsBox);

        // Apply custom CSS style to the "No" button
        noButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.showAndWait();

        return answer;
    }
}
