package edu.gmu.cs321;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Tester for EntriesHandler.
 * Examines methods related to form validation.
 * @author Michael Toyco
 */
public class EntriesHandlerTest {

    /* ------------------- methods related-test ------------------- */

    /**
     * Tests if processForReviewi() returns true 
     * when application has invalid or missing fields.
     */
    @Test
    public void testProcessForReview() {
        // object declaration
        EntriesHandler handler = new EntriesHandler();
        // determine if function returns true
        assertEquals(true, handler.processForReview());
    }
}