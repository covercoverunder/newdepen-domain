package edu.gmu.cs321;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.StringBuilder;

/*
 * Tester for NotificationService.
 * Examines variable validations and function outcomes.
 * @author Michael Toyco
 */
public class NotificationServiceTest {

    /* ------------------- constructor related-test ------------------- */

    /**
     * Tests if email value is null when
     * no String is passed within the
     * constructor.
     */
    @Test
    public void testEmptyEmail() {
        // object declaration
        NotificationService ns = new NotificationService();
        // determine if email is null
        assertEquals(null, ns.getEmail());
    }
    /**
     * Tests if constructor throws IllegalArgumentException
     * when invalid number of char is stored to email.
     */
    @Test
    public void testInvalidEmailLength() {
        // var for email
        StringBuilder email = new StringBuilder("a");
        // while loop to assign chars above 50
        while(email.length() <= 50) {
            email.append("a");
        }
        // test IAE
        try {
            // run constructor with invalid email
            new NotificationService(email.toString());
            // alert if unexpected condition occurs
            fail("Expected IllegalArgumentException");
        // validation passes if IAE is caught
        } catch (IllegalArgumentException e) {
            assertEquals("Email too long", e.getMessage());
        }
    }
    /**
     * Tests if constructor assigns email successfully
     * if valid values are passed.
     */
    @Test
    public void testValidEmailLength() {
        // var for email
        String email = "test@gmail.com";
        // object declaration
        NotificationService ns = new NotificationService(email);
        // determine if email value is equal
        assertEquals(email, ns.getEmail());
    }

    /* ------------------- methods related-test ------------------- */

    /**
     * Tests if sendNotification() is successful
     * when constructor contains valid email.
     */
    @Test
    public void testSendNotification() {
        // object declaration with predefined email
        NotificationService ns = new NotificationService("test@gmail.com");
        // determine if function returns true
        assertEquals(true, ns.sendNotification());
    }
}