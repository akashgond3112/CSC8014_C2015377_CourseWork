package uk.ac.university.ncl;

import java.util.List;
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

    final int MAX_SUPERVISE_STUDENTS = 10;

    public int getMAX_SUPERVISE_STUDENTS() {
        return MAX_SUPERVISE_STUDENTS;
    }

    public String getRESEARCHER() {
        return RESEARCHER;
    }

    private Set<Name> listOfStudent;

    public Set<Name> getListOfStudent() {
        return listOfStudent;
    }

    public void setListOfStudent(Set<Name> listOfStudent) {
        this.listOfStudent = listOfStudent;
    }

    public void setStudent(Name name) {
        try {
            if (this.getNoOfStudentSupervised()) {
                throw new Exception("Researcher is already assigned with max no. of student!");
            }
            this.listOfStudent.add(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    final String RESEARCHER = "Researcher";

    /**
     * @see AbstractStaff#AbstractStaff(Name ,String, SmartCard, StaffID)
     */
    public Researcher(Name name,String contractType, SmartCard smartCard, StaffID staffID) {
        super(name,contractType, smartCard, staffID);
    }

    /**
     * @return the staff type
     */
    @Override
    public String getStaffType() {
        return this.RESEARCHER;
    }

    public boolean getNoOfStudentSupervised() {
        return listOfStudent.size() == MAX_SUPERVISE_STUDENTS;
    }
}
