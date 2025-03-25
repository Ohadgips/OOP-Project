import java.util.Scanner;

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
    public static boolean existsInArray (String[] array,int arraySize,String name)
    {
        for (int i=0; i<arraySize-1; i++){
            if (array[i].equals(name)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int committeeSize = 1,lecturersSize = 1,DepartmentsSize = 1;
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
                    if (lecturersSize >= lecturers.length) {
                        lecturers = resizeArray(lecturers, lecturersSize);
                    }
                    addToArray("Enter lecturer name: ", lecturers, lecturersSize);
                    lecturersSize++;
                    break;

                case 2:
                    if (committeeSize >= committees.length) {
                        committees = resizeArray(committees, committeeSize);
                    }
                    addToArray("Enter committee name: ", committees, committeeSize);
                    committeeSize++;
                    break;

                case 3:
                    if (DepartmentsSize >= studyDepartments.length) {
                        studyDepartments = resizeArray(studyDepartments, DepartmentsSize);
                    }
                    addToArray("Enter study department name: ", studyDepartments, DepartmentsSize);
                    DepartmentsSize++;
                    break;
                case 4:
                    System.out.print("Enter lecturer name: ");
                    String input = sc.nextLine();
                    if (existsInArray(lecturers, lecturersSize, input))
                    {
                        System.out.println("This lecturer does not exist");
                    }
                    System.out.print("Enter committee name: ");
                    input = sc.nextLine();
                    if (existsInArray(committees, committeeSize, input))
                    {
                        System.out.println("This committee does not exist");
                    }
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
            }
        }while(option != 0);
        System.out.println("You have left the system");
    }
}