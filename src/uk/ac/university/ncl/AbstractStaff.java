package uk.ac.university.ncl;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 03022023
 * Copyright (C) 2023 Newcastle University, UK
 */
public abstract class AbstractStaff implements Staff {


    String contractType;
    SmartCard smartCard;
    StaffID staffID;
    Name name;

    public AbstractStaff(Name name, String contractType, SmartCard smartCard, StaffID staffID) {
        this.name=name;
        this.contractType = contractType;
        this.smartCard = smartCard;
        this.staffID = staffID;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public void setSmartCard(SmartCard smartCard) {
        this.smartCard = smartCard;
    }

    public void setStaffID(StaffID staffID) {
        this.staffID = staffID;
    }

    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Returns the Name.
     * All staff must have Name
     *
     * @return the Name object
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns the staff ID.
     * All staff must have an ID
     *
     * @return the StaffID object
     */
    public StaffID getStaffID() {
        return this.staffID;
    }

    /**
     * Returns the smart card.
     * All staff must have a smart card
     *
     * @return the SmartCard object
     */
    public SmartCard getSmartCard() {
        return this.smartCard;
    }

    /**
     * Returns the Staff employment status.
     * a Staff can be either on Permanent or fixed contract
     *
     * @return a string (Permanent or fixed)
     */
    public String getStaffEmploymentStatus() {
        return this.contractType;
    }
}
