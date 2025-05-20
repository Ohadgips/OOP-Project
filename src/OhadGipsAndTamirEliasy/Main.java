package OhadGipsAndTamirEliasy;

import javax.print.Doc;
import java.util.Scanner;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class Main {
    // Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter College Name: ");
        String collegeName = sc.nextLine();
        College college = new College(collegeName);
        int option;
        do{
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
                    12 - Compare between professors
                    13 - Compare between committees
                    14 - Duplicate committee""");
            option = sc.nextInt();
            String lecturerName, committeeName,departmentName;
            sc.nextLine();
            switch(option){
                case 1:
                    college.addLecturer();
                    break;

                case 2:
                    college.addCommittee();
                    break;
                case 3:
                    System.out.print("Enter committee name: ");
                    committeeName = sc.nextLine();
                    System.out.print("Enter lecturer name: ");
                    lecturerName = sc.nextLine();
                    college.addLecturerToCommittee(committeeName, lecturerName);
                    break;
                case 4:
                    System.out.print("Enter committee name: ");
                    committeeName = sc.nextLine();
                    System.out.print("Enter lecturer name: ");
                    lecturerName = sc.nextLine();
                    college.setNewChairperson(committeeName, lecturerName);
                    break;
                case 5:
                    System.out.print("Enter committee name: ");
                    committeeName = sc.nextLine();
                    System.out.print("Enter lecturer name: ");
                    lecturerName = sc.nextLine();
                    college.removeCommitteeMember(committeeName,lecturerName);
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
                    System.out.printf("The average wage for lecturers in this college is: %s\n",college.salaryAverage());
                    break;
                case 9:
                    System.out.printf("The average wage for lecturers in this department is: %s\n",college.getSalaryAverageByDepartment());
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
                    college.CompareDoctors((Doctor) college.getLecturer(doctor1Name),(Doctor) college.getLecturer(doctor2Name));
                    break;
                case 13:
                    break;
                case 14:
                    break;
                default:
                    System.out.println("Invalid input try again");
            }
        }while(option != 0);
        System.out.println("You have left the system");
    }
}