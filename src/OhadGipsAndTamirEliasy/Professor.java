package OhadGipsAndTamirEliasy;

import java.io.Serializable;
import java.util.ArrayList;

public class Professor extends Doctor implements Serializable {
    private String professorshipPlace;

    public Professor(String name, int id, Degree kindOfDegree, String nameOfDegree, int wage, String professorshipPlace, ArrayList<String> articles) {
        super(name, id, kindOfDegree, nameOfDegree, wage,articles);
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
        String details = super.toString();
        details += "\nPlace that gave him the professorship is: " + getProfessorshipPlace() ;
        return details;
    }
    @Override
    public boolean equals(Object lecturer) {
        if (super.equals(lecturer)) return true;
        else if (!(lecturer instanceof Professor professor)) return false;
        else return professorshipPlace.equals(professor.getProfessorshipPlace());
    }

}
