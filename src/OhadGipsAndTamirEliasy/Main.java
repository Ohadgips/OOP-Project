package OhadGipsAndTamirEliasy;

import java.util.Scanner;
// Submitted By: Tamir Eliasy & Ohad Gips
public class Main {



// Submitted By: Tamir Eliasy & Ohad Gips
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
                    3- Add a study department
                    4- Assign a lecturer to a committee
                    5- Show the average salary of lecturers in college
                    6- Show the average salary of lecturers in a committee
                    7- Show all lecturers information
                    8- Show all committees information""");
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

                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    //print all lecturers names
                    System.out.println("Here all the lecturers information:");
                    for(int j =0; j<lecturersSize-1; j++){
                        System.out.println(lecturers[j]);
                    }
                    break;
                case 8:
                    // print all committees names
                    System.out.println("Here all the committees information:");
                    for(int j =0; j<committeeSize-1; j++){
                        System.out.println(committees[j]);
                    }
                    break;
                default:
                    System.out.println("Invalid input try again");
            }
        }while(option != 0);
        System.out.println("You have left the system");
    }
}