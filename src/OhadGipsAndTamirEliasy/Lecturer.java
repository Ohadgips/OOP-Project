package OhadGipsAndTamirEliasy;

import java.io.Serializable;
import java.util.ArrayList;

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
    protected ArrayList<Committee> committees;

    public Lecturer(String name, int id, Degree kindOfDegree, String nameOfDegree, int wage) {
        setName(name);
        setId(id);
        setKindOfDegree(kindOfDegree);
        setNameOfDegree(nameOfDegree);
        setWage(wage);
        this.committees = new ArrayList<>();
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

    public String toString() {
        String details = "Lecturer name is: " + name +
                "\nHis id is: " + id +
                "\nKind of Degree is: " + kindOfDegree +
                "\nName of degree is: " + nameOfDegree +
                "\nWage is: " + wage;
        if (department != null)
            details += "\nDepartment is: " + department.getName();
        details += "\nPart of this committees: ";
        for (int i = 0; i < committees.size(); i++) {
            details += committees.get(i).getName();
            if (i < committees.size() - 1) {
                details += (", ");
            }
        }
        return details;
    }
    public ArrayList<Committee> getCommittees() {
        return committees;
    }

    public String getNameOfDegree() {
        return nameOfDegree;
    }
    public int getId() {
        return id;
    }
    @Override
    public boolean equals(Object Obj) {// the function return true if it is the same
            if (!(Obj instanceof Lecturer lecturer)) return false;
            else {
                if (committees.size() != lecturer.getCommittees().size()) return false;
                else if (!name.equals(lecturer.getName())) return false;
                else if (kindOfDegree != lecturer.getKindOfDegree()) return false;
                else if (!nameOfDegree.equals(lecturer.getNameOfDegree())) return false;
                else if (id != lecturer.getId()) return false;
                else if (!(wage == lecturer.getWage())) return false;
                else if (!(committees.equals(lecturer.getCommittees()))) return false;
                else{
                    if (department== null && lecturer.getDepartment() == null) return true;
                    else if (lecturer.getDepartment() == null || department == null) return false;
                    else return department.equals(lecturer.getDepartment());
                }
            }
    }
}




