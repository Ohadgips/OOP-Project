package OhadGipsAndTamirEliasy;


import java.util.Scanner;

public class College {
    private String collegeName;
    private Lecturer[] lecturers;
    private Committee[] committees;
    private Department[] departments;
    private int committeeSize;
    private int lecturersSize;
    private int departmentsSize;

    public College(String collegeName) {
        this.collegeName = collegeName;
        this.lecturers = new Lecturer[1];
        committees = new Committee[1];
        departments = new Department[1];
        committeeSize=1;
        lecturersSize=1;
        departmentsSize = 1;
    }
    public void addLecturer() {
        boolean exists = true;
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter Lecturer Name: ");
            exists = false;
            input = sc.nextLine();
            for(int i=0; i<lecturersSize-1; i++){
                if (lecturers[i].getName().equals(input)) {
                    System.out.println("\nThis name is already in use.");
                    exists = true;
                    break;
                }
            }
        } while(exists);
        sc.nextLine();
        System.out.print("\nEnter lecturer ID: ");
        int id = sc.nextInt();
        System.out.print("\nEnter kind of degree (Bachelor, Master, Doctoral): ");
        Lecturer.Degree kindOfDegree = Lecturer.Degree.valueOf(sc.next());
        System.out.println("\nEnter name of degree: ");
        String degreeName = sc.nextLine();
        System.out.println("\nEnter lecturer wage: ");
        int wage = sc.nextInt();

        if (lecturersSize +1 <= lecturers.length)
        {
            resizeLecturers();
        }

        lecturers[lecturersSize-1] = new Lecturer(input,id,kindOfDegree,degreeName,wage);
        lecturersSize++;

    }

    public void resizeLecturers(){ //need to change string[] to object
        if(lecturersSize >= lecturers.length) {
            Lecturer[] temp = new Lecturer[lecturersSize * 2];
            for (int i = 0; i < lecturersSize; i++) {
                temp[i] = lecturers[i];
            }
            lecturers = temp;
        }
    }
    public void addCommittee() {
        boolean exists = true;
        String chairpersonName,name;
        Lecturer chairperson = null;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Enter committee name: ");
            exists = false;
            name = sc.nextLine();
            for(int i=0; i<departmentsSize-1; i++){
                if (departments[i].getName().equals(name)) {
                    System.out.println("\nThis committee is already exists");
                    exists = true;
                    break;
                }
            }
        } while(exists);

        do {
            System.out.print("Enter chairperson name: ");
            exists = false;
            chairpersonName = sc.nextLine();
            for(int i=0; i<lecturersSize-1; i++){
                if (lecturers[i].getName().equals(chairpersonName)) {
                    if (lecturers[i].getKindOfDegree() == Lecturer.Degree.Master){
                        chairperson = lecturers[i];
                    exists = true;
                }
                    else System.out.println("\nThis lecturer does not meet the requirements");
                    break;
                }
            }
            System.out.println("\nThis lecturer is not in college");
        } while(exists);

        if (committeeSize + 1 <= committees.length)
        {
            resizeCommittees();
        }

        committees[committeeSize-1] = new Committee(name,chairperson);
        committeeSize++;

    }
    public void resizeCommittees(){ //need to change string[] to object
        if(committeeSize >= committees.length) {
            Committee[] temp = new Committee[committeeSize * 2];
            for (int i = 0; i < committeeSize; i++) {
                temp[i] = committees[i];
            }
            committees = temp;
        }
    }

}
