package edu.gmu.cs321;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * UI/Screen for Approver.
 * @author Michael Toyco
 */
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

    // display form information header
    Label formID = new Label("Form ID: ");
    Label fName = new Label("First Name: ");
    Label lName = new Label("Last Name: ");
    Label dob = new Label("Date of Birth: ");
    Label aNum = new Label("Alien Number: ");
    Label address = new Label("Address:");
    Label city = new Label("City:");
    Label state = new Label("Staete:");
    Label zipcode = new Label("Zipcode:");
    Label petitionFName = new Label("Petitioner First Name: ");
    Label petitionLName = new Label("Petitioner Last Name: ");
    Label petitionDOB = new Label("Petitioner's Date of Birth");
    Label petitionANum = new Label("Petitioner Alien Number:");
    // vars to display form information
    TextArea formID_text = new TextArea();
    TextArea fName_text = new TextArea();
    TextArea lName_text = new TextArea();
    TextArea dob_text = new TextArea();
    TextArea aNum_text = new TextArea();
    TextArea address_text = new TextArea();
    TextArea city_text = new TextArea();
    TextArea state_text = new TextArea();
    TextArea zipcode_text = new TextArea();
    TextArea petitionFName_text = new TextArea();
    TextArea petitionLName_text = new TextArea();
    TextArea petitionDOB_text = new TextArea();
    TextArea petitionANum_text = new TextArea();
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
        // set properties to all form entries
        TextArea[] textAreas = { formID_text, fName_text, lName_text, dob_text, aNum_text, 
                                 address_text, city_text, state_text, zipcode_text,
                                 petitionFName_text, petitionLName_text, petitionDOB_text, 
                                 petitionANum_text };
        for (TextArea ta : textAreas) {
            ta.setPrefRowCount(1);
            ta.setPrefColumnCount(20);
            ta.setMinHeight(30);
            ta.setMaxHeight(30);
            ta.setEditable(false);
        }
        // left region
        VBox leftPanel = new VBox(10);
        // spacing for center region
        leftPanel.setPadding(new Insets(10));
        // button to retrieve available form for approval
        Button getForm = new Button("Get Available Form");
        getForm.setOnAction(e -> {
            // attempt to assign values to attributes
            findAvailableForm();
        });
        leftPanel.getChildren().addAll(getForm, formID, formID_text, fName, fName_text, lName, 
                                       lName_text, dob, dob_text, aNum, aNum_text, address, address_text, 
                                       city, city_text, state, state_text, zipcode, zipcode_text);
        // right panel
        VBox rightPanel = new VBox(10);
        // spacing for right panel
        rightPanel.setPadding(new Insets(10));
        // button to check if current petitioner already exit
        Button duplicate = new Button("Check if Duplicate");
        // config if dup button is pressed
        duplicate.setOnAction(e -> {
            // prompt user if dup/no dup
            if(SQLProcessor.moreThanOnePetNum(Integer.parseInt(petitionANum_text.getText()))) {
                showAlert("Duplicate Found", "Petitioner A-Num exists more than once");
            } else {
                showAlert("Not Duplicate Found", "There's only one existing Petitioner");
            }
        });
        // label to seperate control panel
        Label controlP = new Label("*********************************** Approval Control Panel ***********************************");
        // button to approve document
        approveButton = new Button("Approve Form");
        // button to reject document
        rejectButton = new Button("Reject Form");
        // disable rejection button by default
        rejectButton.setDisable(true);
        // button to save rejection note
        Button saveButton = new Button("Save Changes");
        // button to delete/discard rejection note
        Button undoButton = new Button("Discard Changes");
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
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you confirm approval of the following form?\n\n", ButtonType.YES, ButtonType.NO);
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
        rightPanel.getChildren().addAll(petitionFName, petitionFName_text, petitionLName, petitionLName_text, 
                                        petitionDOB, petitionDOB_text, petitionANum, petitionANum_text, duplicate,
                                        controlP, approveButton, rejectButton, reasonArea, saveButton, undoButton);
        // assign regions to root
        BorderPane layout = new BorderPane();
        layout.setLeft(leftPanel);
        // layout.setCenter(leftPanel);
        layout.setRight(rightPanel);
        // set dimensions
        Scene scene = new Scene(layout, 1000, 800);
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
    private void findAvailableForm() {
        // var to store form id
        int formID = SQLProcessor.availableForm("approve");
        // exit if not available
        if(formID == -1) {
            return;
        }
        // get form
        Form form = SQLProcessor.retrieveForm(formID);
        // get petitioner
        Petitioner pet = SQLProcessor.retrievePetitioner(form.getPetitionerANum());
        // get relative
        Relative rel = SQLProcessor.retrieveRelative(form.getRelativeANum());
        // set form attributes
        formID_text.setText(String.valueOf(formID));
        fName_text.setText(rel.getFirstName());
        lName_text.setText(rel.getLastName());
        dob_text.setText(String.valueOf(rel.getDOB()));
        aNum_text.setText(String.valueOf(rel.getANumRel()));
        address_text.setText(form.getAddress());
        city_text.setText(form.getCity());
        state_text.setText(form.getState());
        zipcode_text.setText(String.valueOf(form.getZipCode()));
        petitionFName_text.setText(String.valueOf(pet.getFirstName()));
        petitionLName_text.setText(String.valueOf(pet.getLastName()));
        petitionDOB_text.setText(String.valueOf(pet.getDOB()));
        petitionANum_text.setText(String.valueOf(pet.getANum()));
    }
} 
