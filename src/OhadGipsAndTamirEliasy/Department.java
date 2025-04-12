package OhadGipsAndTamirEliasy;

public class Department {
    String name;
    int numOfStudents;
    Lecturer[] lecturers;
    int lecturersSize;

    public Department(String name, int numOfStudents) {
        setName(name);
        setNumOfStudents(numOfStudents);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfStudents() {
        return this.numOfStudents;
    }

    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }
}
