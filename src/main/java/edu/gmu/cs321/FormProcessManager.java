package edu.gmu.cs321;

/**
 * Initiates process for each application.
 * @author Michael Toyco
 */
public class FormProcessManager {

    /* ------------------- attributes ------------------- */

    /**
     * Unique indentifier for each application currently being processed.
     * Max value is 15 characters.
     */
    private int processID;
    /**
     * Tracks the progress of application for approval.
     * Max value is 50 characters.
     */
    private String currentStep;

    /* ------------------- operators ------------------- */

    /**
     * boolean startProcess().
     * Determines applicant and petitioner
     * and assigns unique identifier to
     * application process.
     * @return If applicant and petitioner matches, it returns true. Otherwise, false.
     */
    public boolean startProcess() {
        return false;
    }
    /**
     * boolean routeToReview().
     * Once form is approved via entry,
     * the application is transferred for
     * further evaluation.
     * @return If no interruption occurs during data entry, it returns true. Otherwise, false.
     */
    public boolean routeToReview() {
        return false;
    }
   /**
     * boolean routeToApproval().
     * Once form passes the review phase,
     * It is transferred for approval.
     * @return If no interrupt occurs during review phase, it returns true. Otherwise, false.
     */
    public boolean routeToApproval() {
        return false;
    }
    /**
     * boolean triggerNotification().
     * Once application is approved.
     * The system will send a notification
     * to petitioner.
     * @return If email is valid, it returns true. Otherwise, false.
     */
    public boolean triggerNotifcation() {
        return false;
    }

}
