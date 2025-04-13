package OhadGipsAndTamirEliasy;


import java.util.Scanner;

public class ControlPanel {
    private Lecturer[] lecturers;
    private Committee[] committees;
    private Department[] departments;
    private int committeeSize1;
    private int lecturersSize;
    private int DepartmentsSize;

    public void addToArray (String output,String[] array,int arraySize){ //need to change string[] to object
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

    public boolean existsInArray (String[] array,int arraySize,String name) //need to change string[] to object
    {
        for (int i=0; i<arraySize-1; i++){
            if (array[i].equals(name)) {
                return false;
            }
        }
        return true;
    }
    public String[] resizeArray(String[] array, int arraySize){ //need to change string[] to object
        if(arraySize >= array.length) {
            String[] temp = new String[arraySize * 2];
            for (int i = 0; i < arraySize; i++) {
                temp[i] = array[i];
            }
            return temp;
        }
        else {return array;}
    }
}
