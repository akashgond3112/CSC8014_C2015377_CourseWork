package main.com.university.ncl;

import java.util.InputMismatchException;
import java.util.Set;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 322023
 * Copyright (C) 2023 Newcastle University, UK
 */
public final class Lecturer extends AbstractStaff {

    /*
    > lecturers can teach on the different modules whereas researchers cannot.
    > Lecturers can teach up to 40 credits
    */

    final static int MAX_CREDITS = 40;
    final static String LECTURER = "Lecturer";
    private int totalCredits;
    private Set<Module> moduleSet;

    /**
     * @see AbstractStaff#AbstractStaff(Name, String, StaffID)
     */
    public Lecturer(Name name, String contractType, StaffID staffID) {
        super(name, contractType, staffID);
    }

    public Set<Module> getModuleSet() {
        return moduleSet;
    }

    public void setModuleSet(Set<Module> moduleSet) {

        if (moduleSet == null)
            throw new IllegalArgumentException("Module set cannot be empty, Please check your module's entry!");
        if(isTotalCreditFulfilled())
            throw new IllegalArgumentException("Lecturer is already assigned with max no. of credits! i.e " + totalCredits + " for the course");
        for (Module module : moduleSet) {
            setModule(module);
        }
    }

    public void setModule(Module module) {
        if (this.totalCredits < MAX_CREDITS)
            throw new InputMismatchException("Lecturer is already assigned with max no. of credits! i.e " + totalCredits + " for the course");

        if(!moduleSet.contains(module)){
            moduleSet.add(module);
            totalCredits += module.getCreditScore();
        } else {
            System.out.println(module.getName() + " is already assigned to the Lectures , so Skipping it.");
        }
    }

    /**
     * @return the staff type
     */
    @Override
    public String getStaffType() {
        return LECTURER;
    }

    public boolean isTotalCreditFulfilled() {
        return this.totalCredits == MAX_CREDITS;
    }

    public int getTotalCredits() {
        return this.totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }
}
