package main.com.university.ncl;

import java.util.*;

public final class SmartCardNumber {
    private static final Map<String, SmartCardNumber> SMART_CARD_NUMBER_MAP = new HashMap<String, SmartCardNumber>();
    private final String smartCardNumber;
    private static String nameInitial = null;
    private static int serialNumber = 0;
    private static Calendar currentYear = Calendar.getInstance();

    private SmartCardNumber(String smartCardNumber, String nameInitial, int serialNumber, Calendar currentYear) {
        this.smartCardNumber = smartCardNumber;
        SmartCardNumber.nameInitial = nameInitial;
        SmartCardNumber.serialNumber = serialNumber;
        SmartCardNumber.currentYear = currentYear;
    }

    public static SmartCardNumber getInstance(String firstName, String lastName, Date dateOfIssue) {
        if (firstName == null && lastName == null && dateOfIssue == null)
            throw new InputMismatchException("Check the parameter inside the smart card number get instance method , cannot be null.");
        nameInitial = firstName.substring(0, 1) + lastName.substring(0, 1);
        serialNumber = (int) (Math.random() * 100);
        currentYear.setTime(dateOfIssue);

        String smtCardNo = nameInitial + "-" + serialNumber + "-" + currentYear.get(Calendar.YEAR);

        SmartCardNumber smartCardNumber = SMART_CARD_NUMBER_MAP.get(smtCardNo);
        if (smartCardNumber != null) {
            // If we get the matching smartCardNumber generate the smart card number again by doing recursive call to avoid duplicates, until we get a unique smartCardNumber.
            getInstance(firstName, lastName, dateOfIssue);
        } else {
            smartCardNumber = new SmartCardNumber(smtCardNo, nameInitial, serialNumber, currentYear);
            SMART_CARD_NUMBER_MAP.put(smtCardNo, smartCardNumber);
        }
        return smartCardNumber;
    }

    @Override
    public String toString() {
        return smartCardNumber;
    }
}
