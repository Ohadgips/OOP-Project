package OhadGipsAndTamirEliasy;

public class Lecturer {
    public enum Degree {
        Bachelor,
        Master,
        Doctoral;
    }

    private String name;
    private int id;
    private Degree kindOfDegree;
    private String nameOfDegree;
    private int wage;
    private Department department;
    private Department department2;

    // Creating a new lecturer without any Class (department=null).
    public Lecturer(String name, int id, Degree kindOfDegree, String nameOfDegree, int wage) {
        setName(name);
        setId(id);
        setKindOfDegree(kindOfDegree);
        setNameOfDegree(nameOfDegree);
        setWage(wage);
        this.department=null;

    }

    // Creating a new lecturer with Class (department=department).
    public Lecturer(String name, int id, Degree kind_of_degree, String nameOfDegree, int wage, Department department) {
        setName(name);
        setId(id);
        setKindOfDegree(kind_of_degree);
        setWage(wage);
        this.department=department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    //Creating Getters
    public String getName() {
        return name;
    }


    public int getId() {
        return id;
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

    public String getNameOfDegree() {
        return nameOfDegree;
    }

    public int getWage() {
        return wage;
    }



}
