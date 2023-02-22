package main.com.university.ncl.staff;

import main.com.university.ncl.model.Name;
import main.com.university.ncl.model.Module;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 322023
 * Copyright (C) 2023 Newcastle University, UK
 * Researcher -  researchers can supervise students’ projects
 * while lecturers cannot
 * This Researcher can only be instantiated by classes in
 * this same package. Use the AbstractStaff.getInstance("Researcher")
 * to get an instance.
 * package-private class definition
 * cannot be imported to other packages
 */
public final class Researcher extends AbstractStaff {
    /*
     * researchers can supervise students’ projects while lecturers cannot.
     *
     *  */

    final static int MAX_SUPERVISE_STUDENTS = 10;
    private int totalNoOfStudent;
    private final Set<Name> studentSupervised = new HashSet<>();

    /**
     * @see AbstractStaff#AbstractStaff(String, String, String, String, Date)
     * package-private constructor cannot be directly instantiated by
     * clients outside this package.
     * Use AbstractStaff.getInstance("Lecturer") instead.
     */
    public Researcher(String firstName, String lastName, String staffType, String employmentStatus, Date dob) {
        super(firstName, lastName, staffType, employmentStatus, dob);
    }

    /**
     * @param studentSupervised<Name></Name>, expect set of student name to be assigned to the Researcher
     *                                        This function allows to set the number of student Researcher will supervise.
     *                                        > First check if the studentSupervised set is not null.
     *                                        > Always check the total No Of Student for Researcher is less than the max supervised student, if yes add the student.
     *                                        > Once the max supervised student is reached throw the exception.
     * @throws NullPointerException if moduleSet is null
     * @see Module
     */
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

    /**
     * @param name , Expect Name Object
     * @see Name
     * First check if the Name is already added to the set, if not add it.
     */
    public void setStudent(Name name) {
        if (!studentSupervised.contains(name)) {
            studentSupervised.add(new Name(name.getFirstName(), name.getLastName()));
            totalNoOfStudent++;
        } else {
            System.out.println(name.toString() + " is already assigned to the Researcher , so now skipping the student.");
        }
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
     * @return , utility method to allow to know total No. of student supervised by the Researcher.
     */
    public int getTotalNoOfStudent() {
        return totalNoOfStudent;
    }

    /**
     * @return Set<Name></Name>
     * a method to return the list of students who are supervised by a researcher
     * A Name class to store student first name and last name details.
     */
    public Set<Name> getStudentSupervised() {
        return new HashSet<Name>(this.studentSupervised);
    }

}
