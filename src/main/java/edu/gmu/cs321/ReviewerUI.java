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

public class ReviewerUI extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        Scene scence = new Scene(root,  900, 600);
        Stage stage = new Stage();

        Line topLine = new Line();
        topLine.setStartX(0);
        topLine.setStartY(30);
        topLine.setEndX(900);
        topLine.setEndY(30);

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
        employeeID.setText("Employee ID: 000000");
        employeeID.setX(20);
        employeeID.setY(530);

        Text formID = new Text();
        formID.setText("Form ID: 000000");
        formID.setX(20);
        formID.setY(550);

        Text applicationDate = new Text();
        applicationDate.setText("Application Date: 00/00/0000");
        applicationDate.setX(20);
        applicationDate.setY(570);

        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.VERTICAL);
        scrollBar.setLayoutX(880);
        scrollBar.setLayoutY(50);
        scrollBar.setMinHeight(430);
        scrollBar.setMin(50);

        ButtonBar buttonBar = new ButtonBar();
        
        Button editButton = new Button("Edit Form");
        ButtonBar.setButtonData(editButton, ButtonData.NEXT_FORWARD);

        Button saveButton = new Button("Save Form");
        ButtonBar.setButtonData(saveButton, ButtonData.APPLY);
        
        Button revalidateButton = new Button("Revalidate");
        ButtonBar.setButtonData(revalidateButton, ButtonData.NEXT_FORWARD);

        Button submitButton = new Button("Submit for Approval");
        ButtonBar.setButtonData(submitButton, ButtonData.FINISH);

        buttonBar.getButtons().addAll(editButton, saveButton, revalidateButton, submitButton);
        buttonBar.setLayoutX(300);
        buttonBar.setLayoutY(530);

        Text field1 = new Text();
        field1.setText("Relative First Name*");
        field1.setX(20);
        field1.setY(50);

        TextField relativeFName = new TextField("First Name");
        relativeFName.setLayoutX(20);
        relativeFName.setLayoutY(55);

        Text field2 = new Text();
        field2.setText("Relative Last Name*");
        field2.setX(20);
        field2.setY(95);

        TextField relativeLName = new TextField("Last Name");
        relativeLName.setLayoutX(20);
        relativeLName.setLayoutY(100);

        Text field3 = new Text();
        field3.setText("Alien Number*");
        field3.setX(20);
        field3.setY(140);

        TextField alienNum = new TextField("A000-000-000");
        alienNum.setLayoutX(20);
        alienNum.setLayoutY(145);

        Text field4 = new Text();
        field4.setText("Date of Birth");
        field4.setX(20);
        field4.setY(185);

        TextField birthDate = new TextField("MM/DD/YYYY");
        birthDate.setLayoutX(20);
        birthDate.setLayoutY(190);

        Text field5 = new Text();
        field5.setText("Country of Origin*");
        field5.setX(20);
        field5.setY(185);

        TextField country = new TextField("Country");
        country.setLayoutX(20);
        country.setLayoutY(190);

        Text field6 = new Text();
        field6.setText("Petitioner First Name*");
        field6.setX(20);
        field6.setY(230);

        TextField petitionerFName = new TextField("First Name");
        petitionerFName.setLayoutX(20);
        petitionerFName.setLayoutY(235);

        Text field7 = new Text();
        field4.setText("Petitioner Last Name*");
        field4.setX(20);
        field4.setY(275);

        TextField petitionerLName = new TextField("Last Name");
        petitionerLName.setLayoutX(20);
        petitionerLName.setLayoutY(280);

        stage.setScene(scence);

        root.getChildren().add(topLine);
        root.getChildren().add(botLine1);
        root.getChildren().add(botLine2);
        root.getChildren().add(employeeID);
        root.getChildren().add(formID);
        root.getChildren().add(applicationDate);
        root.getChildren().add(scrollBar);
        root.getChildren().add(buttonBar);
        root.getChildren().add(field1);
        root.getChildren().add(relativeFName);
        root.getChildren().add(field2);
        root.getChildren().add(relativeLName);
        root.getChildren().add(field3);
        root.getChildren().add(alienNum);
        root.getChildren().add(field4);
        root.getChildren().add(birthDate);
        root.getChildren().add(field5);
        root.getChildren().add(country);
        root.getChildren().add(field6);
        root.getChildren().add(petitionerFName);
        root.getChildren().add(field7);
        root.getChildren().add(petitionerLName);

        stage.show();
    }

}
