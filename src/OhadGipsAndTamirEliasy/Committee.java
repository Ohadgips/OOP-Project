package OhadGipsAndTamirEliasy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class Committee implements HasName, Serializable {
    private String name;
    private final HashSet<Lecturer> lecturers;
    private Lecturer chairperson;
    private Lecturer.Degree degreeType;

    public Committee(String name, Lecturer chairperson, Lecturer.Degree degreeType) {
        setChairperson(chairperson);
        setName(name);
        lecturers = new HashSet<>();
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

    public void setChairperson(Lecturer chairperson) {
        this.chairperson = chairperson;
    }


    public HashSet<Lecturer> getLecturers() {
        return lecturers;
    }

    public int getArticlesAmount() {
        int sum = 0;
        for (Lecturer lecturer : lecturers) {
            sum += lecturer.getArticleCount();
        }
        return sum;
    }


    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Committee name is: ").append(name)
                .append("\nChairperson is: ").append(chairperson.getName())
                .append("\nThe lecturers degree type in this committee: ").append(degreeType)
                .append("\nThe lecturers in this committee are: ");

        Iterator<Lecturer> it = lecturers.iterator();
        while (it.hasNext()) {
            Lecturer lecturer = it.next();
            details.append(lecturer.getName());
            if (it.hasNext()) {
                details.append(", ");
            }
        }
        details.append("\n");
        return details.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Committee other)) return false;
        return Objects.equals(name, other.name) &&
                degreeType == other.degreeType &&
                Objects.equals(chairperson, other.chairperson) &&
                Objects.equals(lecturers, other.lecturers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

