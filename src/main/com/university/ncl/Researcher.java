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
    private Set<Name> studentSupervised;

    /**
     * @see AbstractStaff#AbstractStaff(Name, String, StaffID)
     */
    public Researcher(Name name, String contractType, StaffID staffID) {
        super(name, contractType, staffID);
    }

    public String getRESEARCHER() {
        return RESEARCHER;
    }

    public void setStudentSupervised(Set<Name> studentSupervised) {
        if (studentSupervised == null)
            throw new IllegalArgumentException("Name set cannot be empty, Please check your names entry!");
        for (Name name : studentSupervised) {
            if (this.totalNoOfStudent < MAX_SUPERVISE_STUDENTS) {
                setStudent(name);
            } else {
                throw new InputMismatchException("Researcher is already assigned with max no. of Students! i.e " + totalNoOfStudent + " for the course");
            }
        }
    }

    public void setStudent(Name name) {
        if (!studentSupervised.contains(name)) {
            studentSupervised.add(name);
        } else {
            System.out.println(name.toString() + " is already assigned to the Lectures , so Skipping it.");
        }
    }

    /**
     * @return the staff type
     * a method to get the staff type (either Lecturer, or Researcher).
     */
    @Override
    public String getStaffType() {
        return RESEARCHER;
    }

    /**
     * @return boolean
     * a method which returns true if the researcher is currently supervising enough
     * students (10 in total) and false otherwise
     */
    public boolean isMaxNoOfStudentSupervised() {
        return this.totalNoOfStudent == MAX_SUPERVISE_STUDENTS;
    }

    /**
     * @return Set<Name></Name>
     * a method to return the list of students who are supervised by a researcher
     */
    public Set<Name> getStudentSupervised() {
        return studentSupervised;
    }

}
