package edu.gmu.cs321;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Tester for ApprovalHandler.
 * Examines method outcome related to final application approval check.
 * @author Michael Toyco
 */
public class ApprovalHandlerTest {

    /* ------------------- methods related-test ------------------- */

    /**
     * Tests if approveForm() returns true 
     * when document fails final validation check.
     */
    @Test
    public void testApproveForm() {
        // object declaration
        ApprovalHandler handler = new ApprovalHandler();
        // determine if function returns true
        assertEquals(true, handler.approveForm());
    }
}