package main.com.university.ncl.staff;

import main.com.university.ncl.model.Name;
import main.com.university.ncl.model.SmartCard;
import main.com.university.ncl.model.StaffID;

import java.util.*;

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
    private final SmartCard smartCard;
    private final StaffID staffID;
    private final Name name;

    AbstractStaff(String firstName, String lastName, String staffType, String employmentStatus, Date dob) {
        this.name = new Name(firstName, lastName);
        this.staffType = staffType;
        this.employmentStatus = employmentStatus;
        this.staffID = StaffID.getInstance();
        this.smartCard = new SmartCard(this.employmentStatus, this.name, dob);
    }

    /**
     * Return an account of the specified type with the specified
     * account number.
     *
     * @param staffType        the type of staff to return
     * @param firstName        first name for the staff
     * @param lastName         last name for the staff
     * @param employmentStatus a Staff can be either on Permanent or fixed contract
     * @param dob              Date of birth of the staff
     * @return a staff of the specified staff type. An existing account
     * is returned if a staff with same name is already known. Otherwise,
     * a new staff with the given Name and staff type  is returned.
     * @throws NullPointerException     if staffType,first name, last name, employment status, DOB is null
     * @throws IllegalArgumentException if staffType is not matching
     */
    public static Staff getInstance(String staffType, String firstName, String lastName, String employmentStatus, Date dob) {

        if (staffType == null && firstName == null && lastName == null && employmentStatus == null && dob == null)
            throw new NullPointerException("Params provided cannot be null while creating staff!");

        // As per the discussion with the instructor we were told that , we should not allow a staff who is having the same and staff type.
        // for e.g. code should not create an object if try to add name as : "Akash Gond" and staff type as a "Lecturer" 2 times.
        // Also, if we try to add the same staff as different staff type as e.e. "Researcher"
        // Inorder to achieve that we can create a Name details as unique ID which can be used as key in the map
        final String uniqueId = SERIAL_NO + "_" + firstName + "_" + lastName + "_" + SERIAL_NO;

        // check is uniqueId already exist
        // enforce single instance per staff Name a staff cannot be both Lecturer and researcher.
        Staff staff = staffMap.get(uniqueId);

        // if not null return matching staff which impose uniqueness
        if (staff != null) return staff;

        // verify if the provided staff is Lecturer or Researcher else throw exception.
        if (staffType.equals(LECTURER)) {
            staff = new Lecturer(firstName, lastName, staffType, employmentStatus, dob);
        } else if (staffType.equals(RESEARCHER)) {
            staff = new Researcher(firstName, lastName, staffType, employmentStatus, dob);
        } else {
            throw new InputMismatchException("Invalid staff type provided!");
        }

        // put staff in staff map
        staffMap.put(uniqueId, staff);
        // return the instance
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
}
