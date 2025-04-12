package OhadGipsAndTamirEliasy;

public class Committee {
    Lecturer[] lecturers;
    int lecturersSize;
    Lecturer chairperson;

    public Committee(OhadGipsAndTamirEliasy.Lecturer chairperson) {
        this.chairperson = chairperson;
    }
    public Committee() {

    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }


    public Lecturer getChairperson() {
        return chairperson;
    }

    public void setChairperson(Lecturer chairperson) {
        this.chairperson = chairperson;
    }
}
