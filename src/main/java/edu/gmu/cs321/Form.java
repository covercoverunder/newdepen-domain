package edu.gmu.cs321;

public class Form {
  //instance variables

    /**
     * Form identification number.
     */
    private int formID;

    /**
     * Date of application.
     */
    private int applicationDate;

    /**
     * Petitioner's home address.
     */
    private String address;

    /**
     * The city the petitioner lives in.
     */
    private String city;

    /**
     * The state the petitioner lives in.
     */
    private String state;

    /**
     * THe petitioner's zip code.
     */
    private int zipcode;

    /**
     * The petitioner's identification number.
     */
    private int petitionerANum;

    /**
     * The relative's identification number.
     */
    private int relativeANum;

    public Form() {
        formID = 0;
        applicationDate = 0;
        address = "";
        city = "";
        state = "";
        zipcode = 0;
        relativeANum = 0;
        petitionerANum = 0;
    }

    public Form(int applicationDate, String address, String city, String state, int zipcode) {
        this.applicationDate = applicationDate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    //Non-static methods

    /**
     * Creates a new form.
     */
    public void createNewForm() {
        //Code here
    }

    /**
     * Set the form identification number.
     * @param formID
     */
    public void setFormID(int formID) {
        //code here
    }

    /**
     * Retrieve form identification number.
     * @return Form identification number
     */
    public int getFormID() {
        //code here
        return formID;
    }
    /**
     * Set the application date.
     * @param applicationDate Date of application
     */
    public void setApplicationDate(int applicationDate) {
        //code here
        this.applicationDate = applicationDate;
    }

    /**
     * Retrieve application date.
     * @return Application date
     */
    public int getApplicationDate() {
        //code here
        return applicationDate;
    }

    /**
     * Set address.
     * @param address Petitioner's address
     */
    public void setAddress(String address) {
        //code here
        this.address = address;
    }

    /**
     * Retrieve the petitioner's address.
     * @return Petitioner's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set city of residence.
     * @param city City of residence
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Retrieve petitioner's city of residence.
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set petitioner's state of residence
     * @param state Petitioner's state of residence
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Retrieve petitioner's state of residence
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * Set petitioner's zip code.
     * @param zipcode petitioner's zip code
     */
    public void setZipCode(int zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Retrive petitioner's zip code.
     * @return zip code
     */
    public int getZipCode() {
        return zipcode;
    }

    /**
     * Set petitioner identification number.
     * @param PetitionerANum Petitioner ID number
     */
    public void setPetitionerANum(int petitionerANum) {
        this.petitionerANum = petitionerANum;
    }

    /**
     * Retrieve petitioner's identification number.
     * @return Petitioner ID number
     */
    public int getPetitionerANum() {
        return petitionerANum;
    }

    /**
     * Set relative identification number.
     * @param relativeID Relative ID number
     */
    public void setRelativeANum(int relativeANum) {
        this.relativeANum = relativeANum;
    }

    /**
     * Retrieve relative identification number.
     * @return Relative ID number
     */
    public int getRelativeANum() {
        return relativeANum;
    }
}
