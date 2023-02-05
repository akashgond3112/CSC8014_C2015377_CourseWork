package uk.ac.university.ncl;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.util.stream.Collectors;


public class StaffManager {

    private static HashSet<Staff> staffs = new HashSet<>();
    private static HashSet<StaffID> existingStaffIDs = new HashSet<>();
    private static HashSet<SmartCardNumber> existingSmartCardNumbers = new HashSet<>();
    Set<Module> moduleList = new HashSet<>();
    Set<Name> studentList = new HashSet<>();


    /**
     * @param path , expect the file path to be provided
     * @return Set<Module></Module>
     * This method should allow modules information to be read from a pre-defined
     * data file (modules.txt, where path is the path to this file) and stored in
     * a set of modules. The modules.TXT file contains one data entry per line with
     * fields separated by a comma e.g. CSC8014, Software Development Advanced
     * Techniques, 2, 10
     */
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

    /**
     * @param path , expect the file path to be provided
     * @return Set<Name></Name>
     * This method should allow students information to be read from a pre-defined
     * data file (Students.txt where path is the path to this file) and stored in a
     * set of names. The Students.TXT file contains one data entry per line with
     * fields separated by a space e.g. Charlie Chaplin
     */
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

    /**
     * @param firstName        , expect first name to be passed e.g Akash
     * @param lastName         , expect last name to be passed e.g Gond
     * @param dob              , expect a Date Object to passed as params
     * @param staffType        , expect String as param, it can be either Lecturer or Researcher
     * @param employmentStatus , expect String a param, it can be either permanent or fixed
     * @return Staff Object
     */
    public Staff employStaff(String firstName, String lastName, Date dob, String staffType, String employmentStatus) {
        //add your code here. Do NOT change the method signature
        Staff staff = null;

        Name name = new Name(firstName, lastName);

        StaffID staffID = new StaffID();
        if (existingStaffIDs.contains(staffID)) return null;


        if (staffType.equals("Lecturer")) {
            staff = new Lecturer(new Name(firstName, lastName), employmentStatus, null, staffID);
        } else if (staffType.equals("Researcher")) {
            staff = new Researcher(new Name(firstName, lastName), employmentStatus, null, staffID);
        } else {
            return null;
        }

        SmartCardNumber smartCardNumber = new SmartCardNumber(name);
        if (existingSmartCardNumbers.contains(smartCardNumber)) return null;


        if (checkSmartCardEligibility(staff, dob)) {
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

    /**
     * @param type , either can be permanent or fixed type
     * @return total no of staff based on staff type provided
     * This method returns the number of staff of the specified type (a lecturer or a
     * researcher) that are currently employed
     */
    public int noOfStaff(String type) {
        return (int) staffs.stream().filter(staff -> staff.getStaffType().equals(type)).count();
    }

    /**
     * @param id       , expect StaffID object to be provided
     * @param modules  , Set of the modules which is to be assigned to the above given StaffId
     * @param students , Set of all the student who are going to be supervised by the above given StaffId
     * @return true or false
     * This method adds either a set of modules or a set of students to the staff
     * depending on their type. You need to make sure that modules and students are
     * valid before assigning them to the staff (This can be done be comparing the set
     * against the records of existing students and modules).
     */
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

    /**
     * @return Collection<Staff></Staff>
     * This method returns all staff that are employed by the university.
     */
    public Collection<Staff> getAllStaff() {
        //add your code here. Do NOT change the method signature
        return staffs;
    }

    /**
     * @param id, expect the StaffID object as param
     *  This method removes the staff record associated with the given staff id. In
     *  effect, the staff is leaving the University.
     */
    public void terminateStaff(StaffID id) {
        Set<Staff> staff = staffs.stream().filter(stf -> stf.getStaffID().equals(id)).collect(Collectors.toSet());

        Staff stf = staff.stream().findFirst().get();
        try {
            if (staffs.contains(stf)) {
                staffs.remove(stf);
            } else {
                throw new Exception(id + "doesn't exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param staff, expect Staff object as parameter
     * @param dob,   expect Date of birth as parameter
     * @return true or false
     * When issuing a smart card, the following rules must be observed.
     * • A staff must be at least 22 years old and at most 67 (retirement age is 68).
     * • A staff cannot be both a researcher and a lecturer.
     * • A staff cannot be issued with more than one smartcard (i.e. do not try to deal with lost cards!)
     */
    public boolean checkSmartCardEligibility(Staff staff, Date dob) {

        //A staff cannot be issued with more than one smart-card
        if (staff.getSmartCard() != null) return false;

        //A staff must be at least 22 years old and at most 67 (retirement age is 68).
        if (!isValidAge(dob)) return false;

        // TODO: Need to implement this conditions , need to check the below criteria
        //A staff cannot be both a researcher and a lecturer.
        return true;
    }

    /**
     * @param date , expect Date object as params
     * @return true or false based on the condition provided
     * A staff must be at least 22 years old and at most 67 (retirement age is 68).
     */
    public static boolean isValidAge(Date date) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(date);

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age >= 22 && age <= 67;
    }

}
