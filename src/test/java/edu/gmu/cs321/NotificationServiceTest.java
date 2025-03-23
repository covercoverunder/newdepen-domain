package edu.gmu.cs321;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Tester for NotificationService.
 * Examines variable validations and function outcomes.
 * @author Michael Toyco
 */
public class NotificationServiceTest {
    @Test
    public void testEmptyEmail() {
        NotificationService testNS = new NotificationService();
        assertTrue(testNS.getEmail() == null);
    }

    @Test
    public void testMaxCharacterLimit() {

    }
    @Test
    public void testSendNotification() {

    }
}