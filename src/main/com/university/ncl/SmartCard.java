package main.com.university.ncl;

import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;


/**
 * @author akash.gond
 * @Project Learning
 * @Date 03022023
 * Copyright (C) 2023 Newcastle University, UK
 * ImmutableBook - immutable version of SmartCard that prevents
 * modification of internal state through references
 * to private member fields.
 */
public final class SmartCard {

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
        this.dateOfBirth = new Date(dob.getTime());
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

    /**
     * @return which returns the employment status of the staff
     */
    public String getEmploymentStatus() {
        return employmentStatus;
    }

    /**
     * @return which Name of the staff
     * @see main.com.university.ncl.Name
     */
    public Name getName() {
        return new Name(this.name.getFirstName(), this.name.getLastName());
    }

    /**
     * @return which SmartCardNumber of the staff
     * @see main.com.university.ncl.SmartCardNumber
     */
    public SmartCardNumber getSmartCardNumber() {
        return smartCardNumber;
    }

    /**
     * @return , Date of birth for the staff
     */
    public Date getDateOfBirth() {
        return (Date) this.dateOfBirth.clone();
    }

    /**
     * @return , Date of issue of the smart card
     */
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
