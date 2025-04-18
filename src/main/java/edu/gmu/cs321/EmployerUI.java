package edu.gmu.cs321;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EmployerUI extends Application {
    @Override
    public void start(Stage stage) {
        /* label for window  */
         Label header = new Label ("[USCIS Employee Main Portal]");
         /* button for data entry */
         Button dataEntry = new Button("Data Entry");
         /* button for reviewer */
         Button reviewer = new Button("Reviewer");
         /* button for approver */
         Button approver = new Button("Approver");
         /* label for customer section  */
         Label header2 = new Label ("[Demo Customer/Petitioner Submission]");
         /* button for customer ui */
         Button customer = new Button("Customer Form");        
         /* button config for data entry */
         dataEntry.setOnAction(e -> {
            DataEntryUI deUI = new DataEntryUI();
            try {
                deUI.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        /* button config for reviewer */
        reviewer.setOnAction(e -> {
            ReviewerUI rUI = new ReviewerUI();
            try {
                rUI.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        /* button config for approver */
        approver.setOnAction(e -> {
            ApprovalUI aUI = new ApprovalUI();
            try {
                aUI.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        /* button config for customer */
        customer.setOnAction(e -> {
            ScreenSubmission cUI = new ScreenSubmission();
            try {
                cUI.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        /* declare region and insert buttons */
        VBox region = new VBox(10, header, dataEntry, reviewer, approver, header2, customer);
        region.setAlignment(Pos.CENTER);
        /* wrap vbox to align in the middle */
        //StackPane root = new StackPane(region);
        //StackPane.setAlignment(region, Pos.CENTER); // center the VBox
        Scene scene = new Scene(region, 200, 400);
        stage.setTitle("USCIS Employee Portal");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
