package main.com.university.ncl;

import java.util.HashSet;
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
    private final Set<Module> moduleSet = new HashSet<>();

    /**
     * @see AbstractStaff#AbstractStaff(Name, String, String, StaffID)
     */
    public Lecturer(Name name, String staffType, StaffID staffID,String employmentStatus) {
        super(name, staffType, employmentStatus, staffID);
    }

    /**
     * @param moduleSet<Module></Module>, expect set of module to be assigned to the lecturer
     *                                    This function allows to set the number of module lecturer will teach.
     *                                    > First check if the module set is not null.
     *                                    > Always check the total credits for lecturer is less than the max credits, if yes add the module.
     *                                    > Once the max credits is reached throw the exception.
     * @throws NullPointerException   if accountType is null
     * @throws InputMismatchException if Lecturer is already assigned with max no. of credits!
     */
    public void setModuleSet(Set<Module> moduleSet) {
        if (moduleSet == null)
            throw new NullPointerException("Module set cannot be empty, Please check your module's entry!");
        for (Module module : moduleSet) {
            if (this.totalCredits < MAX_CREDITS) {
                setModule(module);
            } else {
                System.out.println("Lecturer is already reached with max no. of credits! i.e " + totalCredits + " for the course. So now skipping rest of the modules.");
                break;
            }
        }
    }

    /**
     * @param module , Expect Module Object
     * @see main.com.university.ncl.Module
     * First check if the module is already added to the set, if not add it.
     */
    public void setModule(Module module) {
        if (!moduleSet.contains(module)) {
            moduleSet.add(module);
            totalCredits += module.getCreditScore();
        } else {
            System.out.println(module.getName() + " is already assigned to the Lectures , so now skipping the module.");
        }
    }

    /**
     * @return the staff type
     * a method to get the staff type (either Lecturer, or Researcher).
     */
    @Override
    public String getStaffType() {
        return LECTURER;
    }

    /**
     * @return boolean
     * method which returns true if the lecturer is currently teaching enough
     * credits (40 credits in both semester) and false otherwise.
     */
    public boolean isTotalCreditFulfilled() {
        return this.totalCredits == MAX_CREDITS;
    }

    public int getTotalCredits() {
        return this.totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    /**
     * @return Set<Module></Module>
     * a method to list the modules that a lecturer is assigned to. A module consists
     * of a name (e.g. Introduction to Software Development), a module code (e.g.
     * CSC8011), a semester (e.g. 1) and the number of credits associated with the
     * module (e.g. 10)
     */
    public Set<Module> getModuleSet() {
        return moduleSet;
    }
}
