package main.com.university.ncl;

import java.util.HashMap;
import java.util.InputMismatchException;
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
    public static final String SERIAL_NO = "0be7cd90-3498-4b09-9b36-8e4bbddc859f";
    private static final Map<String, Staff> staffMap = new HashMap<>();
    private final String staffType;
    private final String employmentStatus;
    private SmartCard smartCard;
    private final StaffID staffID;
    private final Name name;

    AbstractStaff(String firstName, String lastName, String staffType, String employmentStatus) {
        this.name = new Name(firstName, lastName);
        this.staffType = staffType;
        this.employmentStatus = employmentStatus;
        this.staffID = StaffID.getInstance();
    }

    public static Staff getInstance(String staffType, String firstName, String lastName, String employmentStatus) {

        // As per the discussion with the instructor we were told that , we should not allow a staff who is having the same and staff type.
        // for e.g. code should not create an object if try to add name as : "Akash Gond" and staff type as a "Lecturer" 2 times.
        // Inorder to achieve that we can create a unique ID which will be the combination of the below filed.
        // generate a unique ID with a combination of first name + last name and staffType  e.g SERIAL_NO_Akash_Gond_Lecturer_SERIAL_NO or Akash_Gond_Researcher
        final String uniqueId = SERIAL_NO + "_" + firstName + "_" + lastName + "_" + staffType + "_" + SERIAL_NO;

        // check is uniqueId already exist
        Staff staff = staffMap.get(uniqueId);

        // if not null return matching staff
        if (staff != null) return staff;

        if (staffType.equals(LECTURER)) {
            staff = new Lecturer(firstName, lastName, staffType, employmentStatus);
        } else if (staffType.equals(RESEARCHER)) {
            staff = new Researcher(firstName, lastName, staffType, employmentStatus);
        } else {
            throw new InputMismatchException("Invalid staff type provided!");
        }

        staffMap.put(uniqueId, staff);
        return staff;
    }

    /**
     * Returns the Staff type.
     * a Staff can be either a Lecturer or a Researcher
     *
     * @return a string (Lecturer or Researcher)
     */
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
        return new Name(this.name.getFirstName(), this.name.getLastName());
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
     * Returns the Staff employment status.
     * a Staff can be either on Permanent or fixed contract
     *
     * @return a string (Permanent or fixed)
     */
    public String getStaffEmploymentStatus() {
        return this.employmentStatus;
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
     * @param smartCard , expect smart card object
     */
    public void setSmartCard(SmartCard smartCard) {
        this.smartCard = new SmartCard(smartCard.getEmploymentStatus(), smartCard.getName(), smartCard.getDateOfBirth());
    }
}
