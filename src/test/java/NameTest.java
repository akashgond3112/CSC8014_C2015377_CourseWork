package test.java;

import main.com.university.ncl.staff.Staff;
import main.com.university.ncl.StaffManager;

import java.util.Calendar;

/**
 * @author akash.gond
 * @Project CSC8014_C2015377_CourseWork
 * @Date 02162023
 * Copyright (C) 2023 Newcastle University, UK
 */
public class NameTest {

    static final Calendar calendar = Calendar.getInstance();
    static final StaffManager staffManager = StaffManager.getInstance();

    public static void validateNameClass() {
        calendar.set(1992, Calendar.APRIL, 30);
        Staff staff1 = staffManager.employStaff("Kiki", "Topsy", calendar.getTime(), "Lecturer", "permanent");

        System.out.println("validate staff name is not null!");
        Assertions.assertNotNull(staff1.getName());
        System.out.println("validate staff name is as expected value!");
        Assertions.assertEquals(staff1.getName().toString(), "Kiki Topsy");
    }

    public static void main(String[] args) {
        validateNameClass();
    }
}
