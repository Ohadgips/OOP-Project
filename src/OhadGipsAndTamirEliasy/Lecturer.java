package OhadGipsAndTamirEliasy;

public class Lecturer {
    public enum Degree {
        Bachelor,
        Master,
        Doctoral,
        Professional
    }
    public static Committee[] resizeCommittees(Committee[] committees) {
        Committee[] temp = new Committee[committees.length * 2];
        for (int i = 0; i < committees.length && committees[i] != null; i++) {
            temp[i] = committees[i];
        }
        return temp;
    }

    protected String name;
    protected int id;
    protected Degree kindOfDegree;
    protected String nameOfDegree;
    protected int wage;
    protected Department department;
    protected Committee[] committees;
    protected int committeeSize;


    public Lecturer(String name, int id, Degree kindOfDegree, String nameOfDegree, int wage) {
        setName(name);
        setId(id);
        setKindOfDegree(kindOfDegree);
        setNameOfDegree(nameOfDegree);
        setWage(wage);
        this.committees = new Committee[1];
        committeeSize = 0;
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

    public void addCommittee(Committee committee) {
        if (committeeSize >= committees.length) {
            committees = resizeCommittees(committees);
        }
        committees[committeeSize] = committee;
        committeeSize++;
    }
    public void removeCommittee(Committee committee) {
        boolean lecturerPlace = false;
        for (int i = 0; i < committeeSize; i++) {
            if (lecturerPlace)
                committees[i - 1] = committees[i];
            else if (committees[i] == committee) {
                lecturerPlace = true;
                if (i + 1 >= committeeSize)
                    committees[i] = null;
            }
        }
        if (lecturerPlace)
            committeeSize--;
    }

    public String toString() {
        String details = "Lecturer name is: " + name +
                "\nHis id is: " + id +
                "\nKind of Degree is: " + kindOfDegree +
                "\nName of degree is: " + nameOfDegree +
                "\nWage is: " + wage +
                "\nDepartment is: " + department.getName() +
                "\nPart of this committees: ";
        if (committeeSize > 0) {
            for (int i = 0; i < committeeSize -1; i++) {
                details += committees[i].getName() + ", ";
            }
            return details + committees[committeeSize-1].getName();
        }
        return details;
    }

    @Override
    public boolean equals(Object obj) { // the function return true if it is the same
        if (obj == null) return false;
        else if (!(obj instanceof Lecturer)) return false;
        else {
            Lecturer other = (Lecturer) obj;
            if (committeeSize != other.committeeSize) return false;
            else if (!name.equals(other.name)) return false;
            else if (kindOfDegree != other.kindOfDegree) return false;
            else if (!nameOfDegree.equals(other.nameOfDegree)) return false;
            else if (id != other.id) return false;
            else if (!department.equals(other.department)) return false;
            else if (wage != other.wage) return false;
            else return true;
        }
    }
}
