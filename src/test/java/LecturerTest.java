package test.java;

import main.com.university.ncl.Lecturer;
import main.com.university.ncl.Module;
import main.com.university.ncl.Staff;
import main.com.university.ncl.StaffManager;

import java.util.Calendar;
import java.util.Set;

/**
 * @author akash.gond
 * @Project CSC8014_C2015377_CourseWork
 * @Date 16022023
 * Copyright (C) 2023 Newcastle University, UK
 */
public class LecturerTest {

    static final Calendar calendar = Calendar.getInstance();
    static final StaffManager staffManager = StaffManager.getInstance();

    public static void validateLecturerClass() {
        Set<Module> modules = staffManager.readInModules("modules.txt");
        calendar.set(1992, Calendar.APRIL, 30);
        Staff staff1 = staffManager.employStaff("Kiki", "Topsy", calendar.getTime(), "Lecturer", "permanent");
        Staff staff3 = staffManager.employStaff("Alan", "Nia", calendar.getTime(), "Lecturer", "fixed");

        Lecturer lecturer1 = (Lecturer) staff1;
        System.out.println("Validate Max credits for Lecturer is initially 0");
        Assertions.assertEquals(0, lecturer1.getTotalCredits());

        System.out.println("Validate Lecturer max credits assigned will be 40.");
        staffManager.addData(staff1.getStaffID(), modules, null);
        Assertions.assertEquals(40, lecturer1.getTotalCredits());

        System.out.println("Validate is Lecturer assigned with enough credits");
        Assertions.assertTrue(lecturer1.isTotalCreditFulfilled());

        System.out.println("Validate the module lecturer is assigned to not null.");
        for (Module module : lecturer1.getModuleSet()) {
            System.out.println(module);
            Assertions.assertNotNull(module);
        }

        System.out.println("Validate lecturer employment status");
        Assertions.assertEquals("permanent", staff1.getStaffEmploymentStatus());
        Assertions.assertEquals("fixed", staff3.getStaffEmploymentStatus());
    }

    public static void main(String[] args) {

        validateLecturerClass();
    }
}
