package main.com.university.ncl.staff;

import main.com.university.ncl.model.Module;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 322023
 * Copyright (C) 2023 Newcastle University, UK
 * Lecturer -   lecturers can teach on the different modules
 * whereas researchers cannot.
 * This Lecturer can only be instantiated by classes in
 * this same package. Use the AbstractStaff.getInstance("Lecturer")
 * to get an instance.
 * package-private class definition
 * cannot be imported to other packages
 */
public final class Lecturer extends AbstractStaff {

    /*
    > lecturers can teach on the different modules whereas researchers cannot.
    > Lecturers can teach up to 40 credits
    */

    final static int MAX_CREDITS = 40;
    private int totalCredits;
    private final Set<Module> moduleSet = new HashSet<>();

    /**
     * @see AbstractStaff#AbstractStaff(String, String, String, String, Date)
     * package-private constructor cannot be directly instantiated by
     * clients outside this package.
     * Use AbstractStaff.getInstance("Lecturer") instead.
     */
    Lecturer(String firstName, String lastName, String staffType, String employmentStatus, Date dob) {
        super(firstName, lastName, staffType, employmentStatus, dob);
    }

    /**
     * @param moduleSet<Module></Module>, expect set of module to be assigned to the lecturer
     *                                    This function allows to set the number of module lecturer will teach.
     *                                    > First check if the module set is not null.
     *                                    > Always check the total credits for lecturer is less than the max credits, if yes add the module.
     *                                    > Once the max credits is reached throw the exception.
     * @throws NullPointerException if moduleSet is null
     * @see Module
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
     * @see Module
     * First check if the module is already added to the set, if not add it.
     */
    public void setModule(Module module) {
        if (!moduleSet.contains(module)) {
            moduleSet.add(new Module(module.getCode(), module.getName(), module.getSemester(), module.getCreditScore()));
            totalCredits += module.getCreditScore();
        } else {
            System.out.println(module.getName() + " is already assigned to the Lectures , so now skipping the module.");
        }
    }


    /**
     * @return boolean
     * method which returns true if the lecturer is currently teaching enough
     * credits (40 credits in both semester) and false otherwise.
     */
    public boolean isTotalCreditFulfilled() {
        return this.totalCredits == MAX_CREDITS;
    }

    /**
     * @return , utility method to allow to know total credits for the lecturer object.
     */
    public int getTotalCredits() {
        return this.totalCredits;
    }

    /**
     * @return Set<Module></Module>
     * @see Module
     * a method to list the modules that a lecturer is assigned to.
     * A module consists of a name (e.g. Introduction to Software Development), a module code (e.g.
     * CSC8011), a semester (e.g. 1) and the number of credits associated with the
     * module (e.g. 10)
     */
    public Set<Module> getModuleSet() {
        return new HashSet<Module>(this.moduleSet);
    }
}
