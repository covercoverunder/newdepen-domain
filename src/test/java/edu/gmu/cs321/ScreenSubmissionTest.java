package edu.gmu.cs321;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Tester for ScreenSubmission.
 * @author Michael Vongsomsack
 */
public class ScreenSubmissionTest {

    /**
     * Reference of type ScreenSubmission;
     */
    private ScreenSubmission ss;

    /**
     * Creates a default ScreenSubmission object before each test.
     */
    @BeforeEach
    void setup() {
        ss = new ScreenSubmission();
        assertNotNull(ss);
    }

    /**
     * Test if the form was successfully submitted.
     */
    @Test
    void submitForm() {
        boolean success = ss.submitForm();
        assertTrue(success);
    }

}
