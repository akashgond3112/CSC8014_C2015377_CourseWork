package main.com.university.ncl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 03022023
 * Copyright (C) 2023 Newcastle University, UK
 */
public abstract class AbstractStaff implements Staff {

    public static final String LECTURER = "Lecturer";
    public static final String RESEARCHER = "Researcher";

    // map of account number to instantiated account
    // note static, so per-class map
    private static final Map<StaffID, Staff> staffMap = new HashMap<StaffID, Staff>();

    private final String contractType;
    private SmartCard smartCard;
    private final StaffID staffID;
    private final Name name;

    AbstractStaff(Name name, String contractType, StaffID staffID) {
        this.name = name;
        this.contractType = contractType;
        this.staffID = staffID;
    }

    public static Staff getInstance(String staffType, Name name, String contractType, StaffID staffID) {

        Staff staff = staffMap.get(staffID);

        if (staff != null) return staff;

        if (staffType.equals(LECTURER)) {
            staff = new Lecturer(name, contractType, staffID);
        } else if (staffType.equals(RESEARCHER)) {
            staff = new Researcher(name, contractType, staffID);
        } else {
            throw new IllegalArgumentException("Invalid staff type!");
        }

        staffMap.put(staffID, staff);

        return staff;
    }

    public String getContractType() {
        return contractType;
    }

    /**
     * Returns the Name.
     * All staff must have Name
     *
     * @return the Name object
     */
    public Name getName() {
        return this.name;
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

    public void setSmartCard(SmartCard smartCard) {
        this.smartCard = smartCard;
    }
}
