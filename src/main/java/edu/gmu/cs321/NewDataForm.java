package edu.gmu.cs321;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

public class NewDataForm extends Application {

    private TextField petFNameField, petLNameField, petDobField, petANumField;
    private TextField relFNameField, relLNameField, relDobField, relANumField;
    private TextField appDateField, addressField, cityField, stateField, zipcodeField;
    private Label statusLabel, counterLabel;
    private ComboBox<Integer> formDropdown;
    private Button darkModeButton;
    private boolean darkMode = false; // Start in non-dark mode
    private int currentFormId = -1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Petitioner Data Entry Form");

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        counterLabel = new Label();
        updateCounters();

        formDropdown = new ComboBox<>();
        formDropdown.setPromptText("Select Form ID");
        loadFormIDs();
        formDropdown.setOnAction(e -> loadForm(formDropdown.getValue()));

        darkModeButton = new Button("Toggle Dark Mode");
        darkModeButton.setOnAction(e -> toggleDarkMode(root));

        HBox topRow = new HBox(10, counterLabel, formDropdown, darkModeButton);
        topRow.setAlignment(Pos.CENTER);

        GridPane grid = createFormGrid();

        Button submitButton = new Button("Submit/Update Form");
        submitButton.setOnAction(e -> handleSubmit());

        Button clearButton = new Button("Clear Fields");
        clearButton.setOnAction(e -> clearForm());

        statusLabel = new Label("");

        HBox buttonRow = new HBox(10, submitButton, clearButton);
        buttonRow.setAlignment(Pos.CENTER);

        root.getChildren().addAll(topRow, grid, buttonRow, statusLabel);

        applyDarkMode(root);

