package edu.gmu.cs321;

/**
 * Creates a new form with the information provided by data entry.
 * @author Michael Vongsomsack
 */
public class NewForm extends Form{

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
    private int zipCode;

    /**
     * The petitioner's identification number.
     */
    private int petitionerID;

    /**
     * The relative's identification number.
     */
    private int relativeID;

    /**
     * Default constructor that creates a NewForm object.
     */
    public NewForm() {
        formID = 0;
        applicationDate = 0;
        address = "";
        city = "";
        state = "";
        zipCode = 0;
        petitionerID = 0;
        relativeID = 0;
    }

    /**
     * Overloaded constructor that creates a NewForm object given required information.
     * @param formID Form identification number
     * @param applicationDate Date of application
     * @param address Petitioner's address
     * @param city City the petitioner resides in
     * @param state State the petitioner is resident of
     * @param zipCode Petitioner's area zipCode
     * @param PetitionerID Petitioner identification number
     * @param relativeID Relative identification number
     */
    public NewForm(int formID, int applicationDate, String address, String city, String state, int zipCode, int petitionerID, int relativeID) {
        this.formID = formID;
        this.applicationDate = applicationDate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.petitionerID = petitionerID;
        this.relativeID = relativeID;
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
        return -1;
    }
    /**
     * Set the application date.
     * @param applicationDate Date of application
     */
    public void setApplicationDate(int applicationDate) {
        //code here
    }

    /**
     * Retrieve application date.
     * @return Application date
     */
    public int getApplicationDate() {
        //code here
        return -1;
    }

    /**
     * Set address.
     * @param address Petitioner's address
     */
    public void setAddress(String address) {
        //code here
    }

    /**
     * Retrieve the petitioner's address.
     * @return Petitioner's address
     */
    public String getAddress() {
        //code here
        return null;
    }

    /**
     * Set city of residence.
     * @param city City of residence
     */
    public void setCity(String city) {
        //code here
    }

    /**
     * Retrieve petitioner's city of residence.
     * @return city
     */
    public String getCity() {
        //code here
        return null;
    }

    /**
     * Set petitioner's state of residence
     * @param state Petitioner's state of residence
     */
    public void setState(String state) {
        //code here
    }

    /**
     * Retrieve petitioner's state of residence
     * @return state
     */
    public String getState() {
        //code here
        return null;
    }

    /**
     * Set petitioner's zip code.
     * @param zipCode petitioner's zip code
     */
    public void setZipCode(int zipCode) {
        //code here
    }

    /**
     * Retrive petitioner's zip code.
     * @return zip code
     */
    public int getZipCode() {
        //code here
        return -1;
    }

    /**
     * Set petitioner identification number.
     * @param petitionerID Petitioner ID number
     */
    public void setPetitionerID(int petitionerID) {
        //code here
    }

    /**
     * Retrieve petitioner's identification number.
     * @return Petitioner ID number
     */
    public int getPetitionerID() {
        //code here
        return -1;
    }

    /**
     * Set relative identification number.
     * @param relativeID Relative ID number
     */
    public void setRelativeID(int relativeID) {
        //code here
    }

    /**
     * Retrieve relative identification number.
     * @return Relative ID number
     */
    public int getRelativeID() {
        //code here
        return -1;
    }

}
