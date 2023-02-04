package uk.ac.university.ncl;

import java.util.HashSet;

public class StaffID {


    private char letter;
    private int number;
    private static HashSet<String> existingIDs = new HashSet<>();

    public StaffID( ) {
    }

    public StaffID(char letter, int number) {
        this.letter = letter;
        this.number = number;

        String id = letter + Integer.toString(number);
        if (existingIDs.contains(id)) {
            throw new IllegalArgumentException("Staff ID already exists: " + id);
        }
        existingIDs.add(id);
    }

    public char getLetter() {
        return letter;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return letter + Integer.toString(number);
    }

    /*
     * @param staffId
     * This method uses the length() method of the String class to check if the
     * string is 4 characters long, and then checks the first character using
     * Character.isLetter(char) to see if it's a letter. Finally, it uses a loop to
     * check the next three characters using Character.isDigit(char) to see if they
     * are digits. If all of these checks pass, the method returns true.
     */

    private static boolean validate(String staffId) {
        if (staffId.length() != 4) {
            return false;
        }
        char first = staffId.charAt(0);
        if (!Character.isLetter(first)) {
            return false;
        }
        for (int i = 1; i < 4; i++) {
            if (!Character.isDigit(staffId.charAt(i))) {
                return false;
            }
        }
        return true;
    }


}
