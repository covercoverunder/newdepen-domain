package edu.gmu.cs321;

import javafx.application.Application;
//import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
//import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;

import java.lang.Integer;

import com.google.protobuf.StringValue;

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
    
    //Search ready for review form
    private Text field13;
    private TextField pickForm;
    
    //Search for rejected form
    /*private Text field14;
    private TextField searchRejected;*/
    
    //rejection message
    private Text field15;
    //private TextField rejectionMessage;
    private TextArea rejectionMessage;

    //Error messages
    Text validatorGood = new Text();
    Text validatorErrors = new Text();
    Text noForm = new Text();

    //screen components
    Group root;
    Scene scene;
    //Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        root = new Group();
        scene = new Scene(root,  1040, 600);
        primaryStage.setTitle("Review UI");
        //stage = new Stage();

        /*Group root2;
        Scene scene2;
        Stage stage2 = new Stage();

        root2 = new Group();
        Text bazinga = new Text("Bazinga");
        root2.getChildren().add(bazinga);
        scene2 = new Scene(root,  100, 100);
        stage2.setScene(scene2);
        stage2.show();*/
        
        //Seperator at top of screen
        Line topLine = new Line();
        topLine.setStartX(0);
        topLine.setStartY(30);
        topLine.setEndX(scene.getWidth());
        topLine.setEndY(30);
        
        //Seperator in middle that is vertical
        Line verticalLine1 = new Line();
        topLine.setStartX(370);
        topLine.setStartY(0);
        topLine.setEndX(370);
        topLine.setEndY(scene.getHeight());

        /*Line verticalLine2 = new Line();
        topLine.setStartX(375);
        topLine.setStartY(0);
        topLine.setEndX(375);
        topLine.setEndY(scene.getHeight());*/

        //sperator at bottom of screen
        Line botLine1 = new Line();
        botLine1.setStartX(0);
        botLine1.setStartY(500);
        botLine1.setEndX(370);
        botLine1.setEndY(500);

        Line botLine2 = new Line();
        botLine2.setStartX(0);
        botLine2.setStartY(505);
        botLine2.setEndX(370);
        botLine2.setEndY(505);

        employeeID = new Text();
        //employeeID.setText("Employee ID: 000000");
        employeeID.setText("Logged in as: Reviewer");
        employeeID.setX(20);
        employeeID.setY(530);

        formIDInfo = new Text();
        formIDInfo.setText("Form ID: " + formID);
        formIDInfo.setX(200);
        formIDInfo.setY(530);

        //not active
        applicationDate = new Text();
        applicationDate.setText("Application Date: 00/00/0000");
        applicationDate.setX(200);
        applicationDate.setY(550);

        //Not needed
        /*ScrollBar scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.VERTICAL);
        scrollBar.setLayoutX(880);
        scrollBar.setLayoutY(50);
        scrollBar.setMinHeight(430);
        scrollBar.setMin(50);*/

        
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
        
        //Enter the id to choose a specific form that is ready for review
        field13 = new Text();
        //field13.setText("Open form ready for review");
        //field13.setX(400);
        //field13.setY(50);
        field13.setX(400);
        field13.setY(140);
        
        //enter integer
        pickForm = new TextField("Enter form id number");
        pickForm.setLayoutX(550); //was 550
        pickForm.setLayoutY(55); //was 55
        //searchReadyForReview.setLayoutX(400);
        //searchReadyForReview.setLayoutY(55);
        
        //Enter id to choose a specific form that was rejected by approver
        /*field14 = new Text();
        field14.setText("Open form rejected by approver");
        //field14.setX(400);
        //field14.setY(95);
        field14.setX(550);
        field14.setY(95);*/
        
        //enter integer
        /*searchRejected = new TextField("Enter form id number");
        searchRejected.setLayoutX(550);
        searchRejected.setLayoutY(100);*/
        
        //if form was rejected, then show rejection message by approver
        field15 = new Text();
        field15.setText("Reason for Rejection:");
        field15.setX(400);
        field15.setY(360);
        
        //Text for aprover note
        //rejectionMessage = new TextField("Approver note");
        //rejectionMessage.setLayoutX(400);
        //rejectionMessage.setLayoutY(145);
        
        rejectionMessage = new TextArea("Approver Note");
        rejectionMessage.setLayoutX(550);
        rejectionMessage.setLayoutY(350);
        
        TextArea formList = new TextArea(SQLProcessor.strAvailForms("toReview"));
        formList.setText("Form list");
        formList.setLayoutX(550);
        formList.setLayoutY(100);

        //Error from validation
        //emptyFields = new Text();
        //emptyFields.setText("");
        validatorGood.setX(270);
        validatorGood.setY(255);
        validatorErrors.setX(200);
        validatorErrors.setY(280);
        /*badDate
        badANum
        badName
        badState
        badZip*/

        noForm = new Text();
        //noForm.setText("");
        noForm.setX(705);
        noForm.setY(70);
        
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
        logoutButton.setLayoutX(20);
        logoutButton.setLayoutY(535);  
        //ButtonBar.setButtonData(logoutButton, ButtonData.BACK_PREVIOUS);

        logoutButton.setOnAction(e -> {
            primaryStage.close();
            new LoginScreen().start(new Stage());
        });

        Button getReadyFormButton = new Button("Open Form");  
        //getReadyFormButton.setLayoutX(550);
        //getReadyFormButton.setLayoutY(55);  
        getReadyFormButton.setLayoutX(400); 
        getReadyFormButton.setLayoutY(55);  
        getReadyFormButton.setOnAction(e -> pickForm());
        
        /*Button getRejectedFormButton = new Button("Get Rejected Form");
        getRejectedFormButton.setLayoutX(400);
        getRejectedFormButton.setLayoutY(100);       
        getRejectedFormButton.setOnAction(e -> pickRejectedForm());*/
        
        Button getReadyListButton = new Button("Ready For Review");
        getReadyListButton.setLayoutX(400);
        getReadyListButton.setLayoutY(145);  
        getReadyListButton.setOnAction(e -> {
            formList.setText(SQLProcessor.strAvailForms("toReview"));
            field13.setText("Displaying \"To Review\"");
        });
        
        Button getRejectedListButton = new Button("Rejected");
        getRejectedListButton.setLayoutX(400);
        getRejectedListButton.setLayoutY(190);  
        getRejectedListButton.setOnAction(e -> {
            formList.setText(SQLProcessor.strAvailForms("rejected"));
            field13.setText("Displaying \"Rejected\"");
        });
        
        //call the validate function and print success/failure message
        Button validateButton = new Button("Validate");
        validateButton.setLayoutX(200);
        validateButton.setLayoutY(235);   
        //ButtonBar.setButtonData(validateButton, ButtonData.NEXT_FORWARD);
        validateButton.setOnAction(e -> validateFields());

        //update current item and switchflage to "approve" or "toapprove"
        Button submitButton = new Button("Submit for Approval");
        submitButton.setLayoutX(200);
        submitButton.setLayoutY(370);   
        //ButtonBar.setButtonData(submitButton, ButtonData.FINISH);
        submitButton.setOnAction(e -> submitForm());

        //Adjusted
        //buttonBar.getButtons().addAll(editButton, saveButton, revalidateButton, submitButton);
        //buttonBar.getButtons().addAll(logoutButton, validateButton, submitButton);
        //buttonBar.setLayoutX(200);
        //buttonBar.setLayoutY(530);

        //stage.setScene(scene);
        primaryStage.setScene(scene);
        
        //seperators
        root.getChildren().add(topLine);
        root.getChildren().add(verticalLine1);
        //root.getChildren().add(verticalLine2);
        root.getChildren().add(botLine1);
        root.getChildren().add(botLine2);
        
        //General info
        root.getChildren().add(employeeID);
        root.getChildren().add(formIDInfo);
        root.getChildren().add(applicationDate);
        
        //root.getChildren().add(scrollBar); //not needed
        
        //buttons
        root.getChildren().add(getReadyFormButton);
        //root.getChildren().add(getRejectedFormButton);
        root.getChildren().add(getReadyListButton);
        root.getChildren().add(getRejectedListButton);
        root.getChildren().add(validateButton);
        root.getChildren().add(submitButton);
        root.getChildren().add(logoutButton);
        //root.getChildren().add(buttonBar);
        
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

        //form retrieval fields
        root.getChildren().add(field13);
        root.getChildren().add(pickForm);
        //root.getChildren().add(field14);
        //root.getChildren().add(searchRejected);

        //rejection message
        //root.getChildren().add(field15);
        //root.getChildren().add(rejectionMessage);

        //root.getChildren().add(field15);
        root.getChildren().add(formList);

        //validation empty fields error
        //root.getChildren().add(emptyFields);
        //root.getChildren().add(noForm);

        //stage.show();
        primaryStage.show();
    }
    
    private Form form;
    private int formID = 0;

    private Relative relative;
    private Petitioner petitioner;
    
    //form retrieval
    //private void nextForm() {
    /*private void pickFormReadyForReview() {
        //formID = SQLProcessor.availableForm("toReview");
        formID = Integer.parseInt(pickForm.getText());
        
        form = SQLProcessor.retrieveForm(formID);
        
        if(form != null && formID > 0 && String.valueOf(form.getStatus()).equals("toReview")) {
            
            formIDInfo.setText(String.valueOf("Form ID: " + formID));

            String formDate = String.valueOf(form.getApplicationDate());
            StringBuilder dateFormatted = new StringBuilder("Application Date: ");

            for(int i = 0; i < formDate.length(); i++ ) {
                if(i == 2 || i == 4) {
                    dateFormatted.append("/");
                }

                dateFormatted.append(formDate.charAt(i));
            }

            if(root.getChildren().contains(rejectionMessage) && root.getChildren().contains(field15)) {
                root.getChildren().remove(rejectionMessage);
                root.getChildren().remove(field15);
            }

            applicationDate.setText(String.valueOf(dateFormatted));
          
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
        else {
            Text noForm = new Text("No forms available");
            noForm.setX(400);
            noForm.setY(200);
            root.getChildren().add(noForm);
        } 
    }*/

    //BETTER USE THIS
    private void pickForm() {

        try {
            formID = Integer.parseInt(pickForm.getText());          
            form = SQLProcessor.retrieveForm(formID);
            
            if(form != null && formID > 0) {
    
                if(root.getChildren().contains(noForm)) {
                    root.getChildren().remove(noForm);
                }

                if(root.getChildren().contains(validatorGood)) {
                    root.getChildren().remove(validatorGood);
                }

                if(root.getChildren().contains(validatorErrors)) {
                    root.getChildren().remove(validatorErrors);
                }
                
                formIDInfo.setText(String.valueOf("Form ID: " + formID));
    
                /*String formDate = String.valueOf(form.getApplicationDate());
                StringBuilder dateFormatted = new StringBuilder("Application Date: ");
    
                for(int i = 0; i < formDate.length(); i++ ) {
                    if(i == 4 || i == 6) {
                        dateFormatted.append("/");
                    }
    
                    dateFormatted.append(formDate.charAt(i));
                }*/
                
                if(String.valueOf(form.getStatus()).equals("toReview") && root.getChildren().contains(rejectionMessage)
                    && root.getChildren().contains(field15)) {
                    
                    root.getChildren().remove(rejectionMessage);
                    root.getChildren().remove(field15);
                }
                else if(String.valueOf(form.getStatus()).equals("rejected") && !root.getChildren().contains(rejectionMessage)
                    && !root.getChildren().contains(field15)) {
                    
                    root.getChildren().add(rejectionMessage);
                    root.getChildren().add(field15);              
                    rejectionMessage.setText(form.getRejectionReason());
                }
                else if(String.valueOf(form.getStatus()).equals("rejected") && root.getChildren().contains(rejectionMessage)
                && root.getChildren().contains(field15)) {            
                    
                    rejectionMessage.setText(form.getRejectionReason());
                }
                
                //applicationDate.setText(String.valueOf(dateFormatted));
                applicationDate.setText("Application Date: " + formatDate(String.valueOf(form.getApplicationDate())));
                
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
                
            }
            else if(!root.getChildren().contains(noForm)) {
                    root.getChildren().add(noForm);
                if(formID <= 0) {
                    noForm.setText("Positive integers only");
                }
                else {
                    noForm.setText("This form does not exist");
                }
            }
            else if(root.getChildren().contains(noForm)) {
                if(formID <= 0) {
                    root.getChildren().remove(noForm);
                    root.getChildren().add(noForm);
                    noForm.setText("Positive integers only");
                }
                else {
                    noForm.setText("This form does not exist");
                }
            }
        } catch (NumberFormatException e) {
            if(root.getChildren().contains(noForm)) {
                noForm.setText("Invalid input");
            }
            else {
                root.getChildren().add(noForm);
                noForm.setText("Invalid input");
            }
        }
        
    }

    private String formatDate(String numberString) {
            StringBuilder dateFormatted = new StringBuilder();

            for(int i = 0; i < numberString.length(); i++ ) {
                if(i == 4 || i == 6) {
                    dateFormatted.append("/");
                }

                dateFormatted.append(numberString.charAt(i));
            }
            return String.valueOf(dateFormatted);
    }

    /*private String reverseFormatDate(String numberString) {
            StringBuilder dateFormatted = new StringBuilder();

            for(int i = 0; i < numberString.length(); i++ ) {
                if(i == 4 || i == 6) {
                    dateFormatted.deleteCharAt(i);
                }

            }
            return String.valueOf(dateFormatted);
    }*/

    //return form (possibly modified) to workflow/database
    private void submitForm() {

        //relative table
        relative.setFirstName(relativeFName.getText());
        relative.setLastName(relativeLName.getText());
        relative.setDOB(Integer.parseInt(relativeBirthDate.getText()));

        //petition table
        petitioner.setFirstName(petitionerFName.getText());
        petitioner.setLastName(petitionerLName.getText());
        petitioner.setDOB(Integer.parseInt(petitionerBirthDate.getText()));
        
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
        TextField[] allFields = {relativeFName, relativeLName, relativeBirthDate, relativeANum,
            relativeAddress, relativeCity, relativeState, relativeZip, petitionerFName, petitionerLName, petitionerBirthDate,
            petitionerANum};

        boolean empty = false;
        for(int i = 0; i < allFields.length; i++) {
            if(allFields[i].getText().length() == 0) {
                empty = true;
            }
        }
        Text emptyFields = new Text();

        //if(empty && !root.getChildren().contains(emptyFields)) {
        if(empty) {  
            emptyFields.setText("Some fields are empty\n");
            //root.getChildren().add(emptyFields);
        }
        /*else if(!empty) {
            root.getChildren().remove(emptyFields);
        }*/

        TextField[] dateFields = {relativeBirthDate, petitionerBirthDate};

        boolean badDate = false;
        for(int i = 0; i < dateFields.length; i++) {
            if(dateFields[i].getText().length() != 8 || dateFields[i].getText().contains(" ")) {
                badDate = true;
            }
            else {
                try {
                    Integer.parseInt(String.valueOf(dateFields[i].getText()));
                } catch (NumberFormatException e) {
                    badDate = true;
                }
            }
        }

        Text dateError = new Text();

        //if(badDate && !root.getChildren().contains(dateError)) {
        if(badDate) {
            dateError.setText("Invalid birth dates\n");
            //root.getChildren().add(dateError);
        }
        /*else if(!badDate) {
            root.getChildren().remove(dateError);
        }*/

        TextField[] aNumFields = {relativeANum, petitionerANum};

        boolean badANum = false;
        for(int i = 0; i < aNumFields.length; i++) {
            if(aNumFields[i].getText().length() != 9 || aNumFields[i].getText().contains(" ")) {
                badANum = true;
            }
            else {
                try {
                    Integer.parseInt(String.valueOf(aNumFields[i].getText()));
                } catch (NumberFormatException e) {
                    badANum = true;
                }
            }
        }

        Text aNumError = new Text();

        //if(badANum && !root.getChildren().contains(aNumError)) {
        if(badANum) {
            aNumError.setText("Invalid A-numbers\n");
            //root.getChildren().add(aNumError);
        }
        /*else if(!badANum) {
            root.getChildren().remove(aNumError);
        }*/

        TextField[] nameFields = {relativeFName, relativeLName, petitionerFName, petitionerLName}; //FIXME

        boolean badName = false;
        for(int i = 0; i < nameFields.length; i++) {
            for(int j = 0; j < nameFields[i].getText().length(); j++) {
                try {
                    if(Integer.parseInt(String.valueOf(nameFields[i].getText().charAt(j))) >= 0) {
                        badName = true;
                    }
                } catch (NumberFormatException e) {
                    //badName = false;
                    e.getMessage();
                }
            }
        }

        Text nameError = new Text();

        //if(badName && !root.getChildren().contains(nameError)) {
        if(badName) {
            emptyFields.setText("Invalid names\n");
            //root.getChildren().add(nameError);
        }
        /*else if(!badName) {
            root.getChildren().remove(nameError);
        }*/

        boolean badState = false;
            if(relativeState.getText().length() != 2 || relativeState.getText().contains(" ")) {
                badState = true;
            }
            else {

                for(int i = 0; i < String.valueOf(relativeState.getText()).length(); i++) {
                    try {
                        if(Integer.parseInt(String.valueOf(relativeState.getText().charAt(i))) >= 0) {
                            badState = true;
                        }
                    } catch (NumberFormatException e) {
                        //badState = true;
                        e.getMessage();
                    }
                }
            }

        Text stateError = new Text();

        //if(badState && !root.getChildren().contains(stateError)) {
        if(badState) {
            stateError.setText("Invalid state\n");
            //root.getChildren().add(stateError);
        }
        /*else if(!badState) {
            root.getChildren().remove(stateError);
        }*/

        Text zipError = new Text();

        boolean badZip = false;
        try {
            if(relativeZip.getText().contains(" ")) {
                badZip = true;
            }
            Integer.parseInt(String.valueOf(relativeZip.getText()));
        } catch (NumberFormatException e) {
            badZip = true;
        }

        //if(badZip && !root.getChildren().contains(zipError)) {
        if(badZip) {
            zipError.setText("Invalid zipcode\n");
            //root.getChildren().add(zipError);
        }
        /*else if(!badZip) {
            root.getChildren().remove(zipError);
        }*/

        StringBuilder errorList = new StringBuilder();

        errorList.append(String.valueOf(emptyFields.getText())).append(String.valueOf(dateError.getText())).append(aNumError.getText())
            .append(String.valueOf(nameError.getText())).append(String.valueOf(stateError.getText()))
            .append(String.valueOf(zipError.getText()));


        validatorErrors.setText(String.valueOf(errorList));
        //root.getChildren().add(validatorErrors);
         
        if(errorList.length() == 0) {
            if(root.getChildren().contains(validatorErrors)) {
                root.getChildren().remove(validatorErrors);
            }
            
            if(!root.getChildren().contains(validatorGood)) { 
                root.getChildren().add(validatorGood);
            }
            else {
                validatorGood.setText("Valid");
            }
        }
        else if(errorList.length() > 0) {
            //root.getChildren().add(validatorGood);
            if(root.getChildren().contains(validatorGood)) { 
                root.getChildren().remove(validatorGood);
            }

            if(!root.getChildren().contains(validatorErrors)) {
                root.getChildren().add(validatorErrors);
            }
            else {
                validatorGood.setText("");
            }
        }
        /*else if(!root.getChildren().contains(validatorErrors) && !errorList.equals(null)) {
            validatorErrors.setText(String.valueOf(errorList));
            root.getChildren().remove(validatorGood);
            root.getChildren().remove(validatorErrors);
            validatorErrors.setText(String.valueOf(errorList));
        }
        else if(!root.getChildren().contains(validatorErrors) && errorList.equals(null)) {
            validatorErrors.setText(String.valueOf(errorList));
            root.getChildren().remove(validatorGood);
            root.getChildren().remove(validatorErrors);
            validatorErrors.setText(String.valueOf(errorList));
        }*/

    }

    //get the next rejected item from approver
    //private void nextRejectedForm() {
    /*private void pickRejectedForm() {

        //formID = SQLProcessor.availableForm("rejected");
        //formID = Integer.parseInt(searchRejected.getText());

        if(form != null && formID > 0 && String.valueOf(form.getStatus()).equals("rejected")) {
            form = SQLProcessor.retrieveForm(formID);

            formIDInfo.setText(String.valueOf("Form ID: " + formID));

            String formDate = String.valueOf(form.getApplicationDate());
            StringBuilder dateFormatted = new StringBuilder("Application Date: ");

            for(int i = 0; i < formDate.length(); i++ ) {
                if(i == 2 || i == 4) {
                    dateFormatted.append("/");
                }

                dateFormatted.append(formDate.charAt(i));
            }

            if(root.getChildren().contains(rejectionMessage) && root.getChildren().contains(field15)) {
                root.getChildren().add(rejectionMessage);
                root.getChildren().add(field15);
            }

            applicationDate.setText(String.valueOf(dateFormatted));
            
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

            rejectionMessage.setText(form.getRejectionReason());
        
        }
    }*/

}
