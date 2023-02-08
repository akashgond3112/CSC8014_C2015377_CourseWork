package uk.ac.university.ncl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public final class SmartCardNumber {
    private static final Map<String, SmartCardNumber> SMART_CARD_NUMBER_MAP = new HashMap<String, SmartCardNumber>();
    private final String smartCardNumber ,nameInitial;
    private final int serialNumber ,currentYear;


    private SmartCardNumber(String smartCardNumber, String nameInitial, int serialNumber, int currentYear) {
        this.smartCardNumber = smartCardNumber;
        this.nameInitial = nameInitial;
        this.serialNumber = serialNumber;
        this.currentYear = currentYear;
    }

    public static SmartCardNumber getInstance(Name name) {
        String nameInitial = name.getFirstName().substring(0, 1) + name.getLastName().substring(0, 1);
        int serialNumber = (int) (Math.random() * 100);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        String smtCardNo = nameInitial + "-" + serialNumber + "-" + currentYear;

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
