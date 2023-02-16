package main.com.university.ncl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        nameInitial = firstName.substring(0, 1) + lastName.substring(0, 1);
        serialNumber = (int) (Math.random() * 100);
        currentYear.setTime(dateOfIssue);

        String smtCardNo = nameInitial + "-" + serialNumber + "-" + currentYear.get(Calendar.YEAR);

        SmartCardNumber smartCardNumber = SMART_CARD_NUMBER_MAP.get(smtCardNo);
        if (smartCardNumber == null) {
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
