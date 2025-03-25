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
    public static void addToArray (String output,String[] array,int arraySize){
        boolean exists = true;
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            exists = false;
            System.out.print(output);
            input = sc.nextLine();
            for(int i=0; i<arraySize-1; i++){
                if (array[i].equals(input)) {
                    System.out.println("This name is already in use.");
                    exists = true;
                    break;
                }
            }
        } while(exists);
        array[arraySize-1] = input;

    }

    public static void main(String[] args) {
        int committeeSize = 1,lecturersSize = 1,DepartmentsSize = 1;
        boolean exists = true;
        //lecturer = resizeArray(lecturer, arraySize)
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter College Name: ");
        String collegeName = sc.nextLine();
        String[] lecturers = new String[lecturersSize];
        String[] committees = new String[committeeSize];
        String[] studyDepartments = new String[DepartmentsSize];
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
                    if (lecturersSize +1 >= lecturers.length) {
                        lecturers = resizeArray(lecturers, lecturersSize);
                    }
                    addToArray("Enter lecturer name: ", lecturers, lecturersSize);
                    lecturersSize++;
                    break;

                case 2:
                    int i = 0;
                    exists = true;
                    while(exists & i <= committeeSize) {
                        System.out.print("Enter committee name: ");
                        committeeName = sc.nextLine(); // get from the user new Committee

                        if (Arrays.asList(committees).contains(committeeName)) {
                            System.out.println("This name is already in use.");
                        }
                        else{
                            exists = false;
                            if (committeeSize >= committees.length) {
                                committees = resizeArray(committees, committeeSize);
                            }
                            committees[committeeSize-1] = committeeName;
                            committeeSize++;
                        }
                    }
                    break;/*
                    if (committeeSize >= committees.length) {
                        committees = resizeArray(committees, committeeSize);
                    }
                    addToArray("Enter committee name: ", committees, committeeSize);
                    committeeSize++;
                    break;*/

                case 3:
                    if (DepartmentsSize +1 >= studyDepartments.length) {
                        studyDepartments = resizeArray(studyDepartments, DepartmentsSize);
                    }
                    addToArray("Enter study department name: ", studyDepartments, DepartmentsSize);
                    DepartmentsSize++;
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
                    System.out.println("Here all the lecturers information:");
                    for(int j =0; j<lecturersSize-1; j++){
                        System.out.println(lecturers[j]);
                    }
                    break;
                case 8:
                    // print all committees names
                    break;
            }
        }while(option != 0);

    }
}
