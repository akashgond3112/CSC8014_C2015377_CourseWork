package main.com.university.ncl;

import java.util.InputMismatchException;
import java.util.Set;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 322023
 * Copyright (C) 2023 Newcastle University, UK
 */
public final class Researcher extends AbstractStaff {
    /*
     * researchers can supervise students’ projects while lecturers cannot.
     *
     *  */

    final static int MAX_SUPERVISE_STUDENTS = 10;
    final static String RESEARCHER = "Researcher";
    private int totalNoOfStudent;
    private Set<Name> nameSet;

    /**
     * @see AbstractStaff#AbstractStaff( Name ,String, StaffID)
     */
    public Researcher(Name name,String contractType, StaffID staffID) {
        super(name,contractType, staffID);
    }

    public String getRESEARCHER() {
        return RESEARCHER;
    }

    public Set<Name> getNameSet() {
        return nameSet;
    }

    public void setNameSet(Set<Name> nameSet) {
        if (nameSet == null)
            throw new IllegalArgumentException("Name set cannot be empty, Please check your names entry!");
        for (Name name : nameSet) {
            setStudent(name);
        }
    }

    public void setStudent(Name name) {
        if(this.totalNoOfStudent < MAX_SUPERVISE_STUDENTS)
            throw new InputMismatchException("Researcher is already assigned with max no. of Students! i.e " + totalNoOfStudent + " for the course");
        if(!nameSet.contains(name)){
            nameSet.add(name);
        }else {
            System.out.println(name.toString() + " is already assigned to the Lectures , so Skipping it.");
        }
    }

    /**
     * @return the staff type
     */
    @Override
    public String getStaffType() {
        return RESEARCHER;
    }

    public boolean getNoOfStudentSupervised() {
        return this.totalNoOfStudent == MAX_SUPERVISE_STUDENTS;
    }
}
