package edu.gmu.cs321;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Tester for FormValidation.
 * @author Michael Vongsomsack
 */
public class FormValidationTest {

    /**
     * Reference of type FormValidation.
     */
    private FormValidation FV;

    /**
     * Creates a FormValidation object before each test.
     */
    @BeforeEach
    void setup() {
        FV = new FormValidation();
        assertNotNull(FV);
    }

    /**
     * Test whether the form contains errors or is clear of them.
     */
    @Test
    void validate() {
        boolean success = FV.validate();
        assertTrue(success);
    }
}
