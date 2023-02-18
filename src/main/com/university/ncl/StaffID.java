package main.com.university.ncl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 322023
 * Copyright (C) 2023 Newcastle University, UK
 * StaffID -  A StaffID for every staff
 * This StaffID can only be instantiated by classes in
 * this same package. Use the StaffID.getInstance()
 * to get an instance.
 * package-private class definition
 * cannot be imported to other packages
 */
public final class StaffID {
    private static final Map<String, StaffID> STAFF_ID = new HashMap<String, StaffID>();
    private final char prefix;
    private final int suffix;
    private final String staffId;

    private StaffID(char prefix, int suffix, String staffId) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.staffId = staffId;
    }

    /**
     * @return an StaffID Object.
     * If an existing StaffID is there we will recursively call the method again and crete a unique StaffID.
     * a new StaffID with the given StaffID is returned.
     * This code generates a random number between 97 and 122 (corresponding to the ASCII values for the letters 'a' to 'z') using the Random class,
     * and then converts that number to a letter by casting it to a char. A second random number between 1 and 999 is generated and formatted with leading zeros.
     * The resulting letter and number are concatenated to produce the final output.
     */
    public static StaffID getInstance() {

        final Random rand = new Random();
        final int letterInt = rand.nextInt(26) + 97; // generate a random number between 97 and 122 (corresponding to ASCII values for 'a' to 'z')
        final char letter = (char) letterInt; // convert the integer to a letter
        final int number = rand.nextInt(999) + 100; // generate a random number between 100 and 999
        final String staffId = letter + String.format("%03d", number); // format the number with leading zeros

        StaffID stfID = STAFF_ID.get(staffId);
        // check If we get the matching staff ID generate the staff ID again by making a recursive call to generate ID again and avoid duplicates, until we get a unique staffID
        if (stfID != null) {
            getInstance();
        } else {
            // create new StaffId object
            stfID = new StaffID(letter, number, staffId);
            // put staff in staff map
            STAFF_ID.put(staffId, stfID);
        }
        // return the instance
        return stfID;
    }

    // utilities getter method
    public String getStaffId() {
        return staffId;
    }

    public char getPrefix() {
        return prefix;
    }

    public int getSuffix() {
        return suffix;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return staffId;
    }

}
