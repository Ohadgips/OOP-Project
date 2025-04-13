package OhadGipsAndTamirEliasy;

public class Committee {
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

    public Committee(Lecturer chairperson) {
        this.chairperson = chairperson;
        lecturersSize = 0;
        lecturers = new Lecturer[1];
    }
    public Committee() {
        lecturersSize = 0;
        lecturers = new Lecturer[1];
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }
    public boolean addLecturer(Lecturer lecturer){
        resizeLecturers();
        lecturers[lecturersSize++] = lecturer;
        return true;
    }


    public Lecturer getChairperson() {
        return chairperson;
    }

    public void setChairperson(Lecturer chairperson) {
        if (chairperson.getDegree() == "doctorate") {
            this.chairperson = chairperson;
        }
        else{
            System.out.println("This lecturer can't be a chairperson. chairperson must have a doctorate");
        }
    }
}
