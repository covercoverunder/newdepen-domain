package edu.gmu.cs321;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Tester for NewForm.
 * @author Michael Vongsomsack
 */
public class NewFormTest {

    /**
     * Reference of type NewForm.
     */
    private NewForm form1;
    //private NewForm form2;

    /**
     * Creates a default form object before each test.
     */
    @BeforeEach
    void setUp() {
        form1 = new NewForm();
        //form2 = new NewForm(123, 12252025, "1111 Green Street", "Greenville", "Florida", 22334, 010101, 030303);
    }

    /**
     * Test to see if a new form was produced.
     */
    @Test
    void createNewForm() {
        assertNotNull(form1);
    }

    /**
     * Test for errors caused by setting a new formID.
     */
    @Test
    void setFormID() {
        form1.setFormID(1234);
    }

    /**
     * Test expected output for formID.
     */
    @Test
    void getFormID() {
        int formID = form1.getFormID();
        assertEquals(123, formID);
    }

    /**
     * Test for errors caused by setting new applicationDate.
     */
    @Test
    void setApplicationDate() {
        form1.setApplicationDate(12252025);
    }

    /**
     * Test for expected output of applicationDate field.
     */
    @Test
    void getApplicationDate() {
        int applicationDate = form1.getApplicationDate();
        assertEquals(12252025, applicationDate);
    }

    /**
     * Test for errors caused by setting new address.
     */
    @Test
    void setAddress() {
        form1.setAddress("1111 Green Street");
    }

    /**
     * Test for expected output of address field;
     */
    @Test
    void getAddress() {
        String address = form1.getAddress();
        assertEquals("1111 Green Street", address);
    }

    /**
     * Test for errors caused by setting new city.
     */
    @Test
    void setCity() {
        form1.setCity("Greenville");
    }

    /**
     * Test for expected output of city field;
     */
    @Test
    void getCity() {
        String city = form1.getCity();
        assertEquals("Greenville", city);
    }

    /**
     * Test for errors caused by setting new state.
     */
    @Test
    void setState() {
        form1.setState("Florida");
    }

    /**
     * Test for expected output of state field.
     */
    @Test
    void getState() {
        String state = form1.getState();
        assertEquals("Florida", state);
    }

    /**
     * Test for errors caused by setting new zip code.
     */
    @Test
    void setZipCode() {
        form1.setZipCode(22334);
    }

    /**
     * Test for expected output on zipCode field.
     */
    @Test
    void getZipCode() {
        int zipCode = form1.getZipCode();
        assertEquals(22334, zipCode);
    }

    /**
     * Test for errors caused by setting new petitionerID.
     */
    @Test
    void setPetitionerID() {
        form1.setPetitionerID(010101);
    }

    /**
     * Test for expected output of petitionerID field.
     */
    @Test
    void getPetitionerID() {
        int petitionerID = form1.getPetitionerID();
        assertEquals(010101, petitionerID);
    }

    /**
     * Test for errors caused by setting new relativeID.
     */
    @Test
    void setRelativeID() {
        form1.setRelativeID(030303);
    }

    /**
     * Test for expected output of relativeID field.
     */
    @Test
    void getRelativeID() {
        int relativeID = form1.getRelativeID();
        assertEquals(030303, relativeID);
    }

}
