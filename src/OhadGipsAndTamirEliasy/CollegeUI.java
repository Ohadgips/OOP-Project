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
                            if(college.addCommittee())
                            {
                                System.out.printf("a new committee has been created\n");
                            } else
                                System.out.println("There are no doctoral lecturers in the college to create a committee");

                        } catch (EnumDoNotExists e) {
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
                        } catch (CommitteeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.print("Enter committee name: ");
                        committeeName = sc.nextLine();
                        System.out.print("Enter lecturer name: ");
                        lecturerName = sc.nextLine();
                        college.removeCommitteeMember(committeeName, lecturerName);
                        break;
                    case 6:
                        college.addDepartment();
                        break;
                    case 7:
                        System.out.print("Enter department name: ");
                        departmentName = sc.nextLine();
                        System.out.print("Enter lecturer name: ");
                        lecturerName = sc.nextLine();
                        college.addLecturerToDepartment(lecturerName, departmentName);
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
                        college.showDetailsLecturers(comparator);
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


                        college.showDetailsCommittees(Committeecmp);
                        break;
                    case 12:
                        System.out.print("Enter first doctor name: ");
                        String doctor1Name = sc.nextLine();
                        System.out.print("Enter second doctor name: ");
                        String doctor2Name = sc.nextLine();
                        try {
                            college.compareDoctors(college.getDoctor(doctor1Name), college.getDoctor(doctor2Name));
                        } catch (DoNotExists e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 13:
                        System.out.print("Enter first committee name: ");
                        String firstCommitteeName = sc.nextLine();
                        System.out.print("Enter second committee name: ");
                        String secondCommitteeName = sc.nextLine();

                        college.compareCommittees(College.getByName(college.getCommittees(), firstCommitteeName), College.getByName(college.getCommittees(), secondCommitteeName));
                        break;
                    case 14:
                        System.out.print("Enter committee name to duplicate: ");
                        committeeName = sc.nextLine();
                        try {
                            college.duplicateCommittee(committeeName);
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
}



