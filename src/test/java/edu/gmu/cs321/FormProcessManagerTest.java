package edu.gmu.cs321;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Tester for FormProcessManager.
 * Examines method outcomes related to form processing and notification.
 * @author Michael Toyco
 */
public class FormProcessManagerTest {

    /* ------------------- methods related tests ------------------- */

    /**
     * Tests if startProcess() returns true 
     * when applicant and petitioner are not equal.
     */
    @Test
    public void testStartProcess() {
        // object declaration
        FormProcessManager fpm = new FormProcessManager();
        // determine if function returns true
        assertEquals(true, fpm.startProcess());
    }
    /**
     * Tests if routeToReview() returns true 
     * when form cannot be sent to review phase.
     */
    @Test
    public void testRouteToReview() {
        // object declaration
        FormProcessManager fpm = new FormProcessManager();
        // determine if function returns true
        assertEquals(true, fpm.routeToReview());
    }
    /**
     * Tests if routeToApproval() returns true 
     * when form cannot be sent to approval phase.
     */
    @Test
    public void testRouteToApproval() {
        // object declaration
        FormProcessManager fpm = new FormProcessManager();
        // determine if function returns true
        assertEquals(true, fpm.routeToApproval());
    }
    /**
     * Tests if triggerNotification() returns true 
     * when email is invalid or not processed.
     */
    @Test
    public void testTriggerNotification() {
        // object declaration
        FormProcessManager fpm = new FormProcessManager();
        // determine if function returns true
        assertEquals(true, fpm.triggerNotification());
    }
}