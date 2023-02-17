package main.com.university.ncl;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.util.stream.Collectors;


/**
 * The StaffManager class is the driver class for the university system. It needs to:
 * • Maintain a record of all staff with their staffID
 * • Maintain a record of all Students and Modules in the university
 */
public class StaffManager {

    HashMap<StaffID, Staff> staffs = new HashMap<>();
    Set<Module> moduleSet = new HashSet<>();
    Set<Name> studentNameSet = new HashSet<>();


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
        if (path.isEmpty())
            throw new IllegalArgumentException("Path cannot be empty!");
        try {
            final File importedFile = new File(path);
            final Scanner fileInput = new Scanner(importedFile);
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                String[] lineArray = line.split(","); // split the sentences using String split inbuilt method.
                if (lineArray.length < 4)
                    throw new InputMismatchException("Please check the Module input data." + Arrays.toString(lineArray));

                // validate each data for the input CSC8011, Introduction to Software Development, 1, 10, The last 2 data should always be Integer type n
                // rest all as String
                if (!validateIfProvidedInputIsInteger(lineArray[2]) && !validateIfProvidedInputIsInteger(lineArray[3]))
                    throw new InputMismatchException("Provided entry should be integer " + lineArray[2] + lineArray[3]);

                // adding the Module object to the set of the Modules.
                moduleSet.add(new Module(lineArray[0].trim(), lineArray[1].trim(), Integer.parseInt(lineArray[2].trim()), Integer.parseInt(lineArray[3].trim())));

            }
            fileInput.close(); // closing the Scanner class

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage()); // catching the exception .
        }
        return moduleSet;
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
        if (path.isEmpty())
            throw new IllegalArgumentException("Path cannot be empty!");

        try {
            final File importedFile = new File(path);
            final Scanner fileInput = new Scanner(importedFile);
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                String[] lineArray = line.split(" "); // split the sentences using String split inbuilt method.

                // adding the Name object to the set of the students.
                studentNameSet.add(new Name(lineArray[0], lineArray[1]));
            }
            fileInput.close(); // closing the Scanner class

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage()); // catching the exception .
        }
        return studentNameSet;
    }

    /**
     * @param firstName        , expect first name to be passed e.g. Akash
     * @param lastName         , expect last name to be passed e.g. Gond
     * @param dob              , expect a Date Object to passed as params
     * @param staffType        , expect String as param, it can be either Lecturer or Researcher
     * @param employmentStatus , expect String a param, it can be either permanent or fixed
     * @return Staff Object
     */
    public Staff employStaff(String firstName, String lastName, Date dob, String staffType, String employmentStatus) {
        //add your code here. Do NOT change the method signature

        // check for null possibilities for all the params
        if (firstName == null && lastName == null && dob == null && staffType == null && employmentStatus == null)
            throw new NullPointerException("Please check the parameter provided!");

        // validate provided input is String not int e.g. firstName
        if (validateIfProvidedInputIsInteger(firstName))
            throw new NumberFormatException("Provided input i.e firstName cannot be int. It should be String!");

        // validate provided input is String not int e.g. lastName,
        if (validateIfProvidedInputIsInteger(lastName))
            throw new NumberFormatException("Provided input i.e lastName cannot be int. It should be String!");

        // validate provided input is String not int e.g. employmentStatus
        if (validateIfProvidedInputIsInteger(employmentStatus))
            throw new NumberFormatException("Provided input i.e employmentStatus cannot be int. It should be String!");

        // validate provided input is String not int e.g. employmentStatus
        if (validateIfProvidedInputIsInteger(staffType))
            throw new NumberFormatException("Provided input i.e staffType cannot be int. It should be String!");

        // validate staff type can be only of type Lecturer and researcher
        if (!(staffType.equals(Lecturer.LECTURER) || staffType.equals(Researcher.RESEARCHER)))
            throw new InputMismatchException("Staff type is not matching!");

        // validate employmentStatus can be of type
        if (!(employmentStatus.equals(SmartCard.PERMANENT) || employmentStatus.equals(SmartCard.FIXED)))
            throw new InputMismatchException("Employment type is not matching!");

        // validate age criteria
        if (!isValidAge(dob))
            throw new InputMismatchException("The provided date of birth is not meeting the criteria. Age should be between 22-67.");

        // create a staff object based on the staff type
        final Staff staff = AbstractStaff.getInstance(staffType, firstName, lastName, employmentStatus);

        // Here we will call the criteria before creating the smart card,which will return boolean value
        if (checkSmartCardEligibility(staff, dob)) {
            // check the staff type based on that we creat an object of  staff and cast it, so that we can call the setSmartCard method from the
            // Abstract class i.e. StaffManager where it is defined
            // Also, check if staff is already assigned with smart card or not if issued cannot be re-issued.
            if (staffType.equals(Lecturer.LECTURER)) {
                final Lecturer lecturer = (Lecturer) staff;
                lecturer.setSmartCard(new SmartCard(staff.getStaffEmploymentStatus(), staff.getName(), dob));
            } else {
                final Researcher researcher = (Researcher) staff;
                researcher.setSmartCard(new SmartCard(staff.getStaffEmploymentStatus(), staff.getName(), dob));
            }
        }
        // creat a record of all the staff employed and return it
        staffs.put(staff.getStaffID(), staff);
        return staff;
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

        //check if there is an employee's enrolled or not e.g. Lecturer or Researchers
        if (staffs == null)
            throw new NullPointerException("There is no staff employed currently please employ a staff first.");

        // First check if the id is not null
        if (id == null)
            throw new NullPointerException("Staff Id cannot be null!, Please check your input");

        final Staff staff = getMtchingStaff(id);

        if (staff == null)
            throw new NullPointerException("Cannot find the matching staffID Object");

        if (staff instanceof Lecturer && modules == null && students != null)
            throw new InputMismatchException("Expected modules cannot not be null!");
        else if (staff instanceof Researcher && modules != null && students == null) {
            throw new InputMismatchException("Expected students cannot not be null!");
        }

        if (staff instanceof Lecturer) {
            // check if the size of the modules and student is not empty and  check if modules exist or not.
            if (modules.size() == 0 && !moduleSet.containsAll(modules))
                throw new IllegalArgumentException("Modules cannot be empty. or Modules doesn't match the read input file, please check!");

            final Lecturer lecturer = (Lecturer) staff;
            if (!lecturer.isTotalCreditFulfilled()) {
                lecturer.setModuleSet(modules);
                return true;
            } else {
                throw new InputMismatchException("Lecturer is already assigned with max no. of credits! i.e " + lecturer.getTotalCredits() + " for the course!");
            }
        } else if (staff instanceof Researcher) {
            // check if the size of the modules and student is not empty and check if modules exist or not.
            if (studentNameSet.size() == 0 && !studentNameSet.containsAll(students))
                throw new IllegalArgumentException("Student cannot be empty.");

            final Researcher researcher = (Researcher) staff;
            if (!researcher.isMaxNoOfStudentSupervised()) {
                researcher.setStudentSupervised(students);
                return true;
            } else {
                throw new InputMismatchException("Researcher is already assigned with max no. of students! i.e " + researcher.getStudentSupervised() + " for the course!");
            }
        } else {
            throw new InputMismatchException("Cannot find the matching staffID Object");
        }
    }

    /**
     * @return Collection<Staff></Staff>
     * This method returns all staff that are employed by the university.
     */
    public Collection<Staff> getAllStaff() {
        //add your code here. Do NOT change the method signature
        return staffs.values();
    }

    /**
     * @param type , either can be permanent or fixed type
     * @return total no of staff based on staff type provided
     * This method returns the number of staff of the specified type (a lecturer or a
     * researcher) that are currently employed
     */
    public int noOfStaff(String type) {
        int counter = 0;
        for (Map.Entry<StaffID, Staff> entry : staffs.entrySet()) {
            if (entry.getValue().getStaffType().equals(type)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * @param staffID, expect the StaffID object as param
     *                 This method removes the staff record associated with the given staff id. In
     *                 effect, the staff is leaving the University.
     */
    public void terminateStaff(StaffID staffID) {
        staffs.remove(staffID);
    }

    /**
     * @param staff, expect Staff object as parameter
     * @param dob,   expect Date of birth as parameter
     * @return true or false
     * When issuing a smart card, the following rules must be observed.
     * • A staff must be at least 22 years old and at most 67 (retirement age is 68).
     * • A staff cannot be both a researcher and a lecturer.
     * • A staff cannot be issued with more than one smart-card (i.e. do not try to deal with lost cards!)
     */
    public boolean checkSmartCardEligibility(Staff staff, Date dob) {

        //A staff cannot be issued with more than one smart-card
        if (staff.getSmartCard() != null) return false;

        //A staff must be at least 22 years old and at most 67 (retirement age is 68).
        return isValidAge(dob);

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

    public Staff getMtchingStaff(StaffID staffID) {
        return staffs.get(staffID);
    }

    public boolean validateIfProvidedInputIsInteger(String input) {
        int value = 0;
        try {
            value = Integer.parseInt(input.trim());
            return true;
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.getMessage();
        }
        return false;
    }
}
