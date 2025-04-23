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
        setCollegeName(collegeName);
        this.lecturers = new Lecturer[1];
        committees = new Committee[1];
        departments = new Department[1];
        committeeSize = 0;
        lecturersSize = 0;
        departmentsSize = 0;
    }
    public College() {
        setCollegeName("test");
        this.lecturers = new Lecturer[]{new Lecturer("rom", 3323, Lecturer.Degree.Doctoral, "Bcs", 4500)
        ,new Lecturer("som", 3893, Lecturer.Degree.Doctoral, "Bc", 4500)
        ,new Lecturer("dom", 3323, Lecturer.Degree.Master, "Bcs", 9000)};
        committees = new Committee[]{new Committee("tech",lecturers[0])};
        departments = new Department[]{new Department("IT",3)};
        committeeSize = 1;
        lecturersSize = 3;
        departmentsSize = 1;
    }



    public Lecturer getLecturer(String lecturerName) {
        for (int i = 0; i < lecturersSize; i++) {
            if (lecturers[i].getName().equals(lecturerName))
                return lecturers[i];
        }
        System.out.println("Lecturer does not exist");
        return null;
    }

    public void addLecturer() {
        boolean exists = true;
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter Lecturer Name: ");
            exists = false;
            input = sc.nextLine();
            for (int i = 0; i < lecturersSize; i++) {
                if (lecturers[i].getName().equals(input)) {
                    System.out.println("\nThis name is already in use.");
                    exists = true;
                    break;
                }
            }
        } while (exists);
        System.out.print("\nEnter lecturer ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("\nEnter kind of degree (Bachelor, Master, Doctoral): ");
        Lecturer.Degree kindOfDegree = Lecturer.Degree.valueOf(sc.nextLine());
        System.out.print("\nEnter name of degree: ");
        String degreeName = sc.nextLine();
        System.out.print("\nEnter lecturer wage: ");
        int wage = sc.nextInt();

        if (lecturersSize + 1 >= lecturers.length) {
            resizeLecturers();
        }

        lecturers[lecturersSize] = new Lecturer(input, id, kindOfDegree, degreeName, wage);
        lecturersSize++;

    }

    public void resizeLecturers() { //need to change string[] to object
        if (lecturersSize >= lecturers.length) {
            Lecturer[] temp = new Lecturer[lecturersSize * 2];
            for (int i = 0; i < lecturersSize; i++) {
                temp[i] = lecturers[i];
            }
            lecturers = temp;
        }
    }

    public void addCommittee() {
        boolean exists = true;
        String chairpersonName, name;
        Lecturer chairperson = null;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Enter committee name: ");
            exists = false;
            name = sc.nextLine();
            for (int i = 0; i < committeeSize; i++) {
                if (committees[i].getName().equals(name)) {
                    System.out.println("\nThis committee is already exists");
                    exists = true;
                    break;
                }
            }
        } while (exists);

        do {
            System.out.print("Enter chairperson name: ");
            exists = true;
            chairpersonName = sc.nextLine();
            chairperson = getLecturer(chairpersonName);
            if (chairperson != null) {
                if (chairperson.getKindOfDegree() == Lecturer.Degree.Doctoral) {
                    exists = false;
                } else {
                    System.out.println("\nThis lecturer does not meet the requirements");
                }
            }
        } while (exists);

        if (committeeSize >= committees.length) {
            resizeCommittees();
        }

        committees[committeeSize] = new Committee(name, chairperson);
        committeeSize++;
        System.out.printf("%s has been created, %s it is chairperson",name,chairpersonName);

    }

    public void resizeCommittees() { //need to change string[] to object
        if (committeeSize >= committees.length) {
            Committee[] temp = new Committee[committeeSize * 2];
            for (int i = 0; i < committeeSize; i++) {
                temp[i] = committees[i];
            }
            committees = temp;
        }
    }

    public Committee getCommittee(String committeeName) {
        for (int i = 0; i < committeeSize; i++) {
            if (committeeName.equals(committees[i].getName()))
                return committees[i];
        }
        System.out.printf("%s does not exist\n", committeeName);
        return null;
    }

    public void addLecturerToCommittee(String committeeName, String lecturerName) {
        Committee committee = getCommittee(committeeName);

        if (committee != null) {
            Lecturer lecturer = getLecturer(lecturerName);
            if (lecturer != null) {
                if (committee.addLecturer(getLecturer(lecturerName)))
                    System.out.printf("%s  has been added to the committee\n", lecturerName);
            }
        }

    }

    public void setNewChairperson(String committeeName,String lecturerName) {
        Committee committee = getCommittee(committeeName);
        if (committee != null) {
            Lecturer lecturer = getLecturer(lecturerName);
            if (lecturer != null)  {

                if (committee.setChairperson(lecturer)) {
                    System.out.printf("%s is now the chairperson of %s\n", lecturerName, committeeName);
                } else {
                    System.out.printf("%s doesn't meet the requirements\n", lecturerName);
                }
            }
        }

    }

    public void removeCommitteeMember(String committeeName,String lecturerName) {
        Committee committee = getCommittee(committeeName);
        if (committee != null)
        {
            Lecturer lecturer = getLecturer(lecturerName);
            if (lecturer != null)  {
                if (committee.removeLecturer(lecturer)) {
                    System.out.printf("%s has been removed from %s\n", lecturerName, committeeName);
                }
            }
        }

    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public void printLecturers()
    {
        for (int i = 0; i < lecturersSize;i++)
        {
            System.out.println(lecturers[i].getName());
        }
    }
    public void printCommitteeLecturers()
    {
        for (int i = 0; i < committeeSize;i++)
        {
            System.out.printf("the chairperson is: %s\n",committees[i].getChairperson().getName());
            for (int j = 0; j < committees[i].getLecturersSize();j++) {
                System.out.println(committees[i].getLecturers()[j].getName());
            }
        }
    }

}

