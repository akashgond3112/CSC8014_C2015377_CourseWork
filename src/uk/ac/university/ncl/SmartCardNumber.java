package uk.ac.university.ncl;

import java.util.Calendar;

public class SmartCardNumber {


    private String smartCardNumber;

    public String getSmartCardNumber() {
        return smartCardNumber;
    }

    public void setSmartCardNumber(String smartCardNumber) {
        this.smartCardNumber = smartCardNumber;
    }

    public SmartCardNumber(Name name) {
        setSmartCardNumber(generateSmartCardNumber(name));
    }

    private static String generateSmartCardNumber(Name name) {
        String firstInitial = name.getFirstName().substring(0, 1);
        String lastInitial = name.getLastName().substring(0, 1);
        int serialNumber = (int) (Math.random() * 100);
        return firstInitial + lastInitial + "-" + serialNumber + "-" + Calendar.getInstance().get(Calendar.YEAR);
    }


}
