package
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.sql.*;
import java.time.Year;

public class DataEntryUI extends Application {

    private TextField firstName, lastName, address, city, zip, originCountry;
    private ComboBox<String> month, day, year, state, status;
    private Label statusLabel;
    private File selectedImage;
    private ImageView imageView;
    private TextArea notesArea;

    private Connection connectToDatabase() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:petitioner_data.db");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS Petitioners (" +
                    "firstName TEXT, lastName TEXT, dob TEXT, address TEXT, city TEXT, state TEXT, " +
                    "zip TEXT, originCountry TEXT, status TEXT, notes TEXT, picturePath TEXT)");
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

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

        firstName = new TextField();
        lastName = new TextField();
        address = new TextField();
        city = new TextField();
        zip = new TextField();
        originCountry = new TextField();
        notesArea = new TextArea();
        notesArea.setPrefRowCount(3);
        notesArea.setWrapText(true);

        for (TextField tf : new TextField[]{firstName, lastName, address, city, zip, originCountry}) {
            tf.setStyle("-fx-control-inner-background: #3c3f41; -fx-text-fill: white;");
        }
        notesArea.setStyle("-fx-control-inner-background: #3c3f41; -fx-text-fill: white;");

        month = new ComboBox<>();
        day = new ComboBox<>();
        year = new ComboBox<>();
        String[] months = {"Month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (String m : months) {
            month.getItems().add(m);
        }
        day.getItems().add("Day");
        for (int i = 1; i <= 31; i++) {
            day.getItems().add(String.valueOf(i));
        }
        year.getItems().add("Year");
        for (int i = 1900; i <= Year.now().getValue(); i++) {
            year.getItems().add(String.valueOf(i));
        }
        month.getSelectionModel().selectFirst();
        day.getSelectionModel().selectFirst();
        year.getSelectionModel().selectFirst();

        state = new ComboBox<>();
        String[] states = {"Select", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY", "DC"};
        for (String s : states) {
            state.getItems().add(s);
        }
        state.getSelectionModel().selectFirst();

        status = new ComboBox<>();
        status.getItems().addAll("Select", "Pending", "Approved", "Denied");
        status.getSelectionModel().selectFirst();

        Button submitButton = new Button("Submit to Reviewer");
        submitButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        Button validatorButton = new Button("Validate");
        Button clearButton = new Button("Clear Fields");
        Button signOutButton = new Button("Sign Out");
        Button uploadButton = new Button("Upload Picture");
        imageView = new ImageView();
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: white;");

        submitButton.setOnAction(this::handleSubmit);
        clearButton.setOnAction(e -> clearForm());
        validatorButton.setOnAction(e -> validateFields());
        uploadButton.setOnAction(e -> handleUpload(primaryStage));
        signOutButton.setOnAction(e -> {
            primaryStage.close();
            new LoginScreen().start(new Stage());
        });

        int row = 0;
        grid.add(new Label("First Name:"), 0, row);
        grid.add(firstName, 1, row++);
        grid.add(new Label("Last Name:"), 0, row);
        grid.add(lastName, 1, row++);
        grid.add(new Label("DOB:"), 0, row);
        grid.add(new HBox(5, month, day, year), 1, row++);
        grid.add(new Label("Address:"), 0, row);
        grid.add(address, 1, row++);
        grid.add(new Label("City:"), 0, row);
        grid.add(city, 1, row++);
        grid.add(new Label("State:"), 0, row);
        grid.add(state, 1, row++);
        grid.add(new Label("Zip Code:"), 0, row);
        grid.add(zip, 1, row++);
        grid.add(new Label("Country of Origin:"), 0, row);
        grid.add(originCountry, 1, row++);
        grid.add(new Label("Immigration Status:"), 0, row);
        grid.add(status, 1, row++);
        grid.add(new Label("Notes:"), 0, row);
        grid.add(notesArea, 1, row++);
        grid.add(new Label("Picture:"), 0, row);
        grid.add(new HBox(10, uploadButton, imageView), 1, row++);

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

        primaryStage.setScene(new Scene(grid, 550, 650));
        primaryStage.show();
    }

    private void handleSubmit(ActionEvent e) {
        if (validateFields()) {
            String fullDOB = month.getValue() + " " + day.getValue() + ", " + year.getValue();
            try (Connection conn = connectToDatabase()) {
                if (conn != null) {
                    PreparedStatement stmt = conn.prepareStatement(
                            "INSERT INTO Petitioners (firstName, lastName, dob, address, city, state, zip, originCountry, status, notes, picturePath) " +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    stmt.setString(1, firstName.getText());
                    stmt.setString(2, lastName.getText());
                    stmt.setString(3, fullDOB);
                    stmt.setString(4, address.getText());
                    stmt.setString(5, city.getText());
                    stmt.setString(6, state.getValue());
                    stmt.setString(7, zip.getText());
                    stmt.setString(8, originCountry.getText());
                    stmt.setString(9, status.getValue());
                    stmt.setString(10, notesArea.getText());
                    stmt.setString(11, selectedImage != null ? selectedImage.getAbsolutePath() : "");
                    stmt.executeUpdate();
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                statusLabel.setText("Submission failed: Database error.");
                return;
            }

            statusLabel.setText("Form submitted and saved to database.");
            clearForm();
        }
    }

    private void handleUpload(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            if (file.length() > 5 * 1024 * 1024) {
                statusLabel.setText("Image file too large. Please select a file under 5MB.");
                return;
            }
            selectedImage = file;
            try {
                Image img = new Image(file.toURI().toString(), 100, 100, true, true);
                imageView.setImage(img);
            } catch (OutOfMemoryError error) {
                statusLabel.setText("Error loading image: file too large for memory.");
            }
        }
    }

    private boolean validateFields() {
        boolean valid = !firstName.getText().isEmpty()
                && !lastName.getText().isEmpty()
                && !address.getText().isEmpty()
                && !city.getText().isEmpty()
                && !zip.getText().isEmpty()
                && !originCountry.getText().isEmpty()
                && !"Select".equals(state.getValue())
                && !"Select".equals(status.getValue())
                && !"Month".equals(month.getValue())
                && !"Day".equals(day.getValue())
                && !"Year".equals(year.getValue());
        statusLabel.setText(valid ? "Validation passed!" : "Please fill out all fields properly.");
        return valid;
    }

    private void clearForm() {
        firstName.clear();
        lastName.clear();
        address.clear();
        city.clear();
        zip.clear();
        originCountry.clear();
        notesArea.clear();
        imageView.setImage(null);
        selectedImage = null;
        ComboBox<String>[] dropdowns = new ComboBox[]{state, status, month, day, year};
        for (ComboBox<String> dropdown : dropdowns) {
            dropdown.getSelectionModel().selectFirst();
        }
        statusLabel.setText("");
    }
}
