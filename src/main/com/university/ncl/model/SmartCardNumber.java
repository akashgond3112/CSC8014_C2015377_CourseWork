package main.com.university.ncl.model;

import java.util.*;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 322023
 * Copyright (C) 2023 Newcastle University, UK
 * StaffID -  A unique SmartCardNumber for every Smart card
 * This SmartCardNumber can only be instantiated by classes in
 * this same package. Use the StaffID.getInstance()
 * to get an instance.
 * package-private class definition
 * cannot be imported to other packages
 */
public final class SmartCardNumber {
    private static final Map<String, SmartCardNumber> SMART_CARD_NUMBER_MAP = new HashMap<String, SmartCardNumber>();
    private final String smartCardNumber;
    private final String nameInitial;
    private final int serialNumber;
    private final int currentYear;

    private SmartCardNumber(String smartCardNumber, String nameInitial, int serialNumber, int currentYear) {
        this.smartCardNumber = smartCardNumber;
        this.nameInitial = nameInitial;
        this.serialNumber = serialNumber;
        this.currentYear = currentYear;
    }

    /**
     * @return Return an SmartCardNumber.
     * @param firstName the type of account to return
     * @param lastName the account number
     * @param dateOfIssue the account number
     * An existing SmartCardNumber is there we will recursively call the method again and crete a unique SmartCardNumber.
     * a new SmartCardNumber with the given smartCardNumber is returned.
     * @throws NullPointerException if param is null
     */
    public static SmartCardNumber getInstance(String firstName, String lastName, Date dateOfIssue) {
        if (firstName == null && lastName == null && dateOfIssue == null)
            throw new InputMismatchException("Check the parameter inside the smart card number get instance method , cannot be null.");
        Calendar currentYear = Calendar.getInstance();
        currentYear.setTime(dateOfIssue);

        String initialOfFirstAndLastName = firstName.substring(0, 1) + lastName.substring(0, 1);
        String smtCardNo = initialOfFirstAndLastName + "-" + ((int) (Math.random() * 100)) + "-" + currentYear.get(Calendar.YEAR);

        SmartCardNumber smartCardNumber = SMART_CARD_NUMBER_MAP.get(smtCardNo);
        // check If we get the matching smartCardNumber generate the smart card number again by doing recursive call to avoid duplicates, until we get a unique smartCardNumber.
        if (smartCardNumber != null) {
            getInstance(firstName, lastName, dateOfIssue);
        } else {
            // create new StaffId object
            smartCardNumber = new SmartCardNumber(smtCardNo, initialOfFirstAndLastName, ((int) (Math.random() * 100)), currentYear.get(Calendar.YEAR));
            // put staff in staff map
            SMART_CARD_NUMBER_MAP.put(smtCardNo, smartCardNumber);
        }
        // return the instance
        return smartCardNumber;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return smartCardNumber;
    }
}
