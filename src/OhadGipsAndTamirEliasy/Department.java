package OhadGipsAndTamirEliasy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Iterator;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class Department implements HasName, Serializable {
    private String name;
    private int numOfStudents;
    private final HashSet<Lecturer> lecturers;

    public Department(String name, int numOfStudents) {
        setName(name);
        setNumOfStudents(numOfStudents);
        lecturers = new HashSet<>();
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
    public HashSet<Lecturer> getLecturers() {
        return lecturers;
    }
    public int getNumOfStudents() {
        return numOfStudents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Department name is: ").append(getName())
                .append("\nNumber Of Students: ").append(getNumOfStudents())
                .append("\nLecturers in this department: ");
        Iterator<Lecturer> it = lecturers.iterator();
        while (it.hasNext()) {
            details.append(it.next().getName());
            if (it.hasNext()) {
                details.append(",");
            }
        }
        return details.append("\n").toString();
    }

    @Override
    public boolean equals(Object obj) { // the function return true if it is the same
        if (obj == null) return false;
        if (!(obj instanceof Department department)) return false;
        return Objects.equals(name, department.name);
        }
}

