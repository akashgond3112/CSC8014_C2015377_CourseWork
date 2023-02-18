package test.java;

import main.com.university.ncl.*;
import main.com.university.ncl.Module;

import java.util.Calendar;
import java.util.Set;

/**
 * @author akash.gond
 * @Project CSC8014_C2015377_CourseWork
 * @Date 1622023
 * Copyright (C) 2023 Newcastle University, UK
 */
public class ResearcherTest {
    static final Calendar calendar = Calendar.getInstance();
    static final StaffManager staffManager = new StaffManager();

    public static void validateResearcherClass() {
        Set<Module> modules = staffManager.readInModules("modules.txt");
        calendar.set(1992, Calendar.APRIL, 30);
        Staff staff1 = staffManager.employStaff("Kirby", "Davy", calendar.getTime(), "Researcher", "permanent");
        Staff staff2 =staffManager.employStaff("Cassie", "Kathleen", calendar.getTime(), "Researcher", "fixed");

        Researcher researcher1 = (Researcher) staff1;
        System.out.println("Validate Max student supervised for Researcher is initially 0");

        System.out.println("Validate Researcher employment status");
        Assertions.assertEquals("permanent", staff1.getStaffEmploymentStatus());
    }

    public static void main(String[] args) {

        validateResearcherClass();
    }
}