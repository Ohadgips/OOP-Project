package OhadGipsAndTamirEliasy;

import java.io.Serializable;
import java.util.ArrayList;

public class Committee implements HasName, Serializable {
    private String name;
    private final ArrayList<Lecturer> lecturers;
    private Lecturer chairperson;
    private Lecturer.Degree degreeType;

    public Committee(String name,Lecturer chairperson,Lecturer.Degree degreeType) {
        setChairperson(chairperson);
        setName(name);
        lecturers = new ArrayList<>();
        setDegreeType(degreeType);
    }

    public void setDegreeType(Lecturer.Degree degreeType) {
        this.degreeType = degreeType;
    }

    public Lecturer.Degree getDegreeType() {
        return degreeType;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public Lecturer getChairperson() {
        return chairperson;
    }

    public boolean setChairperson(Lecturer chairperson) {
        this.chairperson = chairperson;
        return true;
    }
    public boolean canBeChairperson(Lecturer lecturer) throws CommitteeException {
        if (lecturer.getKindOfDegree() == Lecturer.Degree.Doctoral || lecturer.getKindOfDegree() == Lecturer.Degree.Professional)
            return true;
        else {
            throw new CommitteeException();
        }
    }


    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public int getArticlesAmount(){
        int sum = 0;
        for (Lecturer lecturer : lecturers) {
            if (lecturer instanceof Doctor)
            {
                sum += ((Doctor) lecturer).articles.size();
            }
        }
        return sum;
    }


    public String toString() {
        String details = "Committee name is: " + name
                + "\nChairperson is: " + chairperson.getName()
                +"\nThe lecturers degree type in this committee: "+degreeType
                + "\nThe lecturers in this committee are: ";
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
        else if (!(obj instanceof Committee other)) return false;
        else {
            if (!name.equals(other.getName())) return false;
            else if (!degreeType.equals(other.getDegreeType())) return false;
            else if (!chairperson.equals(other.getChairperson())) return false;
            else if (lecturers.size() != other.getLecturers().size()) return false;
            else return (lecturers.equals(other.getLecturers()));
        }
    }
}
