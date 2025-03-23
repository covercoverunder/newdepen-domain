package edu.gmu.cs321;

/**
 * Sends notification to petitioner once application is approved.
 * @author Michael Toyco
 */
public class NotificationService {
    
    
    /* ------------------- attributes ------------------- */

    /**
     * Holds email information of petitioner.
     * Must be less than or equal to 50 characters.
     */
    private String email;

    /* ------------------- operators ------------------- */

    /**
     * Zero-param constructor.
     * Assigns empty values.
     */
    public NotificationService() {
        email = null;
    }
    /*
     * One-param constructor.
     * Assigns predefined email.
     * @param email email information of petitioner.
     */
    public NotificationService(String email) {
        // assign predefined email
        this.email = email;
    }
     /**
     * boolean sentNotification().
     * Sends an a notification to petitioner.
     * Informs that application is approved.
     * @return True, if application is approved. Otherwise false.
     */
    public boolean sendNotification() {
        return false;
    }
     /**
     * String getEmail().
     * Returns email of petitioner.
     * @return If not empty, returns email of petitioner. Otherwise, returns null.
     */
    public String getEmail() {
        return email;
    }
}
