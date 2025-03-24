package edu.gmu.cs321;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Tester for ReviewHandler.
 * @author Michael Vongsomsack
 */
public class ReviewHandlerTest {

    /**
     * Reference of type ReviewHandler;
     */
    private ReviewHandler RH;

    /**
     * Creates a ReviewHandler object before each test.
     */
    @BeforeEach
    void setup() {
        RH = new ReviewHandler();
        assertNotNull(RH);
    }

    /**
     * Test if editing was successful;
     */
    @Test
    void editForm() {
        boolean success = RH.editForm();
        assertTrue(success);
    }

    /**
     * Test if sending the form to workflow was successful.
     */
    @Test
    void processForApproval() {
        boolean success = RH.processForApproval();
        assertTrue( success);
    }

}
