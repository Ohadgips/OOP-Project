package OhadGipsAndTamirEliasy;

public class Committee {
    String name;
    Lecturer[] lecturers;
    int lecturersSize;
    Lecturer chairperson;

    public void resizeLecturers() {
        if (lecturersSize >= lecturers.length) {
            Lecturer[] temp = new Lecturer[lecturersSize * 2];
            for (int i = 0; i < lecturersSize; i++) {
                temp[i] = lecturers[i];
            }
            lecturers = temp;
        }
    }

    public Committee(String name,Lecturer chairperson) {
        setChairperson(chairperson);
        setName(name);
        lecturersSize = 0;
        lecturers = new Lecturer[1];
    }
    public boolean canBeChairperson(Lecturer lecturer){
        return lecturer.getKindOfDegree() == Lecturer.Degree.Master;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }
    public boolean addLecturer(Lecturer lecturer){
        resizeLecturers();
        lecturers[lecturersSize++] = lecturer;
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturer getChairperson() {
        return chairperson;
    }

    public void setChairperson(Lecturer chairperson) {
        if (chairperson.getKindOfDegree() == Lecturer.Degree.Master) {
            this.chairperson = chairperson;
        }
        else{
            System.out.println("This lecturer can't be a chairperson. chairperson must have a doctorate");
        }
    }
}
