package test.java;

import main.com.university.ncl.*;
import main.com.university.ncl.Module;

import java.util.Calendar;
import java.util.Set;

/**
 * @author akash.gond
 * @Project CSC8014_C2015377_CourseWork
 * @Date 02162023
 * Copyright (C) 2023 Newcastle University, UK
 */
public class SmartCardTest {
    static final Calendar calendar = Calendar.getInstance();
    static final StaffManager staffManager = StaffManager.getInstance();

    public static void validateSmartCardForLecturer() {
        Set<Module> modules = staffManager.readInModules("modules.txt");
        calendar.set(1992, Calendar.APRIL, 30);
        Staff staff1 = staffManager.employStaff("Kiki", "Topsy", calendar.getTime(), "Lecturer", "permanent");
        Staff staff2 = staffManager.employStaff("Alan", "Nia", calendar.getTime(), "Lecturer", "fixed");

        Lecturer lecturer1 = (Lecturer) staff1;
        Lecturer lecturer2 = (Lecturer) staff2;

        SmartCard smartCard1 = lecturer1.getSmartCard();
        SmartCard smartCard2 = lecturer2.getSmartCard();

        System.out.println("Validate (Lecturer) date of expiry for staff is not null for both employment status permanent and fixed!");
        Assertions.assertNotNull(smartCard1.getExpiryDate());
        Assertions.assertNotNull(smartCard2.getExpiryDate());

        System.out.println("Validate (Lecturer) date of expiry for staff is equal for both employment status permanent and fixed!");
        Calendar tmpCalendar = Calendar.getInstance();

        tmpCalendar.setTime(smartCard1.getDateOfIssue());
        tmpCalendar.add(Calendar.YEAR, 10);
        Assertions.assertEquals(smartCard1.getExpiryDate(), tmpCalendar.getTime());

        tmpCalendar.setTime(smartCard2.getDateOfIssue());
        tmpCalendar.add(Calendar.YEAR, 2);
        Assertions.assertEquals(smartCard2.getExpiryDate(), tmpCalendar.getTime());

        System.out.println("Validate (Lecturer) date of issue for staff is not null for both employment status permanent and fixed!");
        Assertions.assertNotNull(smartCard1.getDateOfIssue());
        Assertions.assertNotNull(smartCard2.getDateOfIssue());

        System.out.println("Validate (Lecturer) date of birth for staff is not null for both employment status permanent and fixed!");
        Assertions.assertNotNull(smartCard1.getDateOfBirth());
        Assertions.assertNotNull(smartCard2.getDateOfBirth());

        System.out.println("Validate (Lecturer) date of birth for staff is equal for both employment status permanent and fixed!");
        Assertions.assertEquals(smartCard1.getDateOfBirth(), calendar.getTime());
        Assertions.assertEquals(smartCard2.getDateOfBirth(), calendar.getTime());

        System.out.println("Validate (Lecturer) staff employment status is equal for both employment status permanent and fixed!");
        Assertions.assertEquals(smartCard1.getEmploymentStatus(), "permanent");
        Assertions.assertEquals(smartCard2.getEmploymentStatus(), "fixed");

        System.out.println("Validate (Lecturer) staff employment Name is equal for both employment status permanent and fixed!");
        Assertions.assertEquals(smartCard1.getName().toString(), "Kiki Topsy");
        Assertions.assertEquals(smartCard2.getName().toString(), "Alan Nia");

        System.out.println("Validate (Lecturer) staff SmartCardNumber is not null for both employment status permanent and fixed!");
        Assertions.assertNotNull(smartCard1.getSmartCardNumber());
        Assertions.assertNotNull(smartCard2.getSmartCardNumber());

    }

    public static void validateSmartCardForResearcher() {
        Set<Name> students = staffManager.readInStudents("modules.txt");
        calendar.set(1992, Calendar.APRIL, 30);
        Staff staff1 = staffManager.employStaff("Kirby", "Davy", calendar.getTime(), "Researcher", "permanent");
        Staff staff2 = staffManager.employStaff("Cassie", "Kathleen", calendar.getTime(), "Researcher", "fixed");

        Researcher researcher1 = (Researcher) staff1;
        Researcher researcher2 = (Researcher) staff2;

        SmartCard smartCard1 = researcher1.getSmartCard();
        SmartCard smartCard2 = researcher2.getSmartCard();

        System.out.println("Validate (Researcher) date of expiry for staff is not null for both employment status permanent and fixed!");
        Assertions.assertNotNull(smartCard1.getExpiryDate());
        Assertions.assertNotNull(smartCard2.getExpiryDate());

        System.out.println("Validate (Researcher) date of expiry for staff is equal for both employment status permanent and fixed!");
        Calendar tmpCalendar = Calendar.getInstance();

        tmpCalendar.setTime(smartCard1.getDateOfIssue());
        tmpCalendar.add(Calendar.YEAR, 10);
        Assertions.assertEquals(smartCard1.getExpiryDate(), tmpCalendar.getTime().clone());

        tmpCalendar.setTime(smartCard2.getDateOfIssue());
        tmpCalendar.add(Calendar.YEAR, 2);
        Assertions.assertEquals(smartCard2.getExpiryDate(), tmpCalendar.getTime().clone());

        System.out.println("Validate (Researcher) date of issue for staff is not null for both employment status permanent and fixed!");
        Assertions.assertNotNull(smartCard1.getDateOfIssue());
        Assertions.assertNotNull(smartCard2.getDateOfIssue());

        System.out.println("Validate (Researcher) date of birth for staff is not null for both employment status permanent and fixed!");
        Assertions.assertNotNull(smartCard1.getDateOfBirth());
        Assertions.assertNotNull(smartCard2.getDateOfBirth());

        System.out.println("Validate (Researcher) date of birth for staff is equal for both employment status permanent and fixed!");
        Assertions.assertEquals(smartCard1.getDateOfBirth(), calendar.getTime());
        Assertions.assertEquals(smartCard2.getDateOfBirth(), calendar.getTime());

        System.out.println("Validate (Researcher) staff employment status is equal for both employment status permanent and fixed!");
        Assertions.assertEquals(smartCard1.getEmploymentStatus(), "permanent");
        Assertions.assertEquals(smartCard2.getEmploymentStatus(), "fixed");

        System.out.println("Validate (Researcher) staff employment Name is equal for both employment status permanent and fixed!");
        Assertions.assertEquals(smartCard1.getName().toString(), "Kirby Davy");
        Assertions.assertEquals(smartCard2.getName().toString(), "Cassie Kathleen");

        System.out.println("Validate (Researcher) staff SmartCardNumber is not null for both employment status permanent and fixed!");
        Assertions.assertNotNull(smartCard1.getSmartCardNumber());
        Assertions.assertNotNull(smartCard2.getSmartCardNumber());
    }

    public static void main(String[] args) {
        validateSmartCardForLecturer();
        validateSmartCardForResearcher();
    }

}
