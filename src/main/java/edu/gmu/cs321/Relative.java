package edu.gmu.cs321;

public class Relative extends Person {
    private int aNumRel;

    public Relative() {
        this.aNumRel = 0;
    }

    public Relative(String fName, String lName, int dob, int aNumRel) {
        super(fName, lName, dob);
        this.aNumRel = aNumRel;
    }

    public int getANumRel() {
        return aNumRel;
    }
}
