import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static String[] resizeArray(String[] array, int arraySize){
        String[] temp = new String[arraySize*2];
        for(int i=0; i<arraySize; i++){
            temp[i] = array[i];
        }
        return temp;
    }

    public static void main(String[] args) {
        int mainSize = 1;
        boolean exists = true;
        //lecturer = resizeArray(lecturer, arraySize)
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter College Name: ");
        String collegeName = sc.nextLine();
        String[] lecturers = new String[mainSize];
        String[] committee = new String[mainSize];
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
                    do {
                        exists = false;
                        System.out.print("Enter lecturer name: ");
                        lecturerName = sc.nextLine();
                        for(int i=0; i<mainSize-1; i++){
                            if (lecturers[i].equals(lecturerName)) {
                                System.out.println("This name is already in use.");
                                exists = true;
                                break;
                            }
                        }
                    } while(exists);
                    if (mainSize >= lecturers.length) {
                        lecturers = resizeArray(lecturers, mainSize);
                    }
                    lecturers[mainSize-1] = lecturerName;
                    mainSize++;
                    break;

                case 2:
                    int i = 0;
                    exists = true;
                    while(exists & i <= mainSize) {
                        System.out.print("Enter committee name: ");
                        committeeName = sc.nextLine(); // get from the user new Committe

                        if (Arrays.asList(committee).contains(committeeName)) {
                            System.out.println("This name is already in use.");
                        }
                        else{
                            exists = false;
                            if (mainSize >= committee.length) {
                                committee = resizeArray(committee, mainSize);
                            }
                            committee[mainSize-1] = committeeName;
                            mainSize++;
                        }
                    }
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
