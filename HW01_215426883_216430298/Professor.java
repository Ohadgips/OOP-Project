package OhadGipsAndTamirEliasy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class Professor extends Doctor implements Serializable {
    private String professorshipPlace;

    public Professor(String name, int id, Degree kindOfDegree, String nameOfDegree, int wage, String professorshipPlace, HashSet<String> articles) {
        super(name, id, kindOfDegree, nameOfDegree, wage, articles);
        setProfessorshipPlace(professorshipPlace);
    }

    public String getProfessorshipPlace() {
        return professorshipPlace;
    }

    public void setProfessorshipPlace(String professorshipPlace) {
        this.professorshipPlace = professorshipPlace;
    }

    @Override
    public String toString() {
        return super.toString() + "\nPlace that gave him the professorship is: " + professorshipPlace;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Professor other)) return false;
        return Objects.equals(professorshipPlace, other.professorshipPlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
