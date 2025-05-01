package edu.gmu.cs321;
//feature update
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class DataEntryUI extends Application {

    private Label statusLabel = new Label();
    private boolean darkMode = true;

    private ComboBox<Integer> createYearDropdown() {
        ComboBox<Integer> yearBox = new ComboBox<>();
        yearBox.setPromptText("Year");
        Integer[] years = IntStream.rangeClosed(1900, 2025).boxed().sorted((a, b) -> b - a).toArray(Integer[]::new);
        for (int i = 0; i < years.length; i++) {
            yearBox.getItems().add(years[i]);
        }
        return yearBox;
    }

    private ComboBox<Integer> createMonthDropdown() {
        ComboBox<Integer> monthBox = new ComboBox<>();
        monthBox.setPromptText("Month");
        for (int i = 1; i <= 12; i++) {
            monthBox.getItems().add(i);
        }
        return monthBox;
    }

    private ComboBox<Integer> createDayDropdown() {
        ComboBox<Integer> dayBox = new ComboBox<>();
        dayBox.setPromptText("Day");
        for (int i = 1; i <= 31; i++) {
            dayBox.getItems().add(i);
        }
        return dayBox;
    }

    private ComboBox<String> createStateDropdown() {
        ComboBox<String> stateBox = new ComboBox<>();
        stateBox.setPromptText("Select State");
        List<String> states = Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID",
                "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT",
                "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
                "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");
        for (int i = 0; i < states.size(); i++) {
            stateBox.getItems().add(states.get(i));
        }
        return stateBox;
    }

    @Override
    public void start(Stage primaryStage) {
        VBox content = new VBox(12);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: #1e1e1e;");
        statusLabel.setStyle("-fx-text-fill: white;");

        ToggleButton darkModeToggle = new ToggleButton("\u263E");
        darkModeToggle.setOnAction(e -> {
            darkMode = !darkMode;
            darkModeToggle.setText(darkMode ? "\u263E" : "\u2600");
            updateTheme(content, darkModeToggle);
        });

        // Petitioner
        Label petLabel = new Label("[Petitioner]");
        TextField petFNameField = new TextField();
        petFNameField.setPromptText("First Name");
        TextField petLNameField = new TextField();
        petLNameField.setPromptText("Last Name");

        Label petDobLabel = new Label("Date of Birth:");
        ComboBox<Integer> petMonthBox = createMonthDropdown();
        ComboBox<Integer> petDayBox = createDayDropdown();
        ComboBox<Integer> petYearBox = createYearDropdown();
        HBox petDobLabels = new HBox(5, new Label("MM"), new Label("DD"), new Label("YYYY"));
        HBox petDobBox = new HBox(5, petMonthBox, petDayBox, petYearBox);

        TextField petANumField = new TextField();
        petANumField.setPromptText("A-Number");

        // Relative
        Label relLabel = new Label("[Relative]");
        TextField relFNameField = new TextField();
        relFNameField.setPromptText("First Name");
        TextField relLNameField = new TextField();
        relLNameField.setPromptText("Last Name");

        Label relDobLabel = new Label("Date of Birth:");
        ComboBox<Integer> relMonthBox = createMonthDropdown();
        ComboBox<Integer> relDayBox = createDayDropdown();
        ComboBox<Integer> relYearBox = createYearDropdown();
        HBox relDobLabels = new HBox(5, new Label("MM"), new Label("DD"), new Label("YYYY"));
        HBox relDobBox = new HBox(5, relMonthBox, relDayBox, relYearBox);

        TextField relANumField = new TextField();
        relANumField.setPromptText("A-Number");

        // Additional Info
        Label addInfoLabel = new Label("[Additional Information]");
        Label appDateLabel = new Label("Application Date:");
        ComboBox<Integer> appMonthBox = createMonthDropdown();
        ComboBox<Integer> appDayBox = createDayDropdown();
        ComboBox<Integer> appYearBox = createYearDropdown();
        HBox appDobLabels = new HBox(5, new Label("MM"), new Label("DD"), new Label("YYYY"));
        HBox appDateBox = new HBox(5, appMonthBox, appDayBox, appYearBox);

        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        TextField cityField = new TextField();
        cityField.setPromptText("City");
        ComboBox<String> stateBox = createStateDropdown();
        TextField zipcodeField = new TextField();
        zipcodeField.setPromptText("Zipcode");

        // Buttons
        Button submitButton = new Button("Submit");
        Button clearButton = new Button("Clear Fields");
        Button signOutButton = new Button("Sign Out");

        submitButton.setOnAction(e -> {
            if (validateFields(petFNameField, petLNameField, petANumField,
                    relFNameField, relLNameField, relANumField,
                    addressField, cityField, zipcodeField)
                    && validateDateDropdowns(petYearBox, petMonthBox, petDayBox)
                    && validateDateDropdowns(relYearBox, relMonthBox, relDayBox)
                    && validateDateDropdowns(appYearBox, appMonthBox, appDayBox)
                    && stateBox.getValue() != null) {

                try {
                    int petDob = parseDate(petYearBox, petMonthBox, petDayBox);
                    int relDob = parseDate(relYearBox, relMonthBox, relDayBox);
                    int appDate = parseDate(appYearBox, appMonthBox, appDayBox);

                    Petitioner pet = new Petitioner(
                            petFNameField.getText(),
                            petLNameField.getText(),
                            petDob,
                            Integer.parseInt(petANumField.getText())
                    );

                    Relative rel = new Relative(
                            relFNameField.getText(),
                            relLNameField.getText(),
                            relDob,
                            Integer.parseInt(relANumField.getText())
                    );

                    Form form = new Form();
                    form.setApplicationDate(appDate);
                    form.setAddress(addressField.getText());
                    form.setCity(cityField.getText());
                    form.setState(stateBox.getValue());
                    form.setZipCode(Integer.parseInt(zipcodeField.getText()));
                    form.setPetitionerANum(pet.getANum());
                    form.setRelativeANum(rel.getANumRel());

                    SQLProcessor.createPetitioner(pet);
                    SQLProcessor.createRelative(rel);
                    SQLProcessor.createForm(form);

                    statusLabel.setText("Submission successful.");
                    clearForm(petFNameField, petLNameField, petANumField,
                            relFNameField, relLNameField, relANumField,
                            addressField, cityField, zipcodeField);
                } catch (NumberFormatException nfe) {
                    statusLabel.setText("Invalid number format.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    statusLabel.setText("Submission failed.");
                }
            }
        });

        clearButton.setOnAction(e -> {
            clearForm(petFNameField, petLNameField, petANumField,
                    relFNameField, relLNameField, relANumField,
                    addressField, cityField, zipcodeField);
            statusLabel.setText("");
        });

        signOutButton.setOnAction(e -> {
            primaryStage.close();
            new LoginScreen().start(new Stage());
        });

        content.getChildren().addAll(
                darkModeToggle,
                petLabel, petFNameField, petLNameField, petDobLabel, petDobLabels, petDobBox, petANumField,
                relLabel, relFNameField, relLNameField, relDobLabel, relDobLabels, relDobBox, relANumField,
                addInfoLabel, appDateLabel, appDobLabels, appDateBox,
                addressField, cityField, stateBox, zipcodeField,
                new HBox(10, submitButton, clearButton, signOutButton), statusLabel
        );

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #1e1e1e;");

        Scene scene = new Scene(scrollPane, 450, 750);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Form Submission");
        primaryStage.show();
    }

    private void updateTheme(VBox root, ToggleButton toggle) {
        if (darkMode) {
            root.setStyle("-fx-background-color: #1e1e1e;");
            statusLabel.setStyle("-fx-text-fill: white;");
            toggle.setStyle("-fx-background-color: #444; -fx-text-fill: white;");
        } else {
            root.setStyle("-fx-background-color: #ffffff;");
            statusLabel.setStyle("-fx-text-fill: black;");
            toggle.setStyle("-fx-background-color: #ccc; -fx-text-fill: black;");
        }
    }

    private boolean validateFields(TextField... fields) {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getText().trim().isEmpty()) {
                statusLabel.setText("Please fill out all fields.");
                return false;
            }
        }
        return true;
    }

    private boolean validateDateDropdowns(ComboBox<Integer> year, ComboBox<Integer> month, ComboBox<Integer> day) {
        if (year.getValue() == null || month.getValue() == null || day.getValue() == null) {
            statusLabel.setText("Please complete all date fields.");
            return false;
        }
        return true;
    }

    private int parseDate(ComboBox<Integer> year, ComboBox<Integer> month, ComboBox<Integer> day) {
        return year.getValue() * 10000 + month.getValue() * 100 + day.getValue();
    }

    private void clearForm(TextField... fields) {
        for (int i = 0; i < fields.length; i++) {
            fields[i].clear();
        }
    }
}
