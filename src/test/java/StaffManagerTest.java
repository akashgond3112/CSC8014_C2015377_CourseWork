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
    final Calendar calendar = Calendar.getInstance();

    private void testReadInModules() {
        final StaffManager staffManager = new StaffManager();

        // validate proper error is thrown when we pass the incorrect file path.
        try {
            staffManager.readInModules("");
        } catch (Throwable t) {
            System.out.println("Test got passed!");
            test.java.Assertions.assertExpectedThrowable(
                    IllegalArgumentException.class, t);
        }

        // validate set of module set is not null
        Assertions.assertNotNull(staffManager.readInModules("modules.txt"));

    }

    private void testReadInStudents() {
        final StaffManager staffManager = new StaffManager();

        // validate proper error is thrown when we pass the incorrect file path.
        try {
            staffManager.readInStudents("");
        } catch (Throwable t) {
            System.out.println("Test got passed!");
            Assertions.assertExpectedThrowable(
                    IllegalArgumentException.class, t);
        }

        // validate set of student set is not null
        Assertions.assertNotNull(staffManager.readInStudents("students.txt"));

    }

    private void validateEmployStaff() {

        final StaffManager staffManager = new StaffManager();


        final Date dob = calendar.getTime();

        // validate when all the params is passed a null on the method
        try {
            staffManager.employStaff(null, null, null, null, null);
        } catch (Throwable t) {
            System.out.println("when all the params is passed a null. Test got passed!");
            Assertions.assertExpectedThrowable(
                    NullPointerException.class, t);
        }

        // validate when either first name or last name is passed as Integer value instead of string.
        try {
            staffManager.employStaff("1234", "Gond", dob, "null", "null");
        } catch (Throwable t) {
            System.out.println("when either first name or last name is passed as Integer value instead of string. Test got passed!");
            Assertions.assertExpectedThrowable(
                    NumberFormatException.class, t);
        }

        // validate when staff Type is passed as incorrect value.
        try {
            staffManager.employStaff("Akash", "Gond", dob, "Dean", "null");
        } catch (Throwable t) {
            System.out.println("when staff Type is passed as incorrect value. Test got passed!");
            Assertions.assertExpectedThrowable(
                    InputMismatchException.class, t);
        }

        // validate when employment status Type is passed as incorrect value.
        try {
            staffManager.employStaff("Akash", "Gond", dob, "Lecturer", "null");
        } catch (Throwable t) {
            System.out.println("when employment status Type is passed as incorrect value. Test got passed!");
            Assertions.assertExpectedThrowable(
                    InputMismatchException.class, t);
        }

        // validate when DOB is passed as incorrect not meeting the criteria!
        try {
            staffManager.employStaff("Akash", "Gond", dob, "Lecturer", "permanent");
        } catch (Throwable t) {
            System.out.println("When provided DOB is not valid, Test got passed!");
            Assertions.assertExpectedThrowable(
                    InputMismatchException.class, t);
        }

        // positive scenario
        final Calendar dobNew = Calendar.getInstance();
        dobNew.set(1992, Calendar.APRIL, 30);
        Assertions.assertNotNull(staffManager.employStaff("Akash", "Gond", dobNew.getTime(), "Lecturer", "permanent"));
        Assertions.assertNotNull(staffManager.employStaff("Subham", "Singh", dobNew.getTime(), "Lecturer", "fixed"));
        Assertions.assertNotNull(staffManager.employStaff("Vikash", "Gond", dobNew.getTime(), "Researcher", "permanent"));
        Assertions.assertNotNull(staffManager.employStaff("Harsh", "Rohilla", dobNew.getTime(), "Researcher", "fixed"));


    }

    private void validateAddData() {
        final StaffManager staffManager = new StaffManager();

        // validate when there is no staff employed till now.
        try {
            staffManager.addData(null, null, null);
        } catch (Throwable t) {
            System.out.println("when there is no staff employed till now. Test got passed!");
            Assertions.assertExpectedThrowable(
                    NullPointerException.class, t);
        }

        //validate when there is staff but , staffId is passed as null;
        final Calendar dobNew = Calendar.getInstance();
        dobNew.set(1992, Calendar.APRIL, 30);
        Staff staff1 = staffManager.employStaff("Akash", "Gond", dobNew.getTime(), "Lecturer", "permanent");
        Staff staff2 = staffManager.employStaff("Subham", "Singh", dobNew.getTime(), "Lecturer", "fixed");
        Staff staff3 = staffManager.employStaff("Vikash", "Gond", dobNew.getTime(), "Researcher", "permanent");
        Staff staff4 = staffManager.employStaff("Harsh", "Rohilla", dobNew.getTime(), "Researcher", "fixed");

        Set<Module> modules = staffManager.readInModules("modules.txt");
        Set<Name> students = staffManager.readInStudents("students.txt");


        try {
            staffManager.addData(null, null, null);
        } catch (Throwable t) {
            System.out.println("when staff Id, modules and students is passes as null. Test got passed!");
            Assertions.assertExpectedThrowable(
                    NullPointerException.class, t);
        }

        //validate when we try to add data for invalid staffID
        try {
            staffManager.addData(StaffID.getInstance(), modules, students);
        } catch (Throwable t) {
            System.out.println("when we try to add data for invalid staffID. Test got passed!");
            Assertions.assertExpectedThrowable(
                    NullPointerException.class, t);
        }

        //validate when we send valid staff id and staff type as Lecturer and valid modules
        Assertions.assertTrue(staffManager.addData(staff1.getStaffID(), modules, null));

        //validate when we send valid staff id and staff type as Researcher and valid students
        Assertions.assertTrue(staffManager.addData(staff3.getStaffID(), null, students));

        //Validate getStaffId method doesn't return null;
        Assertions.assertNotNull(staff1.getStaffID());

        //Validate getSmartCard method doesn't return null;
        Assertions.assertNotNull(staff1.getSmartCard());

        //Validate getStaffEmploymentStatus method doesn't return null;
        Assertions.assertNotNull(staff1.getStaffEmploymentStatus());
        Assertions.assertEquals(staff1.getStaffEmploymentStatus(),"permanent");

        //Validate getName method doesn't return null;
        Assertions.assertNotNull(staff1.getName());
        Assertions.assertEquals(staff1.getName().toString(), "Akash Gond");

    }

    public static void main(String[] args) {
        StaffManagerTest staffManagerTest = new StaffManagerTest();
        staffManagerTest.testReadInModules();
        staffManagerTest.testReadInStudents();
        staffManagerTest.validateEmployStaff();
        staffManagerTest.validateAddData();

    }

}