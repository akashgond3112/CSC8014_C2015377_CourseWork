package uk.ac.university.ncl;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

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

    final int MAX_CREDITS = 40;
    final String LECTURER = "Lecturer";
    private Set<Module> moduleSet;

    public Set<Module> getModuleSet() {
        return moduleSet;
    }

    public void setModuleSet(Set<Module> moduleSet) {
        this.moduleSet = moduleSet;
    }

    public void setModule(Module module) {
        try {
            if (this.getTotalNoOfCredits()) {
                throw new Exception("");
            }
            this.moduleSet.add(module);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @see AbstractStaff#AbstractStaff(Name,String, SmartCard, StaffID)
     */
    public Lecturer(Name name, String contractType, SmartCard smartCard, StaffID staffID) {
        super(name, contractType, smartCard, staffID);
    }

    /**
     * @return the staff type
     */
    @Override
    public String getStaffType() {
        return this.LECTURER;
    }

    public boolean getTotalNoOfCredits() {
        AtomicInteger totalCreditScore = new AtomicInteger();
        moduleSet.forEach(m -> totalCreditScore.addAndGet(m.getCreditScore()));

        return Integer.parseInt(String.valueOf(totalCreditScore)) == this.MAX_CREDITS;
    }
}
