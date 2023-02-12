package main.com.university.ncl;

import java.util.Calendar;
import java.util.Date;

public class SmartCard {

    final static String PERMANENT = "permanent";
    final static String FIXED = "fixed";
    private final String employmentStatus;
    private final Name name;
    private final SmartCardNumber smartCardNumber;
    private final Date dateOfBirth;
    private final Date dateOfIssue;
    private Date dateOfExpiry;
    final Calendar calendar = Calendar.getInstance();

    public SmartCard(String employmentStatus, Name name, Date dob) {
        this.employmentStatus = employmentStatus;
        this.name = name;
        this.dateOfBirth = dob;
        this.dateOfIssue = calendar.getTime();
        this.smartCardNumber = SmartCardNumber.getInstance(this.name.getFirstName(), this.name.getLastName(), this.dateOfIssue);
        setExpiryDate();
    }

    /**
     * @return which returns the expiry date of the card
     */
    public Date getExpiryDate() {
        return dateOfExpiry;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public String getName() {
        return this.name.toString();
    }

    public SmartCardNumber getSmartCardNumber() {
        return smartCardNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    /**
     * Method which sets an expiry date for the card. If the smart
     * card is held by a staff on fixed-term contract, the expiry date is set to the issue
     * date plus two years. If the smart card is held by a staff on permanent contract,
     * the expiry date is set to the issue date plus ten years.
     */
    private void setExpiryDate() {
        if (this.employmentStatus.equals(FIXED)) {
            calendar.add(Calendar.YEAR, 2);
        } else if (this.employmentStatus.equals(PERMANENT)) {
            calendar.add(Calendar.YEAR, 10);
        } else {
            System.out.println("dd");
        }
        this.dateOfExpiry = calendar.getTime();
    }

}
