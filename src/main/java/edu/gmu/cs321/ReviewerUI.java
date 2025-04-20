package edu.gmu.cs321;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;

import java.lang.Integer;

public class ReviewerUI extends Application{

    //Text fields

    //general info
    private Text formIDInfo;
    private Text employeeID;
    private Text applicationDate;

    //relative info
    private Text field1;
    private TextField relativeFName;
    private Text field2;
    private TextField relativeLName;
    private Text field3;
    private TextField relativeBirthDate;
    private Text field4;
    private TextField relativeANum;
    private Text field5;
    private TextField relativeAddress;
    private Text field6;
    private TextField relativeCity;
    private Text field7;
    private TextField relativeState;
    private Text field8;
    private TextField relativeZip;

    //petitioner info
    private Text field9;
    private TextField petitionerFName;
    private Text field10;
    private TextField petitionerLName;
    private Text field11;
    private TextField petitionerBirthDate;
    private Text field12;
    private TextField petitionerANum;

    //rejection message
    private Text field13;
    private TextField rejectionMessage;

    //Error from validation
    Text emptyFields = new Text();

    //screen components
    Group root;
    Scene scene;
    Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        root = new Group();
        scene = new Scene(root,  900, 600);
        stage = new Stage();

        //Seperator at top of screen
        Line topLine = new Line();
        topLine.setStartX(0);
        topLine.setStartY(30);
        topLine.setEndX(900);
        topLine.setEndY(30);

        //sperator at bottom of screen
        Line botLine1 = new Line();
        botLine1.setStartX(0);
        botLine1.setStartY(500);
        botLine1.setEndX(900);
        botLine1.setEndY(500);

        Line botLine2 = new Line();
        botLine2.setStartX(0);
        botLine2.setStartY(505);
        botLine2.setEndX(900);
        botLine2.setEndY(505);

        Text employeeID = new Text();
        //employeeID.setText("Employee ID: 000000");
        //employeeID.setText("reviewer");
        employeeID.setX(20);
        employeeID.setY(530);

        Text formIDInfo = new Text();
        formIDInfo.setText("Form ID: 000000");
        formIDInfo.setX(20);
        formIDInfo.setY(550);

        Text applicationDate = new Text();
        applicationDate.setText("Application Date: 00/00/0000");
        applicationDate.setX(20);
        applicationDate.setY(570);

        //Not needed
        /*ScrollBar scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.VERTICAL);
        scrollBar.setLayoutX(880);
        scrollBar.setLayoutY(50);
        scrollBar.setMinHeight(430);
        scrollBar.setMin(50);*/

        //buttons to execute different tasks
        ButtonBar buttonBar = new ButtonBar();
        
        //Could implement next sprint
        /*Button editButton = new Button("Edit Form");
        ButtonBar.setButtonData(editButton, ButtonData.NEXT_FORWARD);*/

        //could implement next sprint
        /*Button saveButton = new Button("Save Form");
        ButtonBar.setButtonData(saveButton, ButtonData.APPLY);*/

        //may implement next sprint
        Button logoutButton = new Button("Logout");
        //ButtonBar.setButtonData(logoutButton, ButtonData.BACK_PREVIOUS);

        logoutButton.setOnAction(e -> {
            primaryStage.close();
            new LoginScreen().start(new Stage());
        });

        Button getFormButton = new Button("Get Form");
        //ButtonBar.setButtonData(getFormButton, ButtonData.NEXT_FORWARD);
        getFormButton.setOnAction(e -> nextForm());

        Button rejectedButton = new Button("Get Rejected Form");
        //ButtonBar.setButtonData(rejectedButton, ButtonData.NEXT_FORWARD);
        rejectedButton.setOnAction(e -> nextRejectedForm());
        
        //call the validate function and print success/failure message
        Button validateButton = new Button("Validate");
        //ButtonBar.setButtonData(validateButton, ButtonData.NEXT_FORWARD);
        validateButton.setOnAction(e -> validateFields());

        //update current item and switchflage to "approve" or "toapprove"
        Button submitButton = new Button("Submit for Approval");
        //ButtonBar.setButtonData(submitButton, ButtonData.FINISH);
        submitButton.setOnAction(e -> submitForm());

        //Adjusted
        //buttonBar.getButtons().addAll(editButton, saveButton, revalidateButton, submitButton);
        buttonBar.getButtons().addAll(logoutButton, getFormButton, rejectedButton, validateButton, submitButton);
        buttonBar.setLayoutX(200);
        buttonBar.setLayoutY(530);

        //form data fields

        //relative info
        field1 = new Text();
        field1.setText("Relative First Name*");
        field1.setX(20);
        field1.setY(50);

