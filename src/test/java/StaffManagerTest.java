package test.java;

import main.com.university.ncl.*;
import main.com.university.ncl.Module;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 10022023
 * Copyright (C) 2023 Newcastle University, UK
 */
public class StaffManagerTest {
    final StaffManager staffManager = StaffManager.getInstance();

    private void testReadInModules() {
        System.out.println("validate proper error is thrown when we pass the file path is empty for modules.");
        try {
            staffManager.readInModules("");
        } catch (Throwable t) {
            System.out.println("Test got passed!");
            test.java.Assertions.assertExpectedThrowable(
                    IllegalArgumentException.class, t);
        }

        System.out.println("validate proper error is thrown when we pass the file path is incorrect for modules.");
        try {
            staffManager.readInModules("mod.txt");
        } catch (Throwable t) {
            System.out.println("Test got passed!");
            test.java.Assertions.assertExpectedThrowable(
                    FileNotFoundException.class, t);
        }

        System.out.println("validate proper error is thrown when we pass data is incorrect for modules e.g. semester as string value.");
        try {
            staffManager.readInModules("modules_dummy.txt");
        } catch (Throwable t) {
            System.out.println("Test got passed!");
            test.java.Assertions.assertExpectedThrowable(
                    NumberFormatException.class, t);
        }

        System.out.println("validate when the path is correct for modules");
        Assertions.assertNotNull(staffManager.readInModules("modules.txt"));

    }

    private void testReadInStudents() {

        System.out.println("validate proper error is thrown when we pass the file path is empty fr students.");
        try {
            staffManager.readInStudents("");
        } catch (Throwable t) {
            System.out.println("Test got passed!");
            Assertions.assertExpectedThrowable(
                    IllegalArgumentException.class, t);
        }

        System.out.println("validate proper error is thrown when we pass the file path is incorrect for students.");
        try {
            staffManager.readInModules("stu.txt");
        } catch (Throwable t) {
            System.out.println("Test got passed!");
            test.java.Assertions.assertExpectedThrowable(
                    FileNotFoundException.class, t);
        }

        System.out.println("validate when the path is correct for students");
        Assertions.assertNotNull(staffManager.readInStudents("students.txt"));

    }

    private void validateEmployStaff() {
        Calendar calendar = Calendar.getInstance();

        final Date dob = calendar.getTime();

        System.out.println("validate when all the params is passed a null on the method");
        try {
            staffManager.employStaff(null, null, null, null, null);
        } catch (Throwable t) {
            System.out.println("when all the params is passed a null. Test got passed!");
            Assertions.assertExpectedThrowable(
                    NullPointerException.class, t);
        }

        System.out.println("validate when either first name or last name is passed as Integer value instead of string.");
        try {
            staffManager.employStaff("1234", "Gond", dob, "null", "null");
        } catch (Throwable t) {
            System.out.println("when either first name or last name is passed as Integer value instead of string. Test got passed!");
            Assertions.assertExpectedThrowable(
                    NumberFormatException.class, t);
        }

        System.out.println("validate when staff Type is passed as incorrect value.");
        try {
            staffManager.employStaff("Tess", "Cooke", dob, "Dean", "null");
        } catch (Throwable t) {
            System.out.println("when staff Type is passed as incorrect value. Test got passed!");
            Assertions.assertExpectedThrowable(
                    InputMismatchException.class, t);
        }

        System.out.println("validate when employment status Type is passed as incorrect value.");
        try {
            staffManager.employStaff("Ela", "Wong", dob, "Lecturer", "null");
        } catch (Throwable t) {
            System.out.println("when employment status Type is passed as incorrect value. Test got passed!");
            Assertions.assertExpectedThrowable(
                    InputMismatchException.class, t);
        }

        System.out.println("validate when DOB is passed as incorrect not meeting the criteria!");
        try {
            staffManager.employStaff("Lester", "Cole", dob, "Lecturer", "permanent");
        } catch (Throwable t) {
            System.out.println("When provided DOB is not valid, Test got passed!");
            Assertions.assertExpectedThrowable(
                    InputMismatchException.class, t);
        }

        System.out.println("validate the positive scenario when we add valid data!");
        calendar.set(1992, Calendar.APRIL, 25);
        final Date validDob = calendar.getTime();

        Assertions.assertNotNull(staffManager.employStaff("Kiki", "Topsy", validDob, "Lecturer", "permanent"));
        Assertions.assertNotNull(staffManager.employStaff("Alan", "Nia", validDob, "Lecturer", "fixed"));
        Assertions.assertNotNull(staffManager.employStaff("Kirby", "Davy", validDob, "Researcher", "permanent"));
        Assertions.assertNotNull(staffManager.employStaff("Cassie", "Kathleen", validDob, "Researcher", "fixed"));

        Staff staff = staffManager.employStaff("Harsh", "Rohilla", validDob, "Researcher", "fixed");

        // Duplicate staff having same name and staff type should not be allowed.
        try {
            Staff staff5 = staffManager.employStaff("Harsh", "Rohilla", validDob, "Researcher", "fixed");
        } catch (Throwable t) {
            System.out.println("validate staff having same Name and staffType as same should not be allowed., Test got passed!");
            Assertions.assertExpectedThrowable(
                    InputMismatchException.class, t);
        }

        //validate staff having same Name but staffType as different should not be allowed.
        try {
            Staff staff6 = staffManager.employStaff("Harsh", "Rohilla", validDob, "Lecturer", "fixed");
        } catch (Throwable t) {
            System.out.println("validate staff having same Name but different staffType should not be allowed., Test got passed!");
            Assertions.assertExpectedThrowable(
                    InputMismatchException.class, t);
        }


    }

