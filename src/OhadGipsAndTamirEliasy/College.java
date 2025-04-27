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

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
    public Lecturer getLecturer(String lecturerName) {
        for (int i = 0; i < lecturersSize; i++) {
            if (lecturers[i].getName().equals(lecturerName))
                return lecturers[i];
        }
        System.out.printf("%s does not exist\n", lecturerName);
        return null;
    }
    public void addLecturer() {
        String input;boolean exists;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter Lecturer Name: ");
            exists = false;
            input = sc.nextLine();
            for (int i = 0; i < lecturersSize; i++) {
                if (lecturers[i].getName().equals(input)) {
                    System.out.println("\nThis name is already in use. Try a different name");
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

        if (lecturersSize >= lecturers.length)
           lecturers = Committee.resizeLecturers(lecturers);

        lecturers[lecturersSize] = new Lecturer(input, id, kindOfDegree, degreeName, wage);
        lecturersSize++;
    }

    public void addCommittee() {
        boolean exists;
        String chairpersonName, name;
        Lecturer chairperson;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Enter committee name: ");
            exists = false;
            name = sc.nextLine();

            for (int i = 0; i < committeeSize; i++) {
                if (committees[i].getName().equals(name)) {
                    System.out.println("\nThis committee is already exists. Try a different name");
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
             committees = Lecturer.resizeCommittees(committees);
        }

        committees[committeeSize] = new Committee(name, chairperson);
        chairperson.addCommittee(committees[committeeSize]);
        committeeSize++;
        System.out.printf("%s has been created, %s it is chairperson\n",name,chairpersonName);

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
                if (!committee.getChairperson().getName().equals(lecturerName)) {
                    if (committee.addLecturer(lecturer)) {
                        lecturer.addCommittee(committee);
                        System.out.printf("%s has been added to the committee\n", lecturerName);
                    }
                }
                else {
                    System.out.printf("%s is already part of committee as the chairperson\n", lecturerName);
                }
            }
        }
    }
    // set new chairperson - remove committee from chairperson list
    public void setNewChairperson(String committeeName,String lecturerName) {
        Committee committee = getCommittee(committeeName);
        if (committee != null) {
            committee.getChairperson().removeCommittee(committee);
            Lecturer lecturer = getLecturer(lecturerName);
            if (lecturer != null)  {
                if (committee.canBeChairperson(lecturer)) {
                    if (!committee.removeLecturer(lecturer))
                        lecturer.addCommittee(committee);
                    if (committee.setChairperson(lecturer))
                        System.out.printf("%s is now the chairperson of %s\n", lecturerName, committeeName);
                }
                else System.out.printf("%s doesn't meet the requirements\n", lecturerName);

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
                    lecturer.removeCommittee(committee);
                    System.out.printf("%s has been removed from %s\n", lecturerName, committeeName);
                }
            }
        }

    }
    public void resizeDepartments() {
        if (departmentsSize >= departments.length) {
            Department[] temp = new Department[departmentsSize * 2];
            for (int i = 0; i < departmentsSize; i++) {
                temp[i] = departments[i];
            }
            departments = temp;
        }
    }
    public void addDepartment(){
        String departmentName;
        Scanner sc = new Scanner(System.in);
        boolean exists;
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
        } while (exists);

        System.out.print("Enter number of students in the department: ");
        int numOfStudents = sc.nextInt();

        if (departmentsSize >= departments.length) {
            resizeDepartments();
        }
        departments[departmentsSize++] = new Department(departmentName, numOfStudents);
        System.out.printf("%s department was added successfully.\n",departmentName);

    }
    public void addLecturerToDepartment(String lecturerName,String departmentName) {
        Department department = getDepartment(departmentName);
        if (department != null) {
            Lecturer lecturer = getLecturer(lecturerName);
            if (lecturer != null) {
                if (department.addLecturer(lecturer)){

                    System.out.printf("%s has been added to the department\n", lecturerName);
                }
            }
        }
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
    public Department getDepartment(String departmentName) {
        for (int i = 0; i < departmentsSize; i++) {
            if (departmentName.equals(departments[i].getName()))
                return departments[i];
        }
        System.out.printf("%s does not exist\n", departmentName);
        return null;
    }
    public double getSalaryAverageByDepartment(){
        String departmentName;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter department name: ");
        departmentName = sc.nextLine();
        Department department = getDepartment(departmentName);
        if (department == null) {
            System.out.printf("%s does not exist\n", departmentName);
            return -1;
        }
        else{

        int salaryTotal = 0;
        for (int j = 0; j < department.getLecturersSize(); j++) {
                salaryTotal += department.getLecturers()[j].getWage();
            }
        return (double) salaryTotal / department.getLecturersSize();
        }
    }

    public void showDetailsLecturers(){
        System.out.println("Here all the lecturers:");
        for(int i = 0; i < lecturersSize; i++){
            System.out.println(lecturers[i].toString()+"\n");
        }
    }

    public void showDetailsDepartments() {
        System.out.println("Here all the committees:");
        for (int i = 0; i < committeeSize; i++) {
            System.out.println(committees[i].toString()+"\n");
        }
    }

}