        relativeFName = new TextField("First Name");
        relativeFName.setLayoutX(20);
        relativeFName.setLayoutY(55);

        field2 = new Text();
        field2.setText("Relative Last Name*");
        field2.setX(20);
        field2.setY(95);

        relativeLName = new TextField("Last Name");
        relativeLName.setLayoutX(20);
        relativeLName.setLayoutY(100);

        field3 = new Text();
        field3.setText("Relative Date of Birth*");
        field3.setX(20);
        field3.setY(140);

        //int only
        relativeBirthDate = new TextField("YYYYMMDD");
        relativeBirthDate.setLayoutX(20);
        relativeBirthDate.setLayoutY(145);

        field4 = new Text();
        field4.setText("Relative Alien Number*");
        field4.setX(20);
        field4.setY(185);

        //int only and max 9 digits
        relativeANum = new TextField("Ex: 123456789");
        relativeANum.setLayoutX(20);
        relativeANum.setLayoutY(190);

        field5 = new Text();
        field5.setText("Relative Address*");
        field5.setX(20);
        field5.setY(230);

        relativeAddress = new TextField("Address");
        relativeAddress.setLayoutX(20);
        relativeAddress.setLayoutY(235);

        field6 = new Text();
        field6.setText("Relative City*");
        field6.setX(20);
        field6.setY(275);

        relativeCity = new TextField("City");
        relativeCity.setLayoutX(20);
        relativeCity.setLayoutY(280);

        //Limited to length two and all caps
        field7 = new Text();
        field7.setText("Relative State*");
        field7.setX(20);
        field7.setY(320);

        relativeState = new TextField("Ex: VA");
        relativeState.setLayoutX(20);
        relativeState.setLayoutY(325);

        field8 = new Text();
        field8.setText("Relative Zipcode*");
        field8.setX(20);
        field8.setY(365);

        //max 5 digits
        relativeZip = new TextField("EX: 12345");
        relativeZip.setLayoutX(20);
        relativeZip.setLayoutY(370);

        //petitioner info
        field9 = new Text();
        field9.setText("Petitioner First Name*");
        field9.setX(200);
        field9.setY(50);

        petitionerFName = new TextField("First Name");
        petitionerFName.setLayoutX(200);
        petitionerFName.setLayoutY(55);

        field10 = new Text();
        field10.setText("Petitioner Last Name*");
        field10.setX(200);
        field10.setY(95);

        petitionerLName = new TextField("Last Name");
        petitionerLName.setLayoutX(200);
        petitionerLName.setLayoutY(100);

        field11 = new Text();
        field11.setText("Petitioner Date of Birth*");
        field11.setX(200);
        field11.setY(140);

        //int only
        petitionerBirthDate = new TextField("YYYYMMDD");
        petitionerBirthDate.setLayoutX(200);
        petitionerBirthDate.setLayoutY(145);

        field12 = new Text();
        field12.setText("Petitioner Alien Number*");
        field12.setX(200);
        field12.setY(185);
        
        //int only and max 9 digits
        petitionerANum = new TextField("Ex: 123456789");
        petitionerANum.setLayoutX(200);
        petitionerANum.setLayoutY(190);
        
        //if form was rejected, then show rejection message by approver
        field13 = new Text();
        field13.setText("Reason of Rejection*");
        field13.setX(400);
        field13.setY(50);
        
        //int only and max 9 digits
        rejectionMessage = new TextField("Reason");
        rejectionMessage.setLayoutX(400);
        rejectionMessage.setLayoutY(55);
        
        //Error from validation
        emptyFields = new Text();
        emptyFields.setText("Some of the fields are empty.");
        emptyFields.setX(400);
        emptyFields.setY(95);
        
        stage.setScene(scene);
        
        //seperators
        root.getChildren().add(topLine);
        root.getChildren().add(botLine1);
        root.getChildren().add(botLine2);
        
        //form info
        //root.getChildren().add(employeeID);
        //root.getChildren().add(formIDInfo);
        //root.getChildren().add(applicationDate);
        
        //root.getChildren().add(scrollBar); //not needed
        
        //buttons
        root.getChildren().add(buttonBar);
        
        //relative data fields
        root.getChildren().add(field1);
        root.getChildren().add(relativeFName);
        root.getChildren().add(field2);
        root.getChildren().add(relativeLName);
        root.getChildren().add(field3);
        root.getChildren().add(relativeBirthDate);
        root.getChildren().add(field4);
        root.getChildren().add(relativeANum);
        root.getChildren().add(field5);
        root.getChildren().add(relativeAddress);
        root.getChildren().add(field6);
        root.getChildren().add(relativeCity);
        root.getChildren().add(field7);
        root.getChildren().add(relativeState);
        root.getChildren().add(field8);
        root.getChildren().add(relativeZip);

