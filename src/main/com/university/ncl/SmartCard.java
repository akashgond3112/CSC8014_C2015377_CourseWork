package main.com.university.ncl;

import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Objects;

public class SmartCard {

    final static String PERMANENT = "permanent";
    final static String FIXED = "fixed";
    private final String employmentStatus;
    private final Name name;
    private final SmartCardNumber smartCardNumber;
    private final Date dateOfBirth;
    private final Date dateOfIssue;
    private Date dateOfExpiry;

    public SmartCard(String employmentStatus, Name name, Date dob) {
        this.employmentStatus = employmentStatus;
        this.name = new Name(name.getFirstName(), name.getLastName());
        this.dateOfBirth = (Date) dob.clone();
        this.dateOfIssue = Calendar.getInstance().getTime();
        this.smartCardNumber = SmartCardNumber.getInstance(this.name.getFirstName(), this.name.getLastName(), this.dateOfIssue);
        setExpiryDate();
    }

    /**
     * @return which returns the expiry date of the card
     */
    public Date getExpiryDate() {
        return (Date) this.dateOfExpiry.clone();
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public Name getName() {
        return new Name(this.name.getFirstName(), this.name.getLastName());
    }

    public SmartCardNumber getSmartCardNumber() {
        return this.smartCardNumber;
    }

    public Date getDateOfBirth() {
        return (Date) this.dateOfBirth.clone();
    }

    public Date getDateOfIssue() {
        return (Date) this.dateOfIssue.clone();
    }

    /**
     * Method which sets an expiry date for the card. If the smart
     * card is held by a staff on fixed-term contract, the expiry date is set to the issue
     * date plus two years. If the smart card is held by a staff on permanent contract,
     * the expiry date is set to the issue date plus ten years.
     */
    private void setExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.dateOfIssue);

        if (this.employmentStatus.equals(FIXED)) {
            calendar.add(Calendar.YEAR, 2);
        } else if (this.employmentStatus.equals(PERMANENT)) {
            calendar.add(Calendar.YEAR, 10);
        } else {
            throw new InputMismatchException("Invalid employment status!");
        }
        this.dateOfExpiry = (Date) calendar.getTime().clone();
    }

}
