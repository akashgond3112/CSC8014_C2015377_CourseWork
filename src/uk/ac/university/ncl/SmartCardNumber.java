package uk.ac.university.ncl;

import java.util.Calendar;
import java.util.Objects;

public class SmartCardNumber {


    private String smartCardNumber;

    public SmartCardNumber(Name name) {
        setSmartCardNumber(generateSmartCardNumber(name));
    }

    @Override
    public String toString() {
        return smartCardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SmartCardNumber that)) return false;
        return smartCardNumber.equals(that.smartCardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(smartCardNumber);
    }

    public void setSmartCardNumber(String smartCardNumber) {
        this.smartCardNumber = smartCardNumber;
    }

    private static String generateSmartCardNumber(Name name) {
        String firstInitial = name.getFirstName().substring(0, 1);
        String lastInitial = name.getLastName().substring(0, 1);
        int serialNumber = (int) (Math.random() * 100);
        return firstInitial + lastInitial + "-" + serialNumber + "-" + Calendar.getInstance().get(Calendar.YEAR);
    }


}
