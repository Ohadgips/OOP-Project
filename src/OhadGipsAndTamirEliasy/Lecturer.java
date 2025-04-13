package OhadGipsAndTamirEliasy;

public class Lecturer {

    private String name;
    private int id;
    private String kind_of_degree;
    private String name_of_degree;
    private int wage;
    private Department department;

    public Lecturer(String name, int id, String kind_of_degree, String name_of_degree, int wage) {
        setName(name);
        setId(id);
        setKind_of_degree(kind_of_degree);
        setWage(wage);
        this.department=null;

    }
    public Lecturer(String name, int id, String kind_of_degree, String name_of_degree, int wage, Department department) {
        setName(name);
        setId(id);
        setKind_of_degree(kind_of_degree);
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

    public void setKind_of_degree(String kind_of_degree) {
        this.kind_of_degree = kind_of_degree;
    }

    public void setName_of_degree(String name_of_degree) {
        this.name_of_degree = name_of_degree;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public String getKind_of_degree() {
        return kind_of_degree;
    }

    public String getName_of_degree() {
        return name_of_degree;
    }

    public int getWage() {
        return wage;
    }



}
