package edu.gmu.cs321;

public class Form {
    private int formID;
    private int applicationDate;

    public Form() {
        this.formID = 0;
        this.applicationDate = 0;
    }

    public Form(int formID, int applicationDate) {
        this.formID = formID;
        this.applicationDate = applicationDate;
    }

    public int getFormID() {
        return formID;
    }

    public int getApplicationDate() {
        return applicationDate;
    }
}