        primaryStage.setScene(new Scene(root, 600, 700));
        primaryStage.show();
    }

    private GridPane createFormGrid() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

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

        int row = 0;
        grid.add(new Label("[Petitioner]"), 0, row++);
        grid.add(new Label("First Name:"), 0, row); grid.add(petFNameField, 1, row++);
        grid.add(new Label("Last Name:"), 0, row); grid.add(petLNameField, 1, row++);
        grid.add(new Label("DOB (YYYYMMDD):"), 0, row); grid.add(petDobField, 1, row++);
        grid.add(new Label("A-Number:"), 0, row); grid.add(petANumField, 1, row++);

        grid.add(new Label("[Relative]"), 0, row++);
        grid.add(new Label("First Name:"), 0, row); grid.add(relFNameField, 1, row++);
        grid.add(new Label("Last Name:"), 0, row); grid.add(relLNameField, 1, row++);
        grid.add(new Label("DOB (YYYYMMDD):"), 0, row); grid.add(relDobField, 1, row++);
        grid.add(new Label("A-Number:"), 0, row); grid.add(relANumField, 1, row++);

        grid.add(new Label("[Additional Info]"), 0, row++);
        grid.add(new Label("Application Date (YYYYMMDD):"), 0, row); grid.add(appDateField, 1, row++);
        grid.add(new Label("Address:"), 0, row); grid.add(addressField, 1, row++);
        grid.add(new Label("City:"), 0, row); grid.add(cityField, 1, row++);
        grid.add(new Label("State:"), 0, row); grid.add(stateField, 1, row++);
        grid.add(new Label("Zipcode:"), 0, row); grid.add(zipcodeField, 1, row++);

        return grid;
    }

    private void updateCounters() {
        int submitted = FormCounter.count("toReview");
        int approved = FormCounter.count("approved");
        int rejected = FormCounter.count("rejected");
        int inReview = FormCounter.count("toApprove");
        counterLabel.setText("Submitted: " + submitted + " | Approved: " + approved + " | Rejected: " + rejected + " | In Review: " + inReview);
    }

    private void loadFormIDs() {
        List<Integer> ids = FormCounter.getAllFormIDs();
        formDropdown.getItems().clear();
        formDropdown.getItems().addAll(ids);
    }

    private void loadForm(int id) {
        Form form = SQLProcessor.retrieveForm(id);
        if (form != null) {
            Petitioner pet = SQLProcessor.retrievePetitioner(form.getPetitionerANum());
            Relative rel = SQLProcessor.retrieveRelative(form.getRelativeANum());

            if (pet != null) {
                petFNameField.setText(pet.getFirstName());
                petLNameField.setText(pet.getLastName());
                petDobField.setText(Integer.toString(pet.getDOB()));
                petANumField.setText(Integer.toString(pet.getANum()));
            }
            if (rel != null) {
                relFNameField.setText(rel.getFirstName());
                relLNameField.setText(rel.getLastName());
                relDobField.setText(Integer.toString(rel.getDOB()));
                relANumField.setText(Integer.toString(rel.getANumRel()));
            }

            appDateField.setText(Integer.toString(form.getApplicationDate()));
            addressField.setText(form.getAddress());
            cityField.setText(form.getCity());
            stateField.setText(form.getState());
            zipcodeField.setText(Integer.toString(form.getZipCode()));
            currentFormId = id;
            statusLabel.setText("Form " + id + " loaded.");
        }
    }

    private void handleSubmit() {
        if (validateFields()) {
            Petitioner pet = new Petitioner(petFNameField.getText(), petLNameField.getText(), Integer.parseInt(petDobField.getText()), Integer.parseInt(petANumField.getText()));
            Relative rel = new Relative(relFNameField.getText(), relLNameField.getText(), Integer.parseInt(relDobField.getText()), Integer.parseInt(relANumField.getText()));
            Form form = new Form();
            form.setApplicationDate(Integer.parseInt(appDateField.getText()));
            form.setAddress(addressField.getText());
            form.setCity(cityField.getText());
            form.setState(stateField.getText());
            form.setZipCode(Integer.parseInt(zipcodeField.getText()));
            form.setPetitionerANum(Integer.parseInt(petANumField.getText()));
            form.setRelativeANum(Integer.parseInt(relANumField.getText()));
            form.setStatus("toReview");

            if (currentFormId == -1) {
                SQLProcessor.createPetitioner(pet);
                SQLProcessor.createRelative(rel);
                SQLProcessor.createForm(form);
                statusLabel.setText("Form submitted.");
            } else {
                SQLProcessor.modifyForm(currentFormId, form);
                SQLProcessor.modifyPetitioner(pet);
                SQLProcessor.modifyRelative(rel);
                statusLabel.setText("Form updated.");
            }

            updateCounters();
            loadFormIDs();
            clearForm();
        }
    }

    private boolean validateFields() {
        return !petFNameField.getText().isEmpty() && !petLNameField.getText().isEmpty() &&
                !petDobField.getText().isEmpty() && !petANumField.getText().isEmpty() &&
                !relFNameField.getText().isEmpty() && !relLNameField.getText().isEmpty() &&
                !relDobField.getText().isEmpty() && !relANumField.getText().isEmpty() &&
                !appDateField.getText().isEmpty() && !addressField.getText().isEmpty() &&
                !cityField.getText().isEmpty() && !stateField.getText().isEmpty() &&
                !zipcodeField.getText().isEmpty();
    }

    private void clearForm() {
        petFNameField.clear(); petLNameField.clear(); petDobField.clear(); petANumField.clear();
        relFNameField.clear(); relLNameField.clear(); relDobField.clear(); relANumField.clear();
        appDateField.clear(); addressField.clear(); cityField.clear(); stateField.clear(); zipcodeField.clear();
        currentFormId = -1;
        statusLabel.setText("");
    }

    private void toggleDarkMode(Pane root) {
        darkMode = !darkMode;
        applyDarkMode(root);
    }

    private void applyDarkMode(Pane root) {
        if (darkMode) {
            root.setStyle("-fx-background-color: #1e1e1e; -fx-text-fill: white;");
        } else {
            root.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        }
    }
}
