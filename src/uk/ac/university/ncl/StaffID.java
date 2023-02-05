package uk.ac.university.ncl;

import java.util.Random;

public class StaffID {

    private String staffId;

    @Override
    public String toString() {
        return staffId;
    }

    public StaffID() {
        this.generateStaffId();
    }


    /*
     This code generates a random number between 97 and 122 (corresponding to the ASCII values for the letters 'a' to 'z') using the Random class,
     and then converts that number to a letter by casting it to a char. A second random number between 1 and 999 is generated and formatted with leading zeros.
     The resulting letter and number are concatenated to produce the final output.
     */

    private void generateStaffId() {
        Random rand = new Random();
        int letterInt = rand.nextInt(26) + 97; // generate a random number between 97 and 122 (corresponding to ASCII values for 'a' to 'z')
        char letter = (char) letterInt; // convert the integer to a letter
        int number = rand.nextInt(999) + 1; // generate a random number between 1 and 999
        this.staffId = letter + String.format("%03d", number); // format the number with leading zeros

    }


}
