package edu.gmu.cs321;
import com.cs321.Workflow;

public class WorkflowTest {
    public static void main(String[] args) {
        Workflow workflow = null;
        try {
            // Initialize the Workflow component
            workflow = new Workflow();

            // Add a workflow item
            // Returns: 0 on success, -1 for invalid NextStep, -2 for existing/invalid FormID
            int addResult = workflow.AddWFItem(101, "Review");

            // Get the next workflow item
            // Returns: FormID on success, -1 for invalid NextStep, -3 if no items available
            int nextItem = workflow.GetNextWFItem("Review");

            if (nextItem > 0) {
                System.out.println("Next item for processing: " + nextItem);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Always close the connection when done
            if (workflow != null) {
                workflow.closeConnection();
            }
        }
    }
}