        //petitioner data fields
        root.getChildren().add(field9);
        root.getChildren().add(petitionerFName);
        root.getChildren().add(field10);
        root.getChildren().add(petitionerLName);
        root.getChildren().add(field11);
        root.getChildren().add(petitionerBirthDate);
        root.getChildren().add(field12);
        root.getChildren().add(petitionerANum);

        //rejection message
        root.getChildren().add(field13);
        root.getChildren().add(rejectionMessage);

        //validation empty fields error
        //root.getChildren().add(emptyFields);

        stage.show();
    }
    
    private Form form;
    private int formID;

    private Relative relative;
    private Petitioner petitioner;
    
    //form retrieval
    private void nextForm() {

        formID = SQLProcessor.availableForm("toReview");

        if(formID != -1) {
            form = SQLProcessor.retrieveForm(formID);

            //formIDInfo.setText(String.valueOf(formID));
            
            relative = SQLProcessor.retrieveRelative(form.getRelativeANum());

            petitioner = SQLProcessor.retrievePetitioner(form.getPetitionerANum());
            
            relativeFName.setText(relative.getFirstName());
            relativeLName.setText(relative.getLastName());
            relativeBirthDate.setText(String.valueOf(relative.getDOB()));
            relativeANum.setText(String.valueOf(relative.getANumRel()));
            relativeAddress.setText(form.getAddress());
            relativeCity.setText(form.getCity());
            relativeState.setText(form.getState());
            relativeZip.setText(String.valueOf(form.getZipCode()));

            petitionerFName.setText(petitioner.getFirstName());
            petitionerLName.setText(petitioner.getLastName());
            petitionerBirthDate.setText(String.valueOf(petitioner.getDOB()));
            petitionerANum.setText(String.valueOf(petitioner.getANum()));
            
            rejectionMessage.setText("None");
        }    
    }

    //return form (possibly modified) to workflow/database
    private void submitForm() {

        //relative table
        //relative.setFirstName(relativeFName.getText());
        //relative.setLastName(relativeLName.getText());
        //relative.setDOB(Integer.parseInt(relativeBirthDate.getText()));

        //petition table
        //petitioner.setFirstName(petitionerFName.getText());
        //petitioner.setLastName(petitionerLName.getText());
        //petitioner.setDOB(Integer.parseInt(petitionerBirthDate.getText()));
        
        form.setAddress(relativeAddress.getText());
        form.setCity(relativeCity.getText());
        form.setState(relativeState.getText());
        form.setZipCode(Integer.parseInt(relativeZip.getText()));
        
        form.setRelativeANum(Integer.parseInt(relativeANum.getText()));
        form.setPetitionerANum(Integer.parseInt(petitionerANum.getText()));
        form.setStatus("toApprove");
        
        SQLProcessor.modifyForm(formID, form);
        
    }

    //form validation
    private void validateFields() {
        TextField dataFields[] = {relativeFName, relativeLName, relativeBirthDate, relativeANum,
            relativeAddress, relativeCity, relativeState, relativeZip, petitionerFName, petitionerLName, petitionerBirthDate,
            petitionerANum};

        //boolean empty = false;
        for(int i = 0; i < dataFields.length; i++) {
            if(dataFields[i].getText() == "") {

                //empty = true;
                root.getChildren().add(emptyFields);
            }
            else {
                root.getChildren().remove(emptyFields);
            }
        }
    }

    //get the next rejected item from approver
    private void nextRejectedForm() {

        formID = SQLProcessor.availableForm("rejected");

        if(formID != -1) {
            form = SQLProcessor.retrieveForm(formID);

            //formIDInfo.setText(String.valueOf(formID));
            
            Relative relative = SQLProcessor.retrieveRelative(form.getRelativeANum());

            Petitioner petitioner = SQLProcessor.retrievePetitioner(form.getPetitionerANum());
            
            relativeFName.setText(relative.getFirstName());
            relativeLName.setText(relative.getLastName());
            relativeBirthDate.setText(String.valueOf(relative.getDOB()));
            relativeANum.setText(String.valueOf(relative.getANumRel()));
            relativeAddress.setText(form.getAddress());
            relativeCity.setText(form.getCity());
            relativeState.setText(form.getState());
            relativeZip.setText(String.valueOf(form.getZipCode()));

            petitionerFName.setText(petitioner.getFirstName());
            petitionerLName.setText(petitioner.getLastName());
            petitionerBirthDate.setText(String.valueOf(petitioner.getDOB()));
            petitionerANum.setText(String.valueOf(petitioner.getANum()));

            rejectionMessage.setText(form.getRejectionReason());
        
        }
    }

}
