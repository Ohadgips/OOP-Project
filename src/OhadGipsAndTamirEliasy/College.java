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
        committeeSize= 0;
        lecturersSize= 0;
        departmentsSize = 0;
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

        if (lecturersSize >= lecturers.length)
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
            for (int i=0; i<committeeSize; i++) {
                if (committees[i].getName().equals(name)){
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
            chairperson = getLecturer(chairpersonName);
            if(chairperson != null) {
                if (chairperson.getKindOfDegree() == Lecturer.Degree.Master) {
                    exists = true;
                }
                else {
                    System.out.println("\nThis lecturer does not meet the requirements");
                }
            }
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

    public Committee getCommittee(String committeeName) {
        for (int i = 0; i < committeeSize; i++) {
            if (committees[i].getName().equals(committeeName))
                return committees[i];
        }
        System.out.println("Lecturer does not exist");
        return null;
    }

    public boolean addLecturerToCommittee(String committeeName, String lecturerName){
        Committee committee = getCommittee(committeeName);

        if (committee == null) {
            System.out.println("\nThis committee does not exist");
            return false;
        }
        else {
            Lecturer lecturer  = getLecturer(lecturerName);
            if (lecturer == null) {
                System.out.println("\nThis lecturer does not exist");
                return false;
            }
            else {
                committee.addLecturer(getLecturer(lecturerName));
                return true;
            }
        }

    }

    public boolean SetNewChairperson(String newChairpersonName,String Committee){
        Committee committee = getCommittee(Committee);
        if (committee == null) {
            return false;
        }
        Lecturer lecturer  = getLecturer(lecturerName);
    }

    public void addDepartment(){
        String departmentName;
        Scanner sc = new Scanner(System.in);
        boolean exists = true;

        do {
            System.out.print("Enter department name: ");
            departmentName = sc.nextLine();
            exists = false;

            for (int i = 0; i < departmentsSize; i++) {
                if (departments[i].getName().equalsIgnoreCase(departmentName)) {
                    System.out.println("This department already exists. Try a different name.");
                    exists = true;
                    break;
                }
            }
        } while (exists = true);

        System.out.print("Enter number of students in the department: ");
        int numOfStudents = sc.nextInt();

        if (departmentsSize >= departments.length) {
            resizeDepartments();
        }

        departments[departmentsSize++] = new Department(departmentName, numOfStudents);
        System.out.println("Department added successfully.");

    }

    public void resizeDepartments() {
        Department[] temp = new Department[departments.length * 2];
        for (int i = 0; i < departmentsSize; i++) {
            temp[i] = departments[i];
        }
        departments = temp;
    }

    public double salaryAverage(){

        int average = 0;
        if (lecturersSize == 0)
            return 0;

        for (int i = 0; i < lecturersSize; i++) {
            average += lecturers[i].getWage();
        }

        return (double) average / lecturersSize;
    }

    public double getSalaryAverageByDepartment(){
        String UserDepartmentChoice = "";
        Scanner sc = new Scanner(System.in);
        boolean exists = false;

        do {
            System.out.print("Enter department name: ");
            UserDepartmentChoice = sc.nextLine();

            exists = false; // נניח שאין
            for (int i = 0; i < departmentsSize; i++) {
                if (departments[i].getName().equalsIgnoreCase(UserDepartmentChoice)) {
                    exists = true; // מצאנו מחלקה מתאימה
                    break;
                }
            }
            if (!exists) {
                System.out.println("This department does not exist. Try a different name.");
            }
        } while (!exists);

        int lecturerCount = 0;
        int salaryTotal = 0;

        for (int i = 0; i < departmentsSize; i++){
            if (departments[i].getName().equalsIgnoreCase(UserDepartmentChoice)){
                Department wantedDepartment = departments[i]; //by this we get the Deparment
                Lecturer[] lecturersInDept = wantedDepartment.getLecturers();
                for (int j = 0; j < lecturersInDept.length; j++) {
                    if (lecturersInDept[j] != null) {
                        salaryTotal += lecturersInDept[j].getWage();
                        lecturerCount++;
                    }
                }
                break;
            }
        }
        return (double) salaryTotal / lecturerCount;

    }

    public void showDetailsLecturers(){
        for(int i = 0; i < lecturersSize; i++){
            System.out.println(lecturers[i].getName());

            System.out.println("Name: " + lecturers[i].getName());
            System.out.println("ID: " + lecturers[i].getId());
            System.out.println("Degree: " + lecturers[i].getKindOfDegree());
            System.out.println("Degree Name: " + lecturers[i].getNameOfDegree());
            System.out.println("Wage: " + lecturers[i].getWage());

            if (lecturers[i].getDepartment() != null) {
                System.out.println("Department: " + lecturers[i].getDepartment().getName());
            }
        }
    }

    public void showDetailsCommittees(){
        if (committeeSize == 0) {
            System.out.println("No committees available.");
            return;
        }

        for (int i = 0; i < committeeSize; i++) {
            Committee c = committees[i];

            System.out.println("Committee Name: " + c.getName());
            System.out.println("Chairperson: " + c.getChairperson().getName());

            System.out.println("Lecturers in committee:");
            Lecturer[] committeeLecturers = c.getLecturers();
            for (int j = 0; j < committeeLecturers.length; j++) {
                if (committeeLecturers[j] != null) {
                    System.out.println("  - " + committeeLecturers[j].getName());
                }
            }
    }














}
