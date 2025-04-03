package edu.gmu.cs321;


public class Person {
    private String firstName;
    private String lastName;
    private int dob;

    public Person() {
        this.firstName = null;
        this.lastName = null;
        this.dob = 0;
    }

    public Person(String firstName, String lastName, int dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDOB() {
        return dob;
    }
}