    private void validateAddData() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1992, Calendar.APRIL, 30);
        final Date validDob = calendar.getTime();

        System.out.println("validate when there is no staff employed till now.");
        try {
            staffManager.addData(null, null, null);
        } catch (Throwable t) {
            System.out.println("when there is no staff employed till now. Test got passed!");
            Assertions.assertExpectedThrowable(
                    NullPointerException.class, t);
        }

        System.out.println("validate when there is staff but , staffId is passed as null;");
        Staff staff1 = staffManager.employStaff("Harry", "Morgan", validDob, "Lecturer", "permanent");
        Staff staff2 = staffManager.employStaff("Amy", "Phillips", validDob, "Lecturer", "fixed");
        Staff staff3 = staffManager.employStaff("Larry", "Taylor", validDob, "Researcher", "permanent");
        Staff staff4 = staffManager.employStaff("Victoria", "Edwards", validDob, "Researcher", "fixed");


        Set<Module> modules = staffManager.readInModules("modules.txt");
        Set<Name> students = staffManager.readInStudents("students.txt");


        System.out.println("validate when staff Id, modules and students is passes as null.");
        try {
            staffManager.addData(null, null, null);
        } catch (Throwable t) {
            System.out.println("when staff Id, modules and students is passes as null. Test got passed!");
            Assertions.assertExpectedThrowable(
                    NullPointerException.class, t);
        }

        System.out.println("validate when we try to add data for invalid staffID.");
        try {
            staffManager.addData(StaffID.getInstance(), modules, students);
        } catch (Throwable t) {
            System.out.println("when we try to add data for invalid staffID. Test got passed!");
            Assertions.assertExpectedThrowable(
                    NullPointerException.class, t);
        }

        System.out.println("validate when we send valid staff id and staff type as Lecturer and valid modules.");
        Assertions.assertTrue(staffManager.addData(staff1.getStaffID(), modules, null));

        System.out.println("validate when we send valid staff id and staff type as Researcher and valid students.");
        Assertions.assertTrue(staffManager.addData(staff3.getStaffID(), null, students));

        System.out.println("validate when we send valid staff id and staff type as Lecturer and valid students.");
        try {
            staffManager.addData(staff1.getStaffID(), null, students);
        } catch (Throwable t) {
            System.out.println("when when we send valid staff id and staff type as Lecturer and valid students. Test got passed!");
            Assertions.assertExpectedThrowable(
                    InputMismatchException.class, t);
        }

        System.out.println("validate when we send valid staff id and staff type as Researcher and valid modules");
        try {
            staffManager.addData(staff3.getStaffID(), modules, null);
        } catch (Throwable t) {
            System.out.println("when we send valid staff id and staff type as Researcher and valid modules. Test got passed!");
            Assertions.assertExpectedThrowable(
                    InputMismatchException.class, t);
        }

        System.out.println("Validate getStaffId method doesn't return null.");
        Assertions.assertNotNull(staff1.getStaffID());

        System.out.println("Validate getSmartCard method doesn't return null.");
        Assertions.assertNotNull(staff1.getSmartCard());

        System.out.println("Validate getStaffEmploymentStatus method doesn't return null and have expected employment status.");
        Assertions.assertNotNull(staff1.getStaffEmploymentStatus());
        Assertions.assertEquals(staff1.getStaffEmploymentStatus(), "permanent");

        System.out.println("Validate getName method doesn't return null and have expected Name.");
        Assertions.assertNotNull(staff1.getName());
        Assertions.assertEquals(staff1.getName().toString(), "Harry Morgan");

        System.out.println("validate method to list the modules that a lecturer is assigned to.");
        Assertions.assertNotNull(((Lecturer) staff1).getModuleSet());
    }

    private void validateTerminateStaff() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1992, Calendar.APRIL, 30);
        System.out.println("Validate when we try to terminate a invalid staffID.");
        try {
            staffManager.terminateStaff(StaffID.getInstance());
        } catch (Throwable t) {
            System.out.println("when we try to terminate a invalid staffID. Test got passed!");
            Assertions.assertExpectedThrowable(
                    NullPointerException.class, t);
        }

        System.out.println("Validate when we try to terminate a valid staffID.");
        try {
            // since the method doesn't have any return type, in order to test the valid staffID scenario, we can follow the below step
            // 1. first remove the valid staff id and then try to remove the same staff ID , we should get an exception.
            Staff staff7 = staffManager.employStaff("Tom", "Cruise", calendar.getTime(), "Lecturer", "fixed");

            staffManager.terminateStaff(staff7.getStaffID());
            staffManager.terminateStaff(staff7.getStaffID());
        } catch (Throwable t) {
            System.out.println("when we try to terminate a valid staffID. Test got passed!");
            Assertions.assertExpectedThrowable(
                    NullPointerException.class, t);
        }
    }

    private void validateGetAllStaff() {
        //validate staff having same Name but staffType as different can be allowed
        System.out.println("validate Get all staff return expected output.");
        Assertions.assertEquals(staffManager.getAllStaff().size(), 9);
    }

    private void validateNoOfStaff() {
        //validate staff having same Name but staffType as different can be allowed
        System.out.println("validate Get number of matching staff return expected output.");
        Assertions.assertEquals(staffManager.noOfStaff("Lecturer"), 4);
        Assertions.assertEquals(staffManager.noOfStaff("Researcher"), 5);

        // Add same data test matching staff
        Calendar calendar = Calendar.getInstance();
        calendar.set(1992, Calendar.APRIL, 30);

        System.out.println("Add same data and test matching staff method and validate Get number of matching staff return expected output.");
        try {
            Staff staff5 = staffManager.employStaff("Harsh", "Rohilla", calendar.getTime(), "Researcher", "fixed");
        } catch (Throwable t) {
            System.out.println("Add same data and test matching staff method and validate Get number of matching staff return expected output. Test got passed!");
            Assertions.assertExpectedThrowable(
                    InputMismatchException.class, t);
        }
        Assertions.assertEquals(staffManager.noOfStaff("Researcher"), 5);

        System.out.println("Validate when user send an invalid staff type it should return 0");
        Assertions.assertEquals(staffManager.noOfStaff("Assistant"), 0);


    }

    public static void main(String[] args) {
        StaffManagerTest staffManagerTest = new StaffManagerTest();
        staffManagerTest.testReadInModules();
        staffManagerTest.testReadInStudents();
        staffManagerTest.validateEmployStaff();
        staffManagerTest.validateAddData();
        staffManagerTest.validateTerminateStaff();
        staffManagerTest.validateGetAllStaff();
        staffManagerTest.validateNoOfStaff();

    }

}
