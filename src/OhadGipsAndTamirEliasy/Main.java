package OhadGipsAndTamirEliasy;

import java.util.InputMismatchException;
import java.util.Scanner;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class Main {
    // Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
    public static void main(String[] args) throws DoNotExists {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter College Name: ");
        String collegeName = sc.nextLine();
        College college = new College(collegeName);
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
                            college.addLecturer();
                        } catch (EnumDoNotExists e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            college.addCommittee();
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
                            Department department = college.getDepartment(departmentName);
                            System.out.printf("The average wage for lecturers in this department is: %s\n", college.getSalaryAverageByDepartment(department));
                        } catch (DoNotExists e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 10:
                        college.showDetailsLecturers();
                        break;
                    case 11:
                        college.showDetailsDepartments();
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
                        college.compareCommittees(college.getCommittee(firstCommitteeName), college.getCommittee(secondCommitteeName));
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
                        System.out.println("Invalid input try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input try again");
                sc.nextLine();
            }
        }
        while (option != 0) ;
                System.out.println("You have left the system");

    }
}