package main.com.university.ncl;

import java.util.HashSet;
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
    private final Set<Name> studentSupervised = new HashSet<>();

    /**
     * @see AbstractStaff#AbstractStaff(String, String, String, String)
     */
    public Researcher(String firstName, String lastName, String staffType,String employmentStatus) {
        super( firstName,  lastName, staffType, employmentStatus);
    }

    public String getRESEARCHER() {
        return RESEARCHER;
    }

    public void setStudentSupervised(Set<Name> studentSupervised) {
        if (studentSupervised == null)
            throw new NullPointerException("Name set cannot be empty, Please check your names entry!");
        for (Name name : studentSupervised) {
            if (this.totalNoOfStudent < MAX_SUPERVISE_STUDENTS) {
                setStudent(name);
            } else {
                System.out.println("Researcher already reached with max no. of Students! to be supervised i.e " + totalNoOfStudent + " for the course. So now skipping rest of the students");
                break;
            }
        }
    }

    public void setStudent(Name name) {
        if (!studentSupervised.contains(name)) {
            studentSupervised.add(name);
            totalNoOfStudent++;
        } else {
            System.out.println(name.toString() + " is already assigned to the Lectures , so now skipping the student.");
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
