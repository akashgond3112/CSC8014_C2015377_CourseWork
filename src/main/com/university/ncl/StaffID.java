package main.com.university.ncl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class StaffID {
    private static final Map<String, StaffID> STAFF_ID = new HashMap<String, StaffID>();
    private final char prefix;
    private final int suffix;
    private final String staffId;


    public String getStaffId() {
        return staffId;
    }

    public char getPrefix() {
        return prefix;
    }

    public int getSuffix() {
        return suffix;
    }


    @Override
    public String toString() {
        return staffId;
    }

    private StaffID(char prefix, int suffix, String staffId) {
        this.prefix=prefix;
        this.suffix=suffix;
        this.staffId=staffId;
    }


    /*
     This code generates a random number between 97 and 122 (corresponding to the ASCII values for the letters 'a' to 'z') using the Random class,
     and then converts that number to a letter by casting it to a char. A second random number between 1 and 999 is generated and formatted with leading zeros.
     The resulting letter and number are concatenated to produce the final output.
     */
    public static StaffID getInstance () {

        final Random rand = new Random();
        final int letterInt = rand.nextInt(26) + 97; // generate a random number between 97 and 122 (corresponding to ASCII values for 'a' to 'z')
        final char letter = (char) letterInt; // convert the integer to a letter
        final int number = rand.nextInt(999) + 1; // generate a random number between 1 and 999
        String staffId =  letter + String.format("%03d", number); // format the number with leading zeros

        StaffID stfID = STAFF_ID.get(staffId);
        if (stfID==null) {
            stfID= new StaffID(letter, number, staffId);
            STAFF_ID.put(staffId, stfID);
        }
        return stfID;
    }

}
