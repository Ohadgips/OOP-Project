package OhadGipsAndTamirEliasy;

import java.io.Serializable;
import java.util.ArrayList;

public class Department implements HasName, Serializable {
    private String name;
    private int numOfStudents;
    private final ArrayList<Lecturer> lecturers;

    public Department(String name, int numOfStudents) {
        setName(name);
        setNumOfStudents(numOfStudents);
        lecturers = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }
    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }
    public int getNumOfStudents() {
        return numOfStudents;
    }


    public String toString() {
        String details = "Department name is: " + getName()
                + "\nNumber Of Students: "+ getNumOfStudents() +
                "\nLecturers in this department: ";
        for (int i = 0; i < lecturers.size(); i++) {
            details += lecturers.get(i).getName();
            if (i < lecturers.size() - 1) {
                details += (", ");
            }
        }
        return details + "\n";
    }

    @Override
    public boolean equals(Object obj) { // the function return true if it is the same
        if (obj == null) return false;
        else if (!(obj instanceof Department department)) return false;
        else {
            if (!name.equals(department.getName())) return false;
            else if (numOfStudents != department.getNumOfStudents()) return false;
            else if (lecturers.size() != department.getLecturers().size()) return false;
            else return lecturers.equals(department.getLecturers());
        }
    }
}

