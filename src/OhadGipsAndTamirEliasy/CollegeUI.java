package OhadGipsAndTamirEliasy;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CollegeUI {
    private College college;
    private static File file;
    private static Scanner sc;

    public CollegeUI() throws DoNotExists, IOException {

        sc = new Scanner(System.in);
        System.out.print("Enter College Name: ");
        String collegeName = sc.nextLine();
        String fileName = collegeName + "_backup.bin";
        File file = new File(fileName);
        if (file.exists()) {
            try {
                college = College.loadFromFile(fileName);
                System.out.println("Load saved data for this existing college: " + collegeName);
            } catch (Exception e) {
                System.err.println("Error loading file, start a new college");
                college = new College(collegeName); // constructor with name
            }
        } else {
            college = new College(collegeName); // new college
        }
    }
    public void UI() {
        int option = -1;
        do {
            try {
                System.out.println("""
                        Welcome To The System:
                        0- Exit
                        1- Add a lecturer
                        2- Add a committee
                        3- Assign a lecturer to a committee
                        4- Assign a New Chairperson to a committee
                        5- Remove a lecturer from a committee
                        6- Add a study department
                        7- Add a lecturer to a study department
                        8- Show the average salary of all lecturers in college
                        9- Show the average salary of lecturers in a certain department
                        10- Show all lecturers information
                        11- Show all committees information
                        12 - Compare between doctors
                        13 - Compare between committees
                        14 - Duplicate committee""");

                option = sc.nextInt();
                String lecturerName, committeeName, departmentName;
                sc.nextLine();
                switch (option) {
                    case 1:
                        try {
                            if (addLecturerUI())
                            {
                                System.out.println("\n a new lecturer was created\n");
                            }
                            else System.out.println("\n a new lecturer was not created\n");
                        } catch (EnumDoNotExists e) {

                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            if (addCommitteeUI()) {
                                System.out.println("a new committee has been created\n");
                            }
                        } catch (EnumDoNotExists e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("Enter committee name: ");
                        committeeName = sc.nextLine();
                        System.out.print("Enter lecturer name: ");
                        lecturerName = sc.nextLine();

                        try {
                            college.addLecturerToCommittee(committeeName, lecturerName);
                            System.out.printf("%s has been added to the committee\n", lecturerName);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.print("Enter committee name: ");
                        committeeName = sc.nextLine();
                        System.out.print("Enter lecturer name: ");
                        lecturerName = sc.nextLine();
                        try {
                            college.setNewChairperson(committeeName, lecturerName);
                            System.out.printf("%s is now the chairperson of %s\n", lecturerName, committeeName);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.print("Enter committee name: ");
                        committeeName = sc.nextLine();
                        System.out.print("Enter lecturer name: ");
                        lecturerName = sc.nextLine();
                        try {
                            college.removeCommitteeMember(committeeName, lecturerName);
                            System.out.printf("%s has been removed from %s\n", lecturerName, committeeName);
                        } catch (DoNotExists e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        addDepartmentUI();
                        break;
                    case 7:
                        addLecturerToDepartmentUI();
                        break;
                    case 8:
                        System.out.printf("The average wage for lecturers in this college is: %s\n", college.salaryAverage());
                        break;
                    case 9:
                        try {
                            System.out.print("Enter department name: ");
                            departmentName = sc.nextLine();
                            Department department = College.getByName(college.getDepartments(), departmentName);
                            System.out.printf("The average wage for lecturers in this department is: %s\n", college.getSalaryAverageByDepartment(department));
                        } catch (DoNotExists e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 10:
                        System.out.println("Choose sorting criterion:");
                        System.out.println("1 - By Name");
                        System.out.println("2 - By ID");
                        System.out.println("3 - By Name Of Degree");
                        System.out.println("4 - By Wage");
                        System.out.print("Your choice: ");
                        option = sc.nextInt();
                        java.util.Comparator<Lecturer> comparator = switch (option) {
                            case 2 -> Comparator.comparingInt(Lecturer::getId);
                            case 3 -> Comparator.comparing(Lecturer::getNameOfDegree);
                            case 4 -> Comparator.comparingInt(Lecturer::getWage);
                            default -> Comparator.comparing(Lecturer::getName);
                        };
                        System.out.println(college.formatSortedLecturers(comparator));
                        break;
                    case 11:
                        System.out.println("Choose sorting criterion:");
                        System.out.println("1 - By Name");
                        System.out.println("2 - By Chairperson Name");
                        System.out.print("Your choice: ");
                        option = sc.nextInt();
                        java.util.Comparator<Committee> Committeecmp;
                        if (option == 2)
                            Committeecmp = Comparator.comparing(l -> l.getChairperson().getName());
                        else
                            Committeecmp = Comparator.comparing(Committee::getName);

                        System.out.println(college.formatSortedCommittees(Committeecmp));
                        break;
                    case 12:
                        System.out.print("Enter first doctor name: ");
                        String doctor1Name = sc.nextLine();
                        System.out.print("Enter second doctor name: ");
                        String doctor2Name = sc.nextLine();
                        try {
                            System.out.println(college.compareDoctors(college.getDoctor(doctor1Name), college.getDoctor(doctor2Name)));
                        } catch (DoNotExists e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 13:
                        System.out.print("Enter first committee name: ");
                        String firstCommitteeName = sc.nextLine();
                        System.out.print("Enter second committee name: ");
                        String secondCommitteeName = sc.nextLine();
                        try {
                            System.out.println(college.compareCommittees(College.getByName(college.getCommittees(), firstCommitteeName), College.getByName(college.getCommittees(), secondCommitteeName)));
                        } catch (DoNotExists e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 14:
                        System.out.print("Enter committee name to duplicate: ");
                        committeeName = sc.nextLine();
                        try {
                            college.duplicateCommittee(committeeName);
                            System.out.printf("Committee '%s' duplicated successfully as '%s-new'\n", committeeName, committeeName);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        if (option != 0) System.out.println("Invalid input try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input try again");
                sc.nextLine();
            } catch (DoNotExists e) {
                throw new RuntimeException(e);
            }
        }
        while (option != 0);
        System.out.println("You have left the system");
        try {
            college.saveCollege(college.getCollegeName() + "_backup.bin");
            System.out.println("Data saved. Goodbye!");
        } catch (
                IOException e) {
            System.err.println("Failed to save data: " + e.getMessage());
        }
    }

    public boolean addLecturerUI() throws EnumDoNotExists {
        String input;
        do {
            System.out.print("Enter Lecturer Name (to return enter 'return'): ");
            input = sc.nextLine();
            if(input.equals("return")) return false;
            if (college.lecturerExist(input))
            {
                System.out.print("\nThis name is already in use. Try a different name");
            } else break;

        } while (true);
        if (!input.equals("return")) {
            System.out.print("\nEnter lecturer ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("\nEnter kind of degree (Bachelor, Master, Doctoral, Professional): ");
            Lecturer.Degree kindOfDegree;
            try {
                kindOfDegree = Lecturer.Degree.valueOf(sc.nextLine());
            } catch (Exception e) {
                throw new EnumDoNotExists();
            }
            System.out.print("\nEnter name of degree: ");
            String degreeName = sc.nextLine();
            System.out.print("\nEnter lecturer wage: ");
            int wage = sc.nextInt();

            if (Lecturer.Degree.Doctoral.equals(kindOfDegree) || Lecturer.Degree.Professional.equals(kindOfDegree)) {
                String string;
                HashSet<String> articles = new HashSet<>();
                sc.nextLine(); // lastly got int need to clear the input from /n
                do {
                    System.out.print("\nEnter articles name (enter to stop): ");
                    string = sc.nextLine();
                    if (!string.isEmpty()) {
                        articles.add(string);
                    }
                } while (!string.isEmpty());

                if (Lecturer.Degree.Professional.equals(kindOfDegree)) {
                    System.out.print("\nEnter place that gave the degree of this professor: ");
                    String professorName = sc.nextLine();
                    college.addLecturer(new Professor(input, id, kindOfDegree, degreeName, wage, professorName, articles));
                } else
                    college.addLecturer(new Doctor(input, id, kindOfDegree, degreeName, wage, articles));

            } else
                college.addLecturer(new Lecturer(input, id, kindOfDegree, degreeName, wage));
            return true;
        }
        return false;
    }
    public boolean addCommitteeUI() throws EnumDoNotExists, DoNotExists {
        if (!college.HasDoctoralLecturer()) {
            System.out.println("There are no doctoral lecturers in the college to create a committee");
            return false;
        }
        
        String committeeName;
        do {
            System.out.print("Enter committee name (to return enter 'return'): ");
            committeeName = sc.nextLine();
            if (committeeName.equals("return")) return false;
            if (college.committeeExist(committeeName))
                System.out.println("This committee already exists. Try a different name");
            else break;
        } while (true);

        Lecturer chairperson = null;
        do {
            System.out.print("Enter chairperson name: ");
            String chairpersonName = sc.nextLine();
            try {
                chairperson = College.getByName(college.getLecturers(), chairpersonName);
                if (chairperson.getKindOfDegree() == Lecturer.Degree.Doctoral || chairperson.getKindOfDegree() == Lecturer.Degree.Professional) {
                    break;
                } else {
                    System.out.println("This lecturer does not meet the requirements");
                }
            } catch (DoNotExists e) {
                System.out.println(e.getMessage() + ". Try a different name");
            }
        } while (true);

        System.out.print("Enter committee degree type (Bachelor, Master, Doctoral, Professional): ");
        Lecturer.Degree kindOfDegree;
        try {
            kindOfDegree = Lecturer.Degree.valueOf(sc.nextLine());
        } catch (Exception e) {
            throw new EnumDoNotExists();
        }

        Committee newCommittee = new Committee(committeeName, chairperson, kindOfDegree);
        college.addCommittee(newCommittee);
        chairperson.addCommittee(newCommittee);
        return true;
    }

    public void addDepartmentUI() {
        String departmentName;
        boolean exists;
        do {
            System.out.print("Enter department name (to return enter 'return'): ");
            departmentName = sc.nextLine();
            exists = false;
            if (!departmentName.equals("return")) {
                if (college.departmentExists(departmentName)) {
                    System.out.println("This department already exists. Try a different name.");
                    exists = true;
                }
            }
        } while (exists);

        if (!departmentName.equals("return")) {
            System.out.print("Enter number of students in the department: ");
            int numOfStudents = sc.nextInt();
            sc.nextLine();

            college.addDepartment(new Department(departmentName, numOfStudents));
            System.out.printf("%s department was added successfully.\n", departmentName);
        }
    }

    public void addLecturerToDepartmentUI() {
        System.out.print("Enter lecturer name: ");
        String lecturerName = sc.nextLine();
        System.out.print("Enter department name: ");
        String departmentName = sc.nextLine();

        try {
            Lecturer lecturer = College.getByName(college.getLecturers(), lecturerName);
            if (lecturer.getDepartment() != null) {
                if (lecturer.getDepartment().getName().equalsIgnoreCase(departmentName)) {
                    System.out.printf("%s is already part of %s\n", lecturerName, departmentName);
                    return;
                } else {
                    String input = "";
                    do {
                        System.out.printf("%s is already part of department. Do you want to change his department? (yes/no): ", lecturerName);
                        input = sc.nextLine();
                    } while (!(input.equals("yes") || input.equals("no")));

                    if (!input.equals("yes")) {
                        System.out.println("Lecturer has not been added to the department");
                        return;
                    }
                }
            }

            college.addLecturerToDepartment(lecturerName, departmentName);
            System.out.printf("%s has been added to the department\n", lecturerName);
        } catch (DoNotExists e) {
            System.out.println(e.getMessage());
        }
    }
}




