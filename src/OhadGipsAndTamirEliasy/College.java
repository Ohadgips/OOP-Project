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

    public String[] resizeArray(String[] array, int newSize) {
        String[] temp = new String[newSize * 2];
        for (int i = 0; i < newSize; i++) {
            temp[i] = array[i];
        }
        return temp;
    }

    public Lecturer getLecturer(String lecturerName) throws DoNotExists {
        for (int i = 0; i < lecturersSize; i++) {
            if (lecturers[i].getName().equals(lecturerName))
                return lecturers[i];
        }
        throw new DoNotExists(lecturerName);
    }

    public void addLecturer() throws EnumDoNotExists {
        String input;
        boolean exists;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter Lecturer Name (to return enter 'return'): ");
            exists = false;
            input = sc.nextLine();
            if (!input.equals("return")) {
                for (int i = 0; i < lecturersSize; i++) {
                    if (lecturers[i].getName().equals(input)) {
                        System.out.println("\nThis name is already in use. Try a different name");
                        exists = true;
                        break;
                    }
                }
            }
        } while (exists);
        if (!input.equals("return")) {
            System.out.print("\nEnter lecturer ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("\nEnter kind of degree (Bachelor, Master, Doctoral, Professional): ");
            Lecturer.Degree kindOfDegree;
            try {
                kindOfDegree = Lecturer.Degree.valueOf(sc.nextLine());
            } catch (Exception e) {
                throw new EnumDoNotExists();
            }
            System.out.print("\nEnter name of degree: ");
            String degreeName = sc.nextLine();
            System.out.print("\nEnter lecturer wage: ");
            int wage = sc.nextInt();

            if (lecturersSize >= lecturers.length)
                lecturers = Committee.resizeLecturers(lecturers);

            if (Lecturer.Degree.Doctoral.equals(kindOfDegree) || Lecturer.Degree.Professional.equals(kindOfDegree)) {
                String string;
                String[] articles = new String[1];
                int articlesSize = 0;
                sc.nextLine(); // lastly got int need to clear the input from /n
                do {
                    System.out.print("\nEnter articles name (enter to stop): ");
                    string = sc.nextLine();
                    if (!string.isEmpty()) {
                        if (articlesSize >= articles.length) {
                            articles = resizeArray(articles, articlesSize);
                        }
                        articles[articlesSize] = string;
                        articlesSize++;
                    }
                } while (!string.isEmpty());

                if (Lecturer.Degree.Professional.equals(kindOfDegree)) {
                    System.out.print("\nEnter place that gave the degree of this professor: ");
                    String professorName = sc.nextLine();
                    lecturers[lecturersSize] = new Professor(input, id, kindOfDegree, degreeName, wage, professorName, articles, articlesSize);
                } else
                    lecturers[lecturersSize] = new Doctor(input, id, kindOfDegree, degreeName, wage, articles, articlesSize);

            } else
                lecturers[lecturersSize] = new Lecturer(input, id, kindOfDegree, degreeName, wage);
            lecturersSize++;
        }
    }

    public void addCommittee() {
        // without a doctoral lecturer you cannot create a committee
        if (HasDoctoralLecturer()) {
            boolean exists;
            String chairpersonName, name;
            Lecturer chairperson = null;
            Scanner sc = new Scanner(System.in);

            do {
                System.out.print("Enter committee name (to return enter 'return'): ");
                exists = false;
                name = sc.nextLine();
                if (!name.equals("return")) {
                    for (int i = 0; i < committeeSize; i++) {
                        if (committees[i].getName().equals(name)) {
                            System.out.println("\nThis committee is already exists. Try a different name");
                            exists = true;
                            break;
                        }
                    }
                }
            } while (exists);
            if (!name.equals("return")) {
                do {
                    System.out.print("Enter chairperson name (to return enter 'return'): ");
                    exists = true;
                    chairpersonName = sc.nextLine();
                    try {
                        chairperson = getLecturer(chairpersonName);
                        if (chairperson.getKindOfDegree() == Lecturer.Degree.Doctoral || chairperson.getKindOfDegree() == Lecturer.Degree.Professional) {
                            exists = false;
                        } else {
                            System.out.println("\nThis lecturer does not meet the requirements");
                        }
                    } catch (DoNotExists e) {
                        System.out.println(e.getMessage()+". Try a different name");
                    }
                } while (exists);
                if (committeeSize >= committees.length) {
                    committees = Lecturer.resizeCommittees(committees);
                }
                committees[committeeSize] = new Committee(name, chairperson);
                chairperson.addCommittee(committees[committeeSize]);
                committeeSize++;
                System.out.printf("%s has been created, %s it is chairperson\n", name, chairpersonName);
            }
        } else System.out.println("There are no doctoral lecturers in the college to create a committee");

    }

    public Committee getCommittee(String committeeName) throws DoNotExists {
        for (int i = 0; i < committeeSize; i++) {
            if (committeeName.equals(committees[i].getName()))
                return committees[i];
        }
        throw new DoNotExists(committeeName);
    }

    public void addLecturerToCommittee(String committeeName, String lecturerName) throws AlreadyInCommitteeException {
        try {
            Committee committee = getCommittee(committeeName);
            try {
                Lecturer lecturer = getLecturer(lecturerName);
                    if (!committee.getChairperson().getName().equals(lecturerName)) {
                        committee.addLecturer(lecturer);
                        lecturer.addCommittee(committee);
                        System.out.printf("%s has been added to the committee\n", lecturerName);
                    }
                    else throw new AlreadyInCommitteeException(" is already part of committee as the chairperson",lecturerName);
            }
            catch (DoNotExists e){
                System.out.println(e.getMessage()+". Try again");
            }
        }
        catch (DoNotExists e) {
            System.out.println(e.getMessage()+". Try again");
        }
    }

    // set new chairperson - remove committee from chairperson list
    public void setNewChairperson(String committeeName, String lecturerName) throws CommitteeException {
        try {
            Committee committee = getCommittee(committeeName);
            try {
                Lecturer lecturer = getLecturer(lecturerName);
                committee.getChairperson().removeCommittee(committee);
                if (committee.canBeChairperson(lecturer)) {
                    committee.removeLecturer(lecturer);
                    lecturer.addCommittee(committee);
                    if (committee.setChairperson(lecturer))
                        System.out.printf("%s is now the chairperson of %s\n", lecturerName, committeeName);
                }
                else System.out.printf("%s doesn't meet the requirements\n", lecturerName);
            }
            catch (DoNotExists e){
                System.out.println(e.getMessage()+". Try again");
            }
        }
        catch (DoNotExists e) {
            System.out.println(e.getMessage()+". Try again");
        }
    }

    public boolean HasDoctoralLecturer() {
        for (int i = 0; i < lecturersSize; i++) {
            if (lecturers[i].getKindOfDegree() == Lecturer.Degree.Doctoral || lecturers[i].getKindOfDegree() == Lecturer.Degree.Professional)
                return true;
        }
        return false;
    }

    public void removeCommitteeMember(String committeeName, String lecturerName) {
        try{
            Committee committee = getCommittee(committeeName);
            try {
                Lecturer lecturer = getLecturer(lecturerName);
                committee.removeLecturer(lecturer);
                lecturer.removeCommittee(committee);
                System.out.printf("%s has been removed from %s\n", lecturerName, committeeName);
            } catch (DoNotExists e){
                System.out.println(e.getMessage()+". Try again");
            }
        } catch (DoNotExists e){
            System.out.println(e.getMessage()+". Try again");
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

    public Department getDepartment(String departmentName) throws DoNotExists {
        for (int i = 0; i < departmentsSize; i++) {
            if (departmentName.equals(departments[i].getName()))
                return departments[i];
        }
        throw new DoNotExists(departmentName);
    }

    public void addDepartment() {
        String departmentName;
        Scanner sc = new Scanner(System.in);
        boolean exists;
        do {
            System.out.print("Enter department name (to return enter 'return'): ");
            departmentName = sc.nextLine();
            exists = false;
            if (!departmentName.equals("return")) {
                for (int i = 0; i < departmentsSize; i++) {
                    if (departments[i].getName().equalsIgnoreCase(departmentName)) {
                        System.out.println("This department already exists. Try a different name.");
                        exists = true;
                        break;
                    }
                }
            }
        } while (exists);
        if (!departmentName.equals("return")) {
            System.out.print("Enter number of students in the department: ");
            int numOfStudents = sc.nextInt();

            if (departmentsSize >= departments.length) {
                resizeDepartments();
            }

            departments[departmentsSize++] = new Department(departmentName, numOfStudents);
            System.out.printf("%s department was added successfully.\n", departmentName);
        }

    }

    public void addLecturerToDepartment(String lecturerName, String departmentName) {
        try {
            Department department = getDepartment(departmentName);
            try {
                Lecturer lecturer = getLecturer(lecturerName);
                String input = "";
                if (lecturer.getDepartment() != null) {
                    do {
                        System.out.printf("%s is already part of department. do you want to change his department? (yes / no): ", lecturerName);
                        Scanner sc = new Scanner(System.in);
                        input = sc.nextLine();
                    } while (!(input.equals("yes") || input.equals("no")));
                    System.out.println(input);
                    if (input.equals("yes")) {
                        lecturer.getDepartment().removeLecturer(lecturer);
                    } else System.out.println("lecturer has not been added to the department");
                }
                if (lecturer.getDepartment() == null || input.equals("yes")) {
                    department.addLecturer(lecturer);
                    lecturer.setDepartment(department);
                    System.out.printf("%s has been added to the department\n", lecturerName);
                }

            } catch (DoNotExists e) {System.out.println(e.getMessage()+". Try again");}
        }
        catch (DoNotExists e) {System.out.println(e.getMessage()+". Try again");}

    }

    public double salaryAverage() {
        int average = 0;
        if (lecturersSize == 0)
            return 0;
        for (int i = 0; i < lecturersSize; i++) {
            average += lecturers[i].getWage();
        }
        return (double) average / lecturersSize;
    }

    public double getSalaryAverageByDepartment(Department department) throws DoNotExists {
        int salaryTotal = 0;
        for (int j = 0; j < department.getLecturersSize(); j++) {
            salaryTotal += department.getLecturers()[j].getWage();
        }
        return (double) salaryTotal / department.getLecturersSize();
    }

    public void showDetailsLecturers() {
        System.out.println("Here all the lecturers:");
        for (int i = 0; i < lecturersSize; i++) {
            System.out.println(lecturers[i].toString() + "\n");
        }
    }

    public void showDetailsDepartments() {
        System.out.println("Here all the committees:");
        for (int i = 0; i < committeeSize; i++) {
            System.out.println(committees[i].toString() + "\n");
        }
    }

    public Doctor getDoctor(String doctorName) throws DoNotExists {
        Lecturer lecturer = getLecturer(doctorName);
        if (!(lecturer.getKindOfDegree().equals(Lecturer.Degree.Doctoral) || lecturer.getKindOfDegree().equals(Lecturer.Degree.Professional)))
            throw new NotADoctor(doctorName);
        else
            return (Doctor) lecturer;
    }

    public void compareDoctors(Doctor doctor1, Doctor doctor2) {
        if (doctor1.getArticlesSize() >= doctor2.getArticlesSize()) {
            if (doctor1.getArticlesSize() == doctor2.getArticlesSize()) {
                System.out.printf("%s and %s have the same number of articles\n",doctor1.getName(),doctor2.getName());

            }
            else {
                System.out.printf("%s has more articles in compare to %s\n",doctor1.getName(),doctor2.getName());
            }
        }
        else {
            System.out.printf("%s has less articles in compare to %s\n",doctor1.getName(),doctor2.getName());
        }
    }

    public void compareCommittees(Committee committee1, Committee committee2) {
        if (committee1.getLecturersSize() >= committee2.getLecturersSize()) {
            if (committee2.getLecturersSize() == committee1.getLecturersSize()) {
                System.out.printf("%s and %s has the same amount of lecturers\n",committee1.getName(),committee2.getName());
            }
            else
                System.out.printf("%s has more lecturers in compare to %s\n",committee1.getName(),committee2.getName());
        }
        else {
            System.out.printf("%s has more lecturers in compare to %s\n",committee2.getName(),committee1.getName());
        }
        int committee1Articles = committee1.getArticlesAmount();
        int committee2Articles = committee2.getArticlesAmount();

        if (committee1Articles >= committee2Articles) {
            if (committee2.getLecturersSize() == committee1.getLecturersSize()) {
                System.out.printf("%s lecturers and %s lecturers has the same amount of articles\n",committee1.getName(),committee2.getName());
            }
            else
                System.out.printf("%s has more articles overall in compare to %s\n",committee1.getName(),committee2.getName());
        }
        else
            System.out.printf("%s has more articles overall in compare to %s\n",committee2.getName(),committee1.getName());

    }

    public String toString(){
        String details = "College name is: " + collegeName +"\nCollege lecturers details:\n ";
        for (int i = 0; i < lecturersSize; i++) details += lecturers[i].toString() + "\n";

        details += "College Committees details:\n ";

        for (int i = 0; i < committeeSize; i++) details += committees[i].toString() + "\n";

        details += "College Departments details:\n ";

        for (int i = 0; i < departmentsSize; i++) details += departments[i].toString() + "\n";

        return details;
    }
}
