package main.com.university.ncl;

import java.time.LocalDate;
import java.util.Date;

public class SmartCard {

    private String employmentStatus;
    private Name name;
    private SmartCardNumber smartCardNumber;
    private Date dob;
    private LocalDate doi;
    private LocalDate doe;

    public SmartCard(String employmentStatus, Name name, SmartCardNumber smartCardNumber, Date dob) {
        this.employmentStatus = employmentStatus;
        this.name = name;
        this.dob = dob;
        this.smartCardNumber = smartCardNumber;
        this.doi = LocalDate.now();
    }

    public SmartCard() {
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public SmartCardNumber getSmartCardNumber() {
        return smartCardNumber;
    }

    public void setSmartCardNumber(SmartCardNumber smartCardNumber) {
        this.smartCardNumber = smartCardNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public LocalDate getDoi() {
        return doi;
    }

    public void setDoi(LocalDate doi) {
        this.doi = doi;
    }


    public LocalDate getExpiryDate() {
        return doe;
    }

    private void setExpiryDate() {
        if (this.employmentStatus.equals("fixed")) {
            this.doe = doi.plusYears(2);
        } else if (this.employmentStatus.equals("permanent")) {
            this.doe = doi.plusYears(10);
        } else {
            System.out.println("dd");
        }
    }

}
