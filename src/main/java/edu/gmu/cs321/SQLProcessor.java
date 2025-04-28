// modified version of "TestDatabase" from previous classes.
// utitlized some aspect of "Workflow.jar" (decompiled Workflow.class)


package edu.gmu.cs321;

import java.sql.*;
import java.util.HashMap;

public class SQLProcessor {

    // keep track of duplicate petitioners
    static HashMap<Integer, Integer> petTracker = new HashMap<>();

    private static final String URL = "jdbc:mysql://localhost:3306/cs321";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567890"; // replace with your MySQL password

    private static Connection connection;

    // Get database connection
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Insert a petitioner using Petitioner object
    public static void createPetitioner(Petitioner petitioner) {
        String insertQuery = "INSERT INTO Petitioner (first, last, dob, aNum) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setString(1, petitioner.getFirstName());
            stmt.setString(2, petitioner.getLastName());
            stmt.setInt(3, petitioner.getDOB());
            stmt.setInt(4, petitioner.getANum());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Petitioner added successfully.");
                petTracker.put(petitioner.getANum(), petitioner.getANum());
            } else {
                System.out.println("Error adding petitioner.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert a relative using Relative object
    public static void createRelative(Relative relative) {
        String insertQuery = "INSERT INTO Relative (first, last, dob, aNumRel) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setString(1, relative.getFirstName());
            stmt.setString(2, relative.getLastName());
            stmt.setInt(3, relative.getDOB());
            stmt.setInt(4, relative.getANumRel());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Relative added successfully.");
            } else {
                System.out.println("Error adding relative.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert a form using Form object
    public static void createForm(Form form) {
        String insertQuery = "INSERT INTO Form (date, address, city, state, zip, aNumPet, aNumRel, status, rejectionNote) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
    
            stmt.setInt(1, form.getApplicationDate());
            stmt.setString(2, form.getAddress());
            stmt.setString(3, form.getCity());
            stmt.setString(4, form.getState());
            stmt.setInt(5, form.getZipCode());
            stmt.setInt(6, form.getPetitionerANum());
            stmt.setInt(7, form.getRelativeANum());
            stmt.setString(8, form.getStatus());
            stmt.setString(9, form.getRejectionReason());
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Form added successfully.");
            } else {
                System.out.println("Error adding form.");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a Form by its ID
    public static Form retrieveForm(int formId) {
        String selectQuery = "SELECT * FROM Form WHERE id = ?";

        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery)) {

            stmt.setInt(1, formId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Create Form object manually
                Form form = new Form();
                form.setApplicationDate(rs.getInt("date"));
                form.setAddress(rs.getString("address"));
                form.setCity(rs.getString("city"));
                form.setState(rs.getString("state"));
                form.setZipCode(rs.getInt("zip"));
                form.setPetitionerANum(rs.getInt("aNumPet"));
                form.setRelativeANum(rs.getInt("aNumRel"));
                form.setStatus(rs.getString("status"));
                form.setRejectionReason(rs.getString("rejectionNote"));
                return form;
            } else {
                System.out.println("Form with ID " + formId + " not found.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieve a petitioner using Alien Number
    public static Petitioner retrievePetitioner(int aNum) {
        String selectQuery = "SELECT * FROM Petitioner WHERE aNum = ?";
        // Attempt to connect to MySQL Server
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery)) {

            stmt.setInt(1, aNum);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Create Petitioner object manually
                Petitioner pet = new Petitioner();
                pet.setANum(rs.getInt("aNum"));
                pet.setFirstName(rs.getString("first"));
                pet.setLastName(rs.getString("last"));
                pet.setDOB(rs.getInt("dob"));
                return pet;
            } else {
                System.out.println("Pet with Alien Num " + aNum + " not found.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieve a Relative using Alien Number
    public static Relative retrieveRelative(int aNum) {
        String selectQuery = "SELECT * FROM Relative WHERE aNumRel = ?";
        // Attempt to connect to MySQL Server
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery)) {

            stmt.setInt(1, aNum);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Create Relative object manually
                Relative rel = new Relative();
                rel.setANumRel(rs.getInt("aNumRel"));
                rel.setFirstName(rs.getString("first"));
                rel.setLastName(rs.getString("last"));
                rel.setDOB(rs.getInt("dob"));
                return rel;
            } else {
                System.out.println("Pet with Alien Num " + aNum + " not found.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Returns the ID of the first form that matches the given status
    // use string keys:
    // "toReview" -> forms ready for reviewer
    // "toApprove" -> forms ready for approver
    // "rejected" -> forms rejected by approver
    // "approved" -> formed approved by approver
    public static int availableForm(String status) {
        String selectQuery = "SELECT id FROM Form WHERE status = ? LIMIT 1";

        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery)) {

            stmt.setString(1, status);

            ResultSet rs = stmt.executeQuery();
            // if it matches
            if (rs.next()) {
                // return form if
                return rs.getInt("id");
            } else {
                // otherwise, not found
                return -1; // No matching form found
            }

        } catch (SQLException e) {
            // if other issues occurs, return as not found
            e.printStackTrace();
            return -1; 
        }
    }

    // edit already existing form and locating specific form via its ID
    public static void modifyForm(int id, Form form) {
        String updateQuery = "UPDATE Form SET date = ?, address = ?, city = ?, state = ?, zip = ?, aNumPet = ?, aNumRel = ?, status = ?, rejectionNote = ? WHERE id = ?";

        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(updateQuery)) { 

                stmt.setInt(1, form.getApplicationDate());
                stmt.setString(2, form.getAddress());
                stmt.setString(3, form.getCity());
                stmt.setString(4, form.getState());
                stmt.setInt(5, form.getZipCode());
                stmt.setInt(6, form.getPetitionerANum());
                stmt.setInt(7, form.getRelativeANum());
                stmt.setString(8, form.getStatus());
                stmt.setString(9, form.getRejectionReason());
                stmt.setInt(10, id);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Form edit success.");
                } else {
                    System.out.println("Error occurred with form.");
                }
        
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    // returns if petitioner id is duplicate
    public static boolean moreThanOnePetNum(int petANum) {
        String existQuery = "SELECT aNum FROM Petitioner WHERE aNum = ?";
        // var to count num or iterations
        int count = 0;

        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(existQuery)) {

            stmt.setInt(1, petANum);

            // execute query/command
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                count++;
            }
            // if greater than one
            if (count > 1) {
                // true
                return true;
            // otherwise, return false
            } else {
                return false;
            }
            // return false if error occurred
            } catch (SQLException e) {
                // not found
                e.printStackTrace();
                return false;
            }
    }

    // return form's rejection message (if exists)
    public static String formRejectionNote(int id) {
        String selectQuery = "SELECT rejectionNote FROM Form WHERE id = ?";
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery)) { 
            stmt.setInt(1, id);
            // exec query
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                // return rejection note
                return rs.getString("rejectionNote");
            } else {
                // if not found, return null
                System.out.println("ID " + id + "not found.");
                return null;
            }
            // if other errors occurs, return null
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }
    public static void modifyPetitioner(Petitioner petitioner) {
        String updateQuery = "UPDATE Petitioner SET first = ?, last = ?, dob = ? WHERE aNum = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setString(1, petitioner.getFirstName());
            stmt.setString(2, petitioner.getLastName());
            stmt.setInt(3, petitioner.getDOB());
            stmt.setInt(4, petitioner.getANum());
            stmt.executeUpdate();
            System.out.println("Petitioner updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifyRelative(Relative relative) {
        String updateQuery = "UPDATE Relative SET first = ?, last = ?, dob = ? WHERE aNumRel = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setString(1, relative.getFirstName());
            stmt.setString(2, relative.getLastName());
            stmt.setInt(3, relative.getDOB());
            stmt.setInt(4, relative.getANumRel());
            stmt.executeUpdate();
            System.out.println("Relative updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* 
    public static void main(String[] args) {
        // Example objects from PetitionerFill
        Relative rel = new Relative("John", "Doe", 20010909, 111);
        Petitioner pet = new Petitioner("Frank", "Furt", 20021010, 222);
        Form form = new Form(20250222, "Somewhere St.", "Fairfax", "VA", 22030);

        // Insert into database
        createPetitioner(pet);
        createRelative(rel);
        createForm(form);
    }

    */
}