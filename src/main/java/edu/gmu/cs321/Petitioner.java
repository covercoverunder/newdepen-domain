package edu.gmu.cs321;


public class Petitioner extends Person {
    private int aNum;

    public Petitioner() {
        this.aNum = 0;
    }

    public Petitioner(String fName, String lName, int dob, int aNum) {
        super(fName, lName, dob);
        this.aNum = aNum;
    }

    public int getANum() {
        return aNum;
    }
    
    public void setANum(int aNum) {
        this.aNum = aNum;
    }
}
