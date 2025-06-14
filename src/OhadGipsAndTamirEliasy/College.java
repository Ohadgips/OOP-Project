package OhadGipsAndTamirEliasy;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class College implements Serializable {
    private String collegeName;
    private final ArrayList<Lecturer> lecturers;
    private final ArrayList<Committee>committees;
    private final ArrayList<Department> departments;

    public College(String collegeName) {
        setCollegeName(collegeName);
        this.lecturers = new ArrayList<>();
        this.committees = new ArrayList<>();
        this.departments = new ArrayList<>();
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public ArrayList<Committee> getCommittees() {
        return committees;
    }
    public ArrayList<Department> getDepartments() {
        return departments;
    }

    // generic func that get an obj by name from arraylist
    public static <T extends HasName> T getByName(ArrayList<T> list,String name) throws DoNotExists {
        for (T item : list) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new DoNotExists(name);
    }
    public static  <T extends HasName> void addObject(ArrayList<T> list, T object,String exception) throws AlreadyInException{
        if (!list.contains(object)) {
            list.add(object);
        }
        else throw new AlreadyInException(exception);
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
                for (Lecturer lecturer : lecturers) {
                    if (lecturer.getName().equals(input)) {
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

            if (Lecturer.Degree.Doctoral.equals(kindOfDegree) || Lecturer.Degree.Professional.equals(kindOfDegree)) {
                String string;
                ArrayList<String> articles = new ArrayList<>();
                sc.nextLine(); // lastly got int need to clear the input from /n
                do {
                    System.out.print("\nEnter articles name (enter to stop): ");
                    string = sc.nextLine();
                    if (!string.isEmpty()) {
                        articles.add(string);
                    }
                } while (!string.isEmpty());

                if (Lecturer.Degree.Professional.equals(kindOfDegree)) {
                    System.out.print("\nEnter place that gave the degree of this professor: ");
                    String professorName = sc.nextLine();
                    lecturers.add(new Professor(input, id, kindOfDegree, degreeName, wage, professorName, articles));
                } else
                    lecturers.add(new Doctor(input, id, kindOfDegree, degreeName, wage, articles));

            } else
                lecturers.add(new Lecturer(input, id, kindOfDegree, degreeName, wage));
        }
    }

    public void addCommittee() throws EnumDoNotExists {
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
                    for (Committee committee : committees) {
                        if (committee.getName().equals(name)) {
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
                        chairperson = getByName(lecturers,chairpersonName);
                        if (chairperson.getKindOfDegree() == Lecturer.Degree.Doctoral || chairperson.getKindOfDegree() == Lecturer.Degree.Professional) {
                            exists = false;
                        } else {
                            System.out.println("\nThis lecturer does not meet the requirements");
                        }
                    } catch (DoNotExists e) {
                        System.out.println(e.getMessage()+". Try a different name");
                    }
                } while (exists);
                System.out.print("Enter committee degree type (Bachelor, Master, Doctoral, Professional): ");

                Lecturer.Degree kindOfDegree;
                try {
                    kindOfDegree = Lecturer.Degree.valueOf(sc.nextLine());
                    Committee newCommittee = new Committee(name, chairperson,kindOfDegree);
                    committees.add(newCommittee);
                    chairperson.getCommittees().add(newCommittee);
                    System.out.printf("%s has been created, %s it is chairperson\n", name, chairpersonName);
                } catch (Exception e) {
                    throw new EnumDoNotExists();
                }
            }
        } else System.out.println("There are no doctoral lecturers in the college to create a committee");

    }

    public void addLecturerToCommittee(String committeeName, String lecturerName) throws AlreadyInException,NotRightDegreeType {
        try {
            Committee committee = getByName(committees,committeeName);
            try {
                Lecturer lecturer = getByName(lecturers,lecturerName);
                    if (!committee.getChairperson().getName().equals(lecturerName)) {
                        if (lecturer.getKindOfDegree() == committee.getDegreeType()) {
                            addObject(lecturer.getCommittees(),committee,lecturer.getName());
                            addObject(committee.getLecturers(),lecturer,lecturer.getName());
                            System.out.printf("%s has been added to the committee\n", lecturerName);
                        }
                        else throw new NotRightDegreeType();
                    } 
                    else throw new AlreadyInException(" is already part of committee as the chairperson",lecturerName);
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
            Committee committee = getByName(committees,committeeName);
            try {
                Lecturer lecturer = getByName(lecturers,lecturerName);
                committee.getChairperson().getCommittees().remove(committee);
                if (committee.canBeChairperson(lecturer)) {
                    committee.getLecturers().remove(lecturer);
                    try {
                        addObject(lecturer.getCommittees(), committee, lecturer.getName());
                    } catch (AlreadyInException e) {
                        System.out.println(e.getMessage());
                    }
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
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getKindOfDegree() == Lecturer.Degree.Doctoral || lecturer.getKindOfDegree() == Lecturer.Degree.Professional)
                return true;
        }
        return false;
    }

    public void removeCommitteeMember(String committeeName, String lecturerName) {
        try{
            Committee committee = getByName(committees,committeeName);
                Lecturer lecturer = getByName(lecturers,lecturerName);
                committee.getLecturers().remove(lecturer);
                lecturer.getCommittees().remove(committee);
                System.out.printf("%s has been removed from %s\n", lecturerName, committeeName);
        } catch (DoNotExists e){
            System.out.println(e.getMessage()+". Try again");
        }
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
                for (Department department : departments) {
                    if (department.getName().equalsIgnoreCase(departmentName)) {
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

            departments.add(new Department(departmentName, numOfStudents));
            System.out.printf("%s department was added successfully.\n", departmentName);
        }

    }

    public void addLecturerToDepartment(String lecturerName, String departmentName) {
        try {
                Department department = getByName(departments,departmentName);
                Lecturer lecturer = getByName(lecturers,lecturerName);
                String input = "";
                if (lecturer.getDepartment() != null) {
                    if (lecturer.getDepartment().getName().equalsIgnoreCase(departmentName))
                        System.out.printf("%s is already part of %s\n", lecturerName, departmentName);
                    else {
                        do {
                            System.out.printf("%s is already part of department. do you want to change his department? (yes / no): ", lecturerName);
                            Scanner sc = new Scanner(System.in);
                            input = sc.nextLine();
                        } while (!(input.equals("yes") || input.equals("no")));
                        System.out.println(input);
                        if (input.equals("yes")) {
                            lecturer.getDepartment().getLecturers().remove(lecturer);
                        } else System.out.println("lecturer has not been added to the department");
                    }
                }
                if (lecturer.getDepartment() == null || input.equals("yes")) {
                    department.getLecturers().add(lecturer);
                    lecturer.setDepartment(department);
                    System.out.printf("%s has been added to the department\n", lecturerName);
                }
            } catch (DoNotExists e) {System.out.println(e.getMessage()+". Try again");}
    }

    public double salaryAverage() {
        int average = 0;
        if (lecturers.isEmpty())
            return 0;
        for (Lecturer lecturer : lecturers) {
            average += lecturer.getWage();
        }
        return (double) average / lecturers.size();
    }

    public double getSalaryAverageByDepartment(Department department) throws DoNotExists {
        int salaryTotal = 0;
        for (Lecturer lecturer : department.getLecturers()) {
            salaryTotal += lecturer.getWage();
        }
        return (double) salaryTotal / department.getLecturers().size();
    }

    public void showDetailsLecturers() {
        System.out.println("Here all the lecturers:");
        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.toString() + "\n");
        }
    }

    public void showDetailsDepartments() {
        System.out.println("Here all the committees:");
        for (Committee committee : committees) {
            System.out.println(committee.toString() + "\n");
        }
    }

    public Doctor getDoctor(String doctorName) throws DoNotExists {
        Lecturer lecturer = getByName(lecturers,doctorName);
        if (!(lecturer.getKindOfDegree().equals(Lecturer.Degree.Doctoral) || lecturer.getKindOfDegree().equals(Lecturer.Degree.Professional)))
            throw new NotADoctor(doctorName);
        else
            return (Doctor) lecturer;
    }

    public void compareDoctors(Doctor doctor1, Doctor doctor2) {
        if (doctor1.getArticles().size() >= doctor2.getArticles().size()) {
            if (doctor1.getArticles().size() == doctor2.getArticles().size()) {
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
        if (committee1.getLecturers().size() >= committee2.getLecturers().size()) {
            if (committee2.getLecturers().size() == committee1.getLecturers().size()) {
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
            if (committee2Articles == committee1Articles) {
                System.out.printf("'%s' committee and '%s' committee has the same amount of articles\n",committee1.getName(),committee2.getName());
            }
            else
                System.out.printf("%s has more articles overall in compare to %s\n",committee1.getName(),committee2.getName());
        }
        else
            System.out.printf("%s has more articles overall in compare to %s\n",committee2.getName(),committee1.getName());

    }
    public String toString(){
        String details = "College name is: " + collegeName +"\nCollege lecturers details:\n ";
        for (Lecturer lecturer : lecturers) details += lecturer.toString() + "\n";

        details += "College Committees details:\n ";

        for (Committee committee : committees) details += committee.toString() + "\n";

        details += "College Departments details:\n ";

        for (Department department: departments) details += department.toString() + "\n";

        return details;
    }
    public void duplicateCommittee(String committeeName) throws AlreadyInException, DoNotExists {
        Committee original = getByName(committees,committeeName);
        Committee copy = new Committee(committeeName + "-new", original.getChairperson(),original.getDegreeType());
        original.getChairperson().getCommittees().add(copy);
        for (Lecturer orginalLecturer : original.getLecturers()) {
            addObject(copy.getLecturers(),orginalLecturer,orginalLecturer.getName());
            addObject(orginalLecturer.getCommittees(),copy,orginalLecturer.getName());
        }
        committees.add(copy);
        System.out.printf("Committee '%s' duplicated successfully as '%s'\n", committeeName, copy.getName());
    }
    //save college
    public void saveCollege(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        }
    }
    //load college
    public static College loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (College) in.readObject();
        }
    }
}




