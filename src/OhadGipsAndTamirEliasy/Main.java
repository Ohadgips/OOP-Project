package OhadGipsAndTamirEliasy;

import java.util.Scanner;
// Submitted By: Tamir Eliasy & Ohad Gips
public class Main {

// Submitted By: Tamir Eliasy & Ohad Gips
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double average, averageByDepartment;
        //System.out.print("Enter College Name: ");
       // String collegeName = sc.nextLine();
       // College college = new College(collegeName);
        College college = new College();
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
                    9- Show the average salary of lecturers in a committee
                    10- Show all lecturers information
                    11- Show all committees information""");
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

                    break;
                case 8:
                    average = college.salaryAverage();
                    break;
                case 9:
                    averageByDepartment = college.getSalaryAverageByDepartment();
                    break;
                case 10:
                    college.showDetailsLecturers();
                    break;
                case 11:
                    college.showDetailsCommittees();
                    break;


                default:
                    System.out.println("Invalid input try again");
            }



        }while(option != 0);
        System.out.println("You have left the system");
    }
}