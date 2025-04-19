package edu.gmu.cs321;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends Application {

    private static final Map<String, String> credentials = new HashMap<>();
    static {
        credentials.put("dataentry", "entry123");
        credentials.put("reviewer", "review456");
        credentials.put("approver", "approve789");
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("USCIS Login");

        Label instruction = new Label("Enter your role and password:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        Label feedback = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim().toLowerCase();
            String password = passwordField.getText();

            if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
                switch (username) {
                    case "dataentry":
                        new DataEntryUI().start(new Stage());
                        break;
                    case "reviewer":
                        try {
                            new ReviewerUI().start(new Stage());
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "approver":
                        new ApprovalUI().start(new Stage());
                        break;
                }
                primaryStage.close();
            } else {
                feedback.setText("Invalid username or password.");
            }
        });

        VBox layout = new VBox(10, instruction, usernameField, passwordField, loginButton, feedback);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(layout, 350, 230));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
