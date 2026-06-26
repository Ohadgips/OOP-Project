package OhadGipsAndTamirEliasy;
import java.io.*;
import java.util.Comparator;
import java.util.HashSet;

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

    public boolean committeeExist(String name)
    {
        if (lecturers != null) {
            for (Committee comm : committees) {
                if (comm.getName().equals(name))
                    return true;
            }
        }
        return false;
    }
    public boolean addCommittee(Committee committee)
    {
        return committees.add(committee);
    }
    public void addLecturerToCommittee(String committeeName, String lecturerName) throws AlreadyInException, NotRightDegreeType, DoNotExists {
        Committee committee = getByName(committees, committeeName);
        Lecturer lecturer = getByName(lecturers, lecturerName);
        if (committee.getChairperson().getName().equals(lecturerName)) {
            throw new AlreadyInException(" is already part of committee as the chairperson", lecturerName);
        }
        if (lecturer.getKindOfDegree() != committee.getDegreeType()) {
            throw new NotRightDegreeType();
        }
        addObject(lecturer.getCommittees(), committee, lecturer.getName());
        addObject(committee.getLecturers(), lecturer, lecturer.getName());
    }

    // set new chairperson - remove committee from chairperson list
    public void setNewChairperson(String committeeName, String lecturerName) throws CommitteeException, DoNotExists, AlreadyInException {
        Committee committee = getByName(committees, committeeName);
        Lecturer lecturer = getByName(lecturers, lecturerName);
        committee.getChairperson().getCommittees().remove(committee);
        if (!lecturer.canBeChairperson()) {
            throw new CommitteeException();
        }
        committee.getLecturers().remove(lecturer);
        addObject(lecturer.getCommittees(), committee, lecturer.getName());
        committee.setChairperson(lecturer);
    }

    public boolean HasDoctoralLecturer() {
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getKindOfDegree() == Lecturer.Degree.Doctoral || lecturer.getKindOfDegree() == Lecturer.Degree.Professional)
                return true;
        }
        return false;
    }

    public void removeCommitteeMember(String committeeName, String lecturerName) throws DoNotExists {
        Committee committee = getByName(committees, committeeName);
        Lecturer lecturer = getByName(lecturers, lecturerName);
        committee.getLecturers().remove(lecturer);
        lecturer.getCommittees().remove(committee);
    }


    public void addDepartment(Department department) {
        departments.add(department);
    }

    public boolean departmentExists(String name) {
        for (Department dept : departments) {
            if (dept.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void addLecturerToDepartment(String lecturerName, String departmentName) throws DoNotExists {
        Department department = getByName(departments, departmentName);
        Lecturer lecturer = getByName(lecturers, lecturerName);
        if (lecturer.getDepartment() != null) {
            lecturer.getDepartment().getLecturers().remove(lecturer);
        }
        department.getLecturers().add(lecturer);
        lecturer.setDepartment(department);
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

    public HashSet<Lecturer> getLecturers() {
        return lecturers;
    }

    public String formatSortedLecturers(Comparator<Lecturer> cmp) {
        java.util.TreeSet<Lecturer> sortedLecturers = new java.util.TreeSet<>(cmp);
        sortedLecturers.addAll(lecturers);
        StringBuilder sb = new StringBuilder("Here all the lecturers:\n");
        for (Lecturer sortedLecturer : sortedLecturers) {
            sb.append(sortedLecturer.toString()).append("\n");
        }
        return sb.toString();
    }

    public String formatSortedCommittees(Comparator<Committee> cmp) {
        java.util.TreeSet<Committee> sortedCommittees = new java.util.TreeSet<>(cmp);
        sortedCommittees.addAll(committees);
        StringBuilder sb = new StringBuilder("Here all the committees:\n");
        for (Committee sortedCommittee : sortedCommittees) {
            sb.append(sortedCommittee.toString()).append("\n");
        }
        return sb.toString();
    }

    public Doctor getDoctor(String doctorName) throws DoNotExists {
        Lecturer lecturer = getByName(lecturers,doctorName);
        if (!(lecturer.getKindOfDegree().equals(Lecturer.Degree.Doctoral) || lecturer.getKindOfDegree().equals(Lecturer.Degree.Professional)))
            throw new NotADoctor(doctorName);
        else
            return (Doctor) lecturer;
    }

    public String compareDoctors(Doctor doctor1, Doctor doctor2) {
        StringBuilder sb = new StringBuilder();
        if (doctor1.getArticles().size() >= doctor2.getArticles().size()) {
            if (doctor1.getArticles().size() == doctor2.getArticles().size()) {
                sb.append(String.format("%s and %s have the same number of articles\n", doctor1.getName(), doctor2.getName()));
            } else {
                sb.append(String.format("%s has more articles in compare to %s\n", doctor1.getName(), doctor2.getName()));
            }
        } else {
            sb.append(String.format("%s has less articles in compare to %s\n", doctor1.getName(), doctor2.getName()));
        }
        return sb.toString();
    }

    public String compareCommittees(Committee committee1, Committee committee2) {
        StringBuilder sb = new StringBuilder();
        if (committee1.getLecturers().size() >= committee2.getLecturers().size()) {
            if (committee2.getLecturers().size() == committee1.getLecturers().size()) {
                sb.append(String.format("%s and %s has the same amount of lecturers\n", committee1.getName(), committee2.getName()));
            } else {
                sb.append(String.format("%s has more lecturers in compare to %s\n", committee1.getName(), committee2.getName()));
            }
        } else {
            sb.append(String.format("%s has more lecturers in compare to %s\n", committee2.getName(), committee1.getName()));
        }
        int committee1Articles = committee1.getArticlesAmount();
        int committee2Articles = committee2.getArticlesAmount();

        if (committee1Articles >= committee2Articles) {
            if (committee2Articles == committee1Articles) {
                sb.append(String.format("'%s' committee and '%s' committee has the same amount of articles\n", committee1.getName(), committee2.getName()));
            } else {
                sb.append(String.format("%s has more articles overall in compare to %s\n", committee1.getName(), committee2.getName()));
            }
        } else {
            sb.append(String.format("%s has more articles overall in compare to %s\n", committee2.getName(), committee1.getName()));
        }
        return sb.toString();
    }
    public String toString(){
        StringBuilder details = new StringBuilder();
        details.append("College name is: ").append(collegeName);
        details.append("\nCollege lecturers details:\n");
        for (Lecturer lecturer : lecturers) {
            details.append(lecturer.toString()).append("\n");
        }
        details.append("College Committees details:\n");
        for (Committee committee : committees) {
            details.append(committee.toString()).append("\n");
        }

        details.append("College Departments details:\n");
        for (Department department : departments) {
            details.append(department.toString()).append("\n");
        }

        return details.toString();
    }
    public void duplicateCommittee(String committeeName) throws AlreadyInException, DoNotExists {
        Committee original = getByName(committees, committeeName);
        Committee copy = new Committee(committeeName + "-new", original.getChairperson(), original.getDegreeType());
        original.getChairperson().getCommittees().add(copy);
        for (Lecturer orginalLecturer : original.getLecturers()) {
            addObject(copy.getLecturers(), orginalLecturer, orginalLecturer.getName());
            addObject(orginalLecturer.getCommittees(), copy, orginalLecturer.getName());
        }
        committees.add(copy);
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




