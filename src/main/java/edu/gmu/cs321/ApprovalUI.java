package edu.gmu.cs321;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ApprovalUI extends Application {

    /**
     * Textbox for reason of rejection,
     */
    private TextArea reasonArea;
    /**
     * Button for rejection.
     */
    private Button rejectButton;
    /**
     * Button for approval.
     */
    private Button approveButton;
    /**
     * Area for process history of form/
     */
    private Label validationHistoryLabel;
    /**
     * Textbox for form information/summary.
     */
    private TextArea formAdditionalNotes;
    /**
     * Main function (start GUI)
     * @param args Strings passed to main.
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * void start()
     * Configure GUI
     * @param primaryStage Top level of container.
     */
    @Override
    public void start(Stage primaryStage) {
        // window title for approval screen
        primaryStage.setTitle("USCIS Portal [Approver - LOGGED IN]");
        // left panel region
        VBox leftPanel = new VBox(10);
        // spacing for left panel
        leftPanel.setPadding(new Insets(10));
        leftPanel.setPrefWidth(200);
        // button for next form in queue
        Button getFormBtn = new Button("Get Available Form");
        // button to check queue/workflow status
        Button checkStatusBtn = new Button("Check Queue Status");
        // button to sign in / sign out
        Button signOutBtn = new Button("Sign Out");
        // custom colors
        signOutBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        // insert buttons into left panel
        leftPanel.getChildren().addAll(getFormBtn, checkStatusBtn, signOutBtn);
        // center region
        VBox centerPanel = new VBox(10);
        // spacing for center region
        centerPanel.setPadding(new Insets(10));
        // display form information
        Label formID = new Label("Form ID: ");
        Label fName = new Label("First Name: ");
        Label lName = new Label("Last Name: ");
        Label dob = new Label("Date of Birth: ");
        Label country = new Label("Originating Country: ");
        Label petitionFName = new Label("Petitioner First Name: ");
        Label petitionLName = new Label("Petitioner Last Name: ");
        // prompt that Reviewer has marked the form as ready for approval.
        Label note = new Label("Note:");
        TextArea noteArea = new TextArea("[Reviewer] marked this form as 'ready for approval.'");
        noteArea.setEditable(false);
        noteArea.setWrapText(true);
        // additional area for more info regarding document/petitioner/dependent.
        formAdditionalNotes = new TextArea("Additional Notes: \n");
        formAdditionalNotes.setEditable(false);
        // history of document
        validationHistoryLabel = new Label("Validation History:\n- Data Entry: Passed\n- Reviewer: Passed");
        // insert to central region
        centerPanel.getChildren().addAll(formID, fName, lName, dob, country, petitionFName, petitionLName, note, noteArea, formAdditionalNotes, validationHistoryLabel);
        // right panel
        VBox rightPanel = new VBox(10);
        // spacing for right panel
        rightPanel.setPadding(new Insets(10));
        // button to approve document
        approveButton = new Button("Approve Form");
        // button to reject document
        rejectButton = new Button("Reject Form");
        // disable rejection button by default
        rejectButton.setDisable(true);
        // button to save rejection note
        Button saveButton = new Button("Save Changes");
        // button to delete/discord rejection note
        Button undoButton = new Button("Undo Changes");
        // area to type in rejection reason
        reasonArea = new TextArea();
        // prompt approver to enter reasoning here
        reasonArea.setPromptText("Enter reasoning for rejection...");
        reasonArea.setWrapText(true);
        // enable reject button if characters typed reached >= 10
        reasonArea.textProperty().addListener((obs, oldText, newText) -> {
            rejectButton.setDisable(newText.trim().length() < 10);
        });
        // if approve button is activated, prompt alert box
        approveButton.setOnAction(e -> {
            // confirm if approver wants to approve form (y/n alert box)
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you confirm approval of the following form?\n\n" + formAdditionalNotes.getText(), ButtonType.YES, ButtonType.NO);
            // header/title of messgae box
            confirm.setHeaderText("Confirm Approval");
            // prompt alert box if approver clicks yes
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    showAlert("Form Approved", "The form has been approved.");
                }
            });
        });
        // add buttons and text box to right region
        rightPanel.getChildren().addAll(approveButton, rejectButton, reasonArea, saveButton, undoButton);
        // assign regions to root
        BorderPane layout = new BorderPane();
        layout.setLeft(leftPanel);
        layout.setCenter(centerPanel);
        layout.setRight(rightPanel);
        // set dimensions
        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * void showAlert()
     * Prompts a alert box to user.
     * @param title Title of alert box.
     * @param alert Body of alert box.
     */
    private void showAlert(String title, String alert) {
        // create object for alert box
        Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
        // assign title to alert box
        alertBox.setTitle(title);
        alertBox.setHeaderText(null);
        // set body to alert box
        alertBox.setContentText(alert);
        alertBox.showAndWait();
    }
} 
