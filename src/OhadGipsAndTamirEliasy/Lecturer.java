package OhadGipsAndTamirEliasy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class Lecturer implements HasName, Serializable
{
    public enum Degree {
        Bachelor,
        Master,
        Doctoral,
        Professional
    }
    protected String name;
    protected int id;
    protected Degree kindOfDegree;
    protected String nameOfDegree;
    protected int wage;
    protected Department department;
    protected HashSet<Committee> committees;

    public Lecturer(String name, int id, Degree kindOfDegree, String nameOfDegree, int wage) {
        setName(name);
        setId(id);
        setKindOfDegree(kindOfDegree);
        setNameOfDegree(nameOfDegree);
        setWage(wage);
        this.committees = new HashSet<>();
        department = null;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setKindOfDegree(Degree KindOfDegree) {
        this.kindOfDegree = KindOfDegree;
    }
    public void setNameOfDegree(String nameOfDegree) {
        this.nameOfDegree = nameOfDegree;
    }
    public void setWage(int wage) {
        this.wage = wage;
    }
    public Degree getKindOfDegree() {
        return kindOfDegree;
    }
    public int getWage() {
        return wage;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public Department getDepartment() {
        return department;
    }
    public void addCommittee(Committee committee)
    {
        committees.add(committee);
    }

    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Lecturer name is: ").append(name)
                .append("\nHis id is: ").append(id)
                .append("\nKind of Degree is: ").append(kindOfDegree)
                .append("\nName of degree is: ").append(nameOfDegree)
                .append("\nWage is: ").append(wage);
        if (department != null) {
            details.append("\nDepartment is: ").append(department.getName());
        }
        details.append("\nPart of this committees: ");

        Iterator<Committee> it = committees.iterator();
        while (it.hasNext()) {
            Committee committee = it.next();
            details.append(committee.getName());
            if (it.hasNext()) {
                details.append(", ");
            }
        }
        return details.toString();
    }

    public HashSet<Committee> getCommittees() {
        return committees;
    }

    public String getNameOfDegree() {
        return nameOfDegree;
    }
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Lecturer lecturer)) return false;
        return id == lecturer.id &&
                wage == lecturer.wage &&
                Objects.equals(name, lecturer.name) &&
                kindOfDegree == lecturer.kindOfDegree &&
                Objects.equals(nameOfDegree, lecturer.nameOfDegree) &&
                Objects.equals(committees, lecturer.committees) &&
                Objects.equals(department, lecturer.department);
    }
    public int getArticleCount() {
        return 0;
    }
    public boolean canBeChairperson() { return false; }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}




