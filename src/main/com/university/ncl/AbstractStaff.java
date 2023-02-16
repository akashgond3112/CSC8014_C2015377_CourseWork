package main.com.university.ncl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 03022023
 * Copyright (C) 2023 Newcastle University, UK
 */
public abstract class AbstractStaff implements Staff {

    public static final String LECTURER = "Lecturer";
    public static final String RESEARCHER = "Researcher";
    private static final Map<StaffID, Staff> staffMap = new HashMap<StaffID, Staff>();
    private final String staffType;
    private final String employmentStatus;
    private SmartCard smartCard;
    private final StaffID staffID;
    private final Name name;

    AbstractStaff(Name name, String staffType, String employmentStatus, StaffID staffID) {
        this.name = new Name(name.getFirstName(), name.getLastName());
        this.staffType = staffType;
        this.employmentStatus = employmentStatus;
        this.staffID = staffID;
    }

    public static Staff getInstance(String staffType, Name name, String employmentStatus) {

        // generate a staff ID
        final StaffID staffID = StaffID.getInstance();

        // check is staffId already exist
        Staff staff = staffMap.get(staffID);

        // if not null return matching staff
        if (staff != null) return staff;

        if (staffType.equals(LECTURER)) {
            staff = new Lecturer(name, staffType, staffID,employmentStatus);
        } else {
            staff = new Researcher(name, staffType, staffID,employmentStatus);
        }

        staffMap.put(staffID, staff);
        return staff;
    }

    public String getStaffType() {
        return staffType;
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
        return this.employmentStatus;
    }

    /**
     * @param smartCard , expect smart card object
     */
    public void setSmartCard(SmartCard smartCard) {
        this.smartCard = smartCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractStaff)) return false;
        AbstractStaff that = (AbstractStaff) o;
        return getStaffType().equals(that.getStaffType()) && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStaffType(), getName());
    }
}
