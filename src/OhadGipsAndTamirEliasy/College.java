package OhadGipsAndTamirEliasy;
import java.io.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class College implements Serializable {
    private String collegeName;
    private final HashSet<Lecturer> lecturers;
    private final HashSet<Committee>committees;
    private final HashSet<Department> departments;

    public College(String collegeName) {
        setCollegeName(collegeName);
        this.lecturers = new HashSet<>();
        this.committees = new HashSet<>();
        this.departments = new HashSet<>();
    }


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public HashSet<Committee> getCommittees() {
        return committees;
    }
    public HashSet<Department> getDepartments() {
        return departments;
    }

    public static <T extends HasName> T getByName(HashSet<T> list,String name) throws DoNotExists {
        for (T item : list) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new DoNotExists(name);
    }
    public static  <T extends HasName> void addObject(HashSet<T> list, T object,String exception) throws AlreadyInException{
        if (!list.contains(object)) {
            list.add(object);
        }
        else throw new AlreadyInException(exception);
    }

    public boolean lecturerExist(String name)
    {
        if (lecturers != null) {
            for (Lecturer lect : lecturers) {
                if (lect.getName().equals(name))
                {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean addLecturer(Lecturer lecturer)
    {
        return lecturers.add(lecturer);
    }

    public boolean addCommittee() throws EnumDoNotExists {
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

                    return true;
                } catch (Exception e) {
                    throw new EnumDoNotExists();
                }
            }
        } else{
            return false;
        }
        return true;
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
                if (lecturer.canBeChairperson()) {
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
        Iterator<Lecturer> it = lecturers.iterator();
        while(it.hasNext()) {
            Lecturer lecturer = it.next();
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
                Iterator<Department> it = departments.iterator();
                while(it.hasNext()) {
                    Department department = it.next();
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
        Iterator<Lecturer> it = lecturers.iterator();
        while(it.hasNext()) {
            average += it.next().getWage();
        }
        return (double) average / lecturers.size();
    }

    public double getSalaryAverageByDepartment(Department department) throws DoNotExists {
        int salaryTotal = 0;
        Iterator<Lecturer> it = department.getLecturers().iterator();
        while(it.hasNext()) {
            salaryTotal += it.next().getWage();
        }
        return (double) salaryTotal / department.getLecturers().size();
    }

    public void showDetailsLecturers(Comparator<Lecturer> cmp) {
        java.util.TreeSet<Lecturer> sortedLecturers = new java.util.TreeSet<>(cmp);
        sortedLecturers.addAll(lecturers);
        System.out.println("Here all the lecturers:");
        Iterator<Lecturer> it = sortedLecturers.iterator();
        while(it.hasNext())
        {
            System.out.println(it.next().toString() + "\n");
        }
    }

    public void showDetailsCommittees(Comparator<Committee> cmp) {
        java.util.TreeSet<Committee> sortedCommittees = new java.util.TreeSet<>(cmp);
        sortedCommittees.addAll(committees);
        System.out.println("Here all the committees:");
        Iterator<Committee> it = sortedCommittees.iterator();
        while(it.hasNext())
        {
            System.out.println(it.next().toString() + "\n");
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
        StringBuilder details = new StringBuilder();
        details.append("College name is: ").append(collegeName);
        details.append("\nCollege lecturers details:\n");
        Iterator<Lecturer> itLecturers = lecturers.iterator();
        while (itLecturers.hasNext()) {
            details.append(itLecturers.next().toString()).append("\n");
        }
        details.append("College Committees details:\n");
        Iterator<Committee> itCommittees = committees.iterator();
        while (itCommittees.hasNext()) {
            details.append(itCommittees.next().toString()).append("\n");
        }

        details.append("College Departments details:\n");
        Iterator<Department> itDepartments = departments.iterator();
        while (itDepartments.hasNext()) {
            details.append(itDepartments.next().toString()).append("\n");
        }

        return details.toString();
    }
    public void duplicateCommittee(String committeeName) throws AlreadyInException, DoNotExists {
        Committee original = getByName(committees,committeeName);
        Committee copy = new Committee(committeeName + "-new", original.getChairperson(),original.getDegreeType());
        original.getChairperson().getCommittees().add(copy);
        Iterator<Lecturer> it = original.getLecturers().iterator();
        while (it.hasNext()) {
            Lecturer orginalLecturer = it.next();
            addObject(copy.getLecturers(), orginalLecturer, orginalLecturer.getName());
            addObject(orginalLecturer.getCommittees(), copy, orginalLecturer.getName());
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




