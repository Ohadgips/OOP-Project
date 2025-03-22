import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int arraySize =1;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter College Name: ");
        String collegeName = sc.nextLine();
        String[] lecturers = new String[arraySize];
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
                    System.out.print("Enter lecturer name: ");
                    lecturerName = sc.nextLine();
                    break;
                case 2:
                    System.out.print("Enter committee name: ");
                    committeeName = sc.nextLine();
                    break;
                case 3:
                    System.out.print("Enter study department name: ");
                    departmentName = sc.nextLine();
                    break;
                case 4:

                    System.out.print("Enter lecturer name: ");
                    lecturerName = sc.nextLine();
                    System.out.print("Enter committee name: ");
                    committeeName = sc.nextLine();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    //print all lecturers names
                    break;
                case 8:
                    // print all committees names
                    break;
            }
        }while(option != 0);

    }
}