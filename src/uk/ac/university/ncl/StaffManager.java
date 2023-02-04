package uk.ac.university.ncl;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.util.stream.Collectors;


public class StaffManager {

    //you can add attributes and other methods if needed.
    //you can throw an exception if needed
    private static HashSet<Staff> staffs = new HashSet<>();
    private static HashSet<StaffID> existingStaffIDs = new HashSet<>();
    private static HashSet<SmartCardNumber> existingSmartCardNumbers = new HashSet<>();
    Set<Module> moduleList = new HashSet<>();
    Set<Name> studentList = new HashSet<>();

    public Set<Module> readInModules(String path) {
        //add your code here. Do NOT change the method signature
        try {
            Scanner readCsv = new Scanner(new File(path));
            while (readCsv.hasNextLine()) {
                String line = readCsv.nextLine();
                String[] lineArray = line.split(","); // split the sentences using String split inbuilt method.

                // adding the Exhibit object to the list of the exhibits.
                moduleList.add(new Module(lineArray[0], lineArray[1], Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3])));

            }
            readCsv.close(); // closing the Scanner class

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage()); // catching the exception .
        }
        return moduleList;
    }

    public Set<Name> readInStudents(String path) {
        //add your code here. Do NOT change the method signature
        try {
            Scanner readCsv = new Scanner(new File(path));
            while (readCsv.hasNextLine()) {
                String line = readCsv.nextLine();
                String[] lineArray = line.split(","); // split the sentences using String split inbuilt method.

                // adding the Exhibit object to the list of the exhibits.
                studentList.add(new Name(lineArray[0], lineArray[1]));
            }
            readCsv.close(); // closing the Scanner class

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage()); // catching the exception .
        }
        return studentList;
    }

    public Staff employStaff(String firstName, String lastName, Date dob, String staffType, String employmentStatus) {
        //add your code here. Do NOT change the method signature
        Staff staff = null;

        Name name = new Name(firstName, lastName);

        if (existingStaffIDs.contains(new StaffID())) return null;
        StaffID staffID = new StaffID();

        if (staffType.equals("Lecturer")) {
            staff = new Lecturer(new Name(firstName, lastName), employmentStatus, null, staffID);
        } else if (staffType.equals("Researcher")) {
            staff = new Researcher(new Name(firstName, lastName), employmentStatus, null, staffID);
        } else {
            return null;
        }

        if (existingSmartCardNumbers.contains(new SmartCardNumber(name))) return null;
        SmartCardNumber smartCardNumber = new SmartCardNumber(name);

        if (checkSmartCardEligibility(staff)) {
            SmartCard smartCard = new SmartCard(employmentStatus, name, smartCardNumber, dob);
            if (staffType.equals("Lecturer")) {
                Lecturer lecturer = (Lecturer) staff;
                lecturer.setSmartCard(smartCard);
            } else {
                Researcher researcher = (Researcher) staff;
                researcher.setSmartCard(smartCard);
            }
        }
        staffs.add(staff);
        return staff;
    }

    public int noOfStaff(String type) {
        //add your code here. Do NOT change the method signature
        return (int) staffs.stream().filter(staff -> staff.getStaffType().equals(type)).count();
    }

    public boolean addData(StaffID id, Set<Module> modules, Set<Name> students) {
        //add your code here. Do NOT change the method signature

        if (modules.size() == 0 || !moduleList.containsAll(modules)) return false;
        if (studentList.size() == 0 || !studentList.containsAll(students)) return false;

        Set<Staff> staff = staffs.stream().filter(stf -> stf.getStaffID().equals(id)).collect(Collectors.toSet());

        if (staff.stream().findFirst().get().getClass().isInstance(Lecturer.class)) {
            Lecturer lecturer = (Lecturer) staff.stream().findFirst().get();
            try {
                if (!lecturer.getTotalNoOfCredits()) lecturer.setModuleSet(modules);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (staff.stream().findFirst().get().getClass().isInstance(Researcher.class)) {
            Researcher researcher = (Researcher) staff.stream().findFirst().get();
            try {
                if (!researcher.getNoOfStudentSupervised()) {
                    researcher.setListOfStudent(students);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public Collection<Staff> getAllStaff() {
        //add your code here. Do NOT change the method signature
        return staffs;
    }

    public void terminateStaff(StaffID id) {
        //add your code here. Do NOT change the method signature
        Set<Staff> staff = staffs.stream().filter(stf -> stf.getStaffID().equals(id)).collect(Collectors.toSet());

        try {
            if (staffs.contains(staff.stream().findFirst().get())) {
                staffs.remove(staff.stream().findFirst().get());
            } else {
                throw new Exception(id + "doesn't exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkSmartCardEligibility(Staff staff) {

        return false;
    }

}
