package edu.gmu.cs321;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class NewDataForm extends Application {

    private TextField petFNameField, petLNameField, petDobField, petANumField;
    private TextField relFNameField, relLNameField, relDobField, relANumField;
    private TextField appDateField, addressField, cityField, stateField, zipcodeField;
    private Label statusLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Petitioner Data Entry Form");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-background-color: #1e1e1e; -fx-text-fill: white;");

        petFNameField = new TextField();
        petLNameField = new TextField();
        petDobField = new TextField();
        petANumField = new TextField();
        relFNameField = new TextField();
        relLNameField = new TextField();
        relDobField = new TextField();
        relANumField = new TextField();
        appDateField = new TextField();
        addressField = new TextField();
        cityField = new TextField();
        stateField = new TextField();
        zipcodeField = new TextField();

        TextField[] fields = {petFNameField, petLNameField, petDobField, petANumField,
                              relFNameField, relLNameField, relDobField, relANumField,
                              appDateField, addressField, cityField, stateField, zipcodeField};

        for (TextField tf : fields) {
            tf.setStyle("-fx-control-inner-background: #3c3f41; -fx-text-fill: white;");
        }

        Button submitButton = new Button("Submit to Reviewer");
        Button validatorButton = new Button("Validate");
        Button clearButton = new Button("Clear Fields");
        Button signOutButton = new Button("Sign Out");

        submitButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: white;");

        submitButton.setOnAction(e -> handleSubmit());
        validatorButton.setOnAction(e -> validateFields());
        clearButton.setOnAction(e -> clearForm());
        signOutButton.setOnAction(e -> primaryStage.close());

        int row = 0;
        grid.add(new Label("[Petitioner]"), 0, row++);
        grid.add(new Label("First Name:"), 0, row); grid.add(petFNameField, 1, row++);
        grid.add(new Label("Last Name:"), 0, row); grid.add(petLNameField, 1, row++);
        grid.add(new Label("Date of Birth (YYYYMMDD):"), 0, row); grid.add(petDobField, 1, row++);
        grid.add(new Label("A-Number:"), 0, row); grid.add(petANumField, 1, row++);

        grid.add(new Label("[Relative]"), 0, row++);
        grid.add(new Label("First Name:"), 0, row); grid.add(relFNameField, 1, row++);
        grid.add(new Label("Last Name:"), 0, row); grid.add(relLNameField, 1, row++);
        grid.add(new Label("Date of Birth (YYYYMMDD):"), 0, row); grid.add(relDobField, 1, row++);
        grid.add(new Label("A-Number:"), 0, row); grid.add(relANumField, 1, row++);

        grid.add(new Label("[Additional Information]"), 0, row++);
        grid.add(new Label("Application Date (YYYYMMDD):"), 0, row); grid.add(appDateField, 1, row++);
        grid.add(new Label("Address:"), 0, row); grid.add(addressField, 1, row++);
        grid.add(new Label("City:"), 0, row); grid.add(cityField, 1, row++);
        grid.add(new Label("State:"), 0, row); grid.add(stateField, 1, row++);
        grid.add(new Label("Zipcode:"), 0, row); grid.add(zipcodeField, 1, row++);

        HBox buttonRow = new HBox(10, validatorButton, clearButton);
        buttonRow.setAlignment(Pos.CENTER);
        grid.add(buttonRow, 0, row, 2, 1);

        HBox bottomRow = new HBox();
        bottomRow.setPadding(new Insets(10, 0, 0, 0));
        bottomRow.setSpacing(10);
        bottomRow.setAlignment(Pos.BOTTOM_RIGHT);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        bottomRow.getChildren().addAll(signOutButton, spacer, submitButton);
        grid.add(bottomRow, 0, ++row, 2, 1);

        grid.add(statusLabel, 0, ++row, 2, 1);

        primaryStage.setScene(new Scene(grid, 550, 700));
        primaryStage.show();
    }

    private void handleSubmit() {
        if (validateFields()) {
            // make petitioner object
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
            // pass on to reviewer
            form.setStatus("toReview");
            // Call your methods
            SQLProcessor.createPetitioner(pet);
            SQLProcessor.createRelative(rel);
            SQLProcessor.createForm(form);
        }
    }

    private boolean validateFields() {
        boolean valid = !petFNameField.getText().isEmpty()
                && !petLNameField.getText().isEmpty()
                && !petDobField.getText().isEmpty()
                && !petANumField.getText().isEmpty()
                && !relFNameField.getText().isEmpty()
                && !relLNameField.getText().isEmpty()
                && !relDobField.getText().isEmpty()
                && !relANumField.getText().isEmpty()
                && !appDateField.getText().isEmpty()
                && !addressField.getText().isEmpty()
                && !cityField.getText().isEmpty()
                && !stateField.getText().isEmpty()
                && !zipcodeField.getText().isEmpty();
        statusLabel.setText(valid ? "Validation passed!" : "Please fill out all fields properly.");
        return valid;
    }

    private void clearForm() {
        petFNameField.clear();
        petLNameField.clear();
        petDobField.clear();
        petANumField.clear();
        relFNameField.clear();
        relLNameField.clear();
        relDobField.clear();
        relANumField.clear();
        appDateField.clear();
        addressField.clear();
        cityField.clear();
        stateField.clear();
        zipcodeField.clear();
        statusLabel.setText("");
    }
}
