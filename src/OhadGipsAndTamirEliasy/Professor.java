package OhadGipsAndTamirEliasy;

public class Professor extends Doctor{
    private String professorshipPlace;
    public Professor(String name, int id, Degree kindOfDegree, String nameOfDegree, int wage, String professorshipPlace,String[] articles,int articlesSize) {
        super(name, id, kindOfDegree, nameOfDegree, wage,articles,articlesSize);
        setProfessorshipPlace(professorshipPlace);
    }

    public String getProfessorshipPlace() {
        return professorshipPlace;
    }

    public void setProfessorshipPlace(String professorshipPlace) {
        this.professorshipPlace = professorshipPlace;
    }
}
