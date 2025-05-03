// packages required
package edu.gmu.cs321;
// java fx objects and additional tools
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
     * Button for rejection.
     */
    private Button rejectButton = new Button("Reject Form");

    /**
     * Button for approval.
     */
    private Button approveButton = new Button("Approve Form");

    /**
     * Button to close approver ui.
     */
    private Button signOutButton = new Button("Sign Out");

    /**
     * button to retrieve available form for approval
     */
    private Button getForm = new Button("Get Available Form");

    /**
     * Button to clear fields.
     */
    private Button clearEntries = new Button("Clear Entries");

    /**
     * Button for validation.
     */
    private Button validate = new Button ("Validator Switch");

    /**
     * Button to check list of forms available.
     */
    private Button listOfForms = new Button ("Check Queue");

    /**
     * Button to locate specified form.
     */
    private Button retrieveForm = new Button ("Retrieve");

    /**
     * Tracks if validation mode is on/off
     */
    private boolean isValidate = false;

    /**
     * Tells approver if validator is on.
     */
    private TextArea validMSG = new TextArea("Validator:  ON  [OFF]");

    /**
     * Textbox for reason of rejection,
     */
    private TextArea reasonArea = new TextArea();

    /**
     * Text area to enter specified form.
     */
    private TextArea searchForm = new TextArea();

    /**
     * Form object
     */
    private Form form;
    /**
     * Holds form id for MySQL table
     */
    private int formID;

    // vars to display form information
    private TextArea formID_text = new TextArea();
    private TextArea fName_text = new TextArea();
    private TextArea lName_text = new TextArea();
    private TextArea dob_text = new TextArea();
    private TextArea aNum_text = new TextArea();
    private TextArea address_text = new TextArea();
    private TextArea city_text = new TextArea();
    private TextArea state_text = new TextArea();
    private TextArea zipcode_text = new TextArea();
    private TextArea petitionFName_text = new TextArea();
    private TextArea petitionLName_text = new TextArea();
    private TextArea petitionDOB_text = new TextArea();
    private TextArea petitionANum_text = new TextArea();
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
        // display form information header
        Label quickStartLabel = new Label("[Quick Start]");
        Label formDetails = new Label("[Applicant Details]");
        Label formID = new Label("Form ID: ");
        Label fName = new Label("First Name: ");
        Label lName = new Label("Last Name: ");
        Label dob = new Label("Date of Birth: ");
        Label aNum = new Label("Alien Number: ");
        Label address = new Label("Address:");
        Label city = new Label("City:");
        Label state = new Label("State:");
        Label zipcode = new Label("Zipcode:");
        Label petitionFName = new Label("Petitioner First Name: ");
        Label petitionLName = new Label("Petitioner Last Name: ");
        Label petitionDOB = new Label("Petitioner's Date of Birth");
        Label petitionANum = new Label("Petitioner Alien Number:");


        // set get form button config
        getForm.setOnAction(e -> {
            // attempt to assign values to attributes
            findAvailableForm();
        });

        // config for clear entries button
        clearEntries.setOnAction(e -> {
            // clear text fields
            clearEntries();
        });

        // config for sign out button
        signOutButton.setOnAction(e -> {
            primaryStage.close();
            new LoginScreen().start(new Stage());
        });

        validMSG.setPrefRowCount(1);
        validMSG.setPrefColumnCount(10);
        validMSG.setMinHeight(25);
        validMSG.setMaxHeight(25);
        validMSG.setEditable(false);

        // area for specific buttons together
        GridPane generalSet = new GridPane();
        generalSet.add(getForm,  0, 0);
        generalSet.add(clearEntries, 1, 0);
        generalSet.add(signOutButton, 3, 0);
        generalSet.setHgap(5);

        leftPanel.getChildren().addAll(quickStartLabel, generalSet, formDetails, formID, formID_text, fName, fName_text, lName, 
                                       lName_text, dob, dob_text, aNum, aNum_text, address, address_text, 
                                       city, city_text, state, state_text, zipcode, zipcode_text);
        // right panel
        VBox rightPanel = new VBox(10);
        // spacing for right panel
        rightPanel.setPadding(new Insets(10));
        // button to check if current petitioner already exit


        listOfForms.setOnAction(e -> {
            showAlert("List of Forms", SQLProcessor.strAvailForms("toApprove"));
        });


        retrieveForm.setOnAction(e -> {
            setForm(Integer.valueOf(searchForm.getText()));
        });

        Button duplicate = new Button("Check if Duplicate");
        // config if dup button is pressed
        duplicate.setOnAction(e -> {
            // prompt user if dup/no dup
            if(SQLProcessor.moreThanOnePetNum(Integer.parseInt(petitionANum_text.getText()))) {
                showAlert("Duplicate Finder", "Same Relative A-Num(s) Found.");
            } else {
                showAlert("Duplicate Finder", "No Duplicates Found.");
            }
        });

        // label to seperate control panel
        Label manualSearchLabel = new Label("[Manual Search]");
        Label petDetailsLabel = new Label("[Petitioner Details]");
        Label validatorLabel = new Label("[Validator]");
        Label controlPanelLabel = new Label("[Control Panel]");

        // disable rejection button by default
        rejectButton.setDisable(true);
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
                    approveForm();
                    showAlert("Form Approved", "The form has been approved.");
                }
            });
        });

        rejectButton.setOnAction(e -> {
                // confirm if approver wants to approve form (y/n alert box)
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you confirm rejection of the following form?\n\n", ButtonType.YES, ButtonType.NO);
                // header/title of messgae box
                confirm.setHeaderText("Confirm Rejection");
                // prompt alert box if approver clicks yes
                confirm.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        rejectForm();
                        showAlert("Form Rejected", "The form has been rejected.");
                    }
                });
        });


        searchForm.setPrefRowCount(1);
        searchForm.setPrefColumnCount(10);
        searchForm.setMinHeight(25);
        searchForm.setMaxHeight(25);
        searchForm.setEditable(true);

        GridPane locator = new GridPane();
        locator.add(listOfForms, 0, 0);
        locator.add(searchForm, 1, 0);
        locator.add(retrieveForm, 3, 0);
        locator.setHgap(5);

        GridPane controlPanel = new GridPane();
        controlPanel.add(duplicate, 0, 0);
        controlPanel.add(rejectButton, 1, 0);
        controlPanel.add(approveButton, 3, 0);
        controlPanel.setHgap(5);

        validate.setOnAction(e -> {
            //validateOn.setDisable(true);
            if(isValidate) {
                disableEdit();
                validMSG.setText("Validator:  ON  [OFF]");
                isValidate = false;
            } else {
                enableEdit();
                validMSG.setText("Validator: [ON]  OFF ");
                isValidate = true;
            }
        });

        GridPane validationSet = new GridPane();
        validationSet.add(validate, 1, 0);
        validationSet.add(validMSG, 3, 0);
        validationSet.setHgap(5);

        // add buttons and text box to right region
        rightPanel.getChildren().addAll(manualSearchLabel, locator, petDetailsLabel, petitionFName, petitionFName_text, petitionLName, petitionLName_text, 
                                        petitionDOB, petitionDOB_text, petitionANum, petitionANum_text,
                                        validatorLabel, validationSet, controlPanelLabel, controlPanel, reasonArea);
        // assign regions to root
        BorderPane layout = new BorderPane();
        layout.setLeft(leftPanel);
        // layout.setCenter(leftPanel);
        layout.setRight(rightPanel);
        // set dimensions
        Scene scene = new Scene(layout, 900, 800);
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
        // custom dimension with form list
        if(title.equals("List of Forms")) {
            alertBox.getDialogPane().setPrefSize(800, 400);
        }
        // assign title to alert box
        alertBox.setTitle(title);
        alertBox.setHeaderText(null);
        // set body to alert box
        alertBox.setContentText(alert);
        alertBox.showAndWait();
    }

    private void setForm(int id) {
        formID = id;
        // get form
        form = SQLProcessor.retrieveForm(formID);
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

    private void findAvailableForm() {
        // var to store form id
        formID = SQLProcessor.availableForm("toApprove");
        // exit if not available
        if(formID == -1) {
            return;
        }
        // get form
        setForm(formID);
    }

    private void rejectForm() {
        // set status to reject and add reject reason
        form.setRejectionReason(reasonArea.getText());
        form.setStatus("rejected");
        // modify table to reflect changes
        SQLProcessor.modifyForm(formID, form);
    }

    private void approveForm() {
        // set status to submit
        form.setStatus("approved");
        // modify table to reflect changes
        SQLProcessor.modifyForm(formID, form);
    }

    private void clearEntries() {
        TextArea[] textAreas = { formID_text, fName_text, lName_text, dob_text, aNum_text, 
            address_text, city_text, state_text, zipcode_text,
            petitionFName_text, petitionLName_text, petitionDOB_text, 
            petitionANum_text };
        for (TextArea ta : textAreas) {
            ta.setText("");
        }
     }

     private void enableEdit() {
        TextArea[] textAreas = { formID_text, fName_text, lName_text, dob_text, aNum_text, 
            address_text, city_text, state_text, zipcode_text,
            petitionFName_text, petitionLName_text, petitionDOB_text, 
            petitionANum_text };
        for (TextArea ta : textAreas) {
            ta.setEditable(true);
        }
     }

     private void disableEdit() {
        TextArea[] textAreas = { formID_text, fName_text, lName_text, dob_text, aNum_text, 
            address_text, city_text, state_text, zipcode_text,
            petitionFName_text, petitionLName_text, petitionDOB_text, 
            petitionANum_text };
        for (TextArea ta : textAreas) {
            ta.setEditable(false);
        }
     }
} 
