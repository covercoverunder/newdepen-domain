package edu.gmu.cs321;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScreenSubmission extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);

        // Petitioner Section
        Label petLabel = new Label("[Petitioner]");
        TextField petFNameField = new TextField();
        petFNameField.setPromptText("First Name");
        TextField petLNameField = new TextField();
        petLNameField.setPromptText("Last Name");
        TextField petDobField = new TextField();
        petDobField.setPromptText("Date of Birth (YYYYMMDD)");
        TextField petANumField = new TextField();
        petANumField.setPromptText("A-Number");

        // Relative Section
        Label relLabel = new Label("[Relative]");
        TextField relFNameField = new TextField();
        relFNameField.setPromptText("First Name");
        TextField relLNameField = new TextField();
        relLNameField.setPromptText("Last Name");
        TextField relDobField = new TextField();
        relDobField.setPromptText("Date of Birth (YYYYMMDD)");
        TextField relANumField = new TextField();
        relANumField.setPromptText("A-Number");

        // Additional Information Section
        Label addInfoLabel = new Label("[Additional Information]");
        TextField appDateField = new TextField();
        appDateField.setPromptText("Application Date (YYYYMMDD)");
        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        TextField cityField = new TextField();
        cityField.setPromptText("City");
        TextField stateField = new TextField();
        stateField.setPromptText("State");
        TextField zipcodeField = new TextField();
        zipcodeField.setPromptText("Zipcode");

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            // Create objects from input
            Petitioner pet = new Petitioner(
                petFNameField.getText(),
                petLNameField.getText(),
                Integer.parseInt(petDobField.getText()),
                Integer.parseInt(petANumField.getText())
            );

            Relative rel = new Relative(
                relFNameField.getText(),
                relLNameField.getText(),
                Integer.parseInt(relDobField.getText()),
                Integer.parseInt(relANumField.getText())
            );

            Form form = new Form();
            form.setApplicationDate(Integer.parseInt(appDateField.getText()));
            form.setAddress(addressField.getText());
            form.setCity(cityField.getText());
            form.setState(stateField.getText());
            form.setZipCode(Integer.parseInt(zipcodeField.getText()));
            form.setPetitionerANum(Integer.parseInt(petANumField.getText()));
            form.setRelativeANum(Integer.parseInt(relANumField.getText()));

            // debug approver
            form.setStatus("approve");

            // Call your methods
            SQLProcessor.createPetitioner(pet);
            SQLProcessor.createRelative(rel);
            SQLProcessor.createForm(form);
        });

        // Add everything to layout
        root.getChildren().addAll(
            petLabel, petFNameField, petLNameField, petDobField, petANumField,
            relLabel, relFNameField, relLNameField, relDobField, relANumField,
            addInfoLabel, appDateField, addressField, cityField, stateField, zipcodeField,
            submitButton
        );

        Scene scene = new Scene(root, 400, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Form Submission");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}