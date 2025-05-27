package OhadGipsAndTamirEliasy;


public class Committee {
    public static Lecturer[] resizeLecturers(Lecturer[] lecturers) {
        Lecturer[] temp = new Lecturer[lecturers.length * 2];
        for (int i = 0; i < lecturers.length && lecturers[i] != null; i++) {
            temp[i] = lecturers[i];
        }
        return temp;
    }

    String name;
    Lecturer[] lecturers;
    int lecturersSize;
    Lecturer chairperson;

    public Committee(String name,Lecturer chairperson) {
        setChairperson(chairperson);
        setName(name);
        lecturersSize = 0;
        lecturers = new Lecturer[1];
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
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

    public Boolean existsInLecturer(Lecturer lecturer) {
        for (int i = 0; i < lecturersSize; i++) {
            if (lecturers[i] == lecturer) {
                return true;
            }
        }
        return false;
    }
    public void addLecturer(Lecturer lecturer) throws AlreadyInCommitteeExeception {
        if (existsInLecturer(lecturer)){
            if (lecturers.length <= lecturersSize)
                setLecturers(resizeLecturers(lecturers));
            lecturers[lecturersSize] = lecturer;
            lecturersSize++;
        } else
         throw new AlreadyInCommitteeExeception(lecturer.getName());
    }

    public void removeLecturer(Lecturer lecturer) {
        boolean lecturerPlace = false;
        for (int i = 0; i < lecturersSize; i++) {
            if (lecturerPlace)
                lecturers[i - 1] = lecturers[i];
            else if (lecturers[i] == lecturer) {
                lecturerPlace = true;
                if (i + 1 >= lecturersSize)
                    lecturers[i] = null;
            }
        }
        if (lecturerPlace)
            lecturersSize--;
    }

    public String toString() {
        String details = "Committee name is: " + name + "\nChairperson is: " + chairperson.getName() + "\nThe lecturers in this committee are: ";
        if (lecturersSize > 0) {
            for (int i = 0; i < lecturersSize - 1; i++) {
                details += lecturers[i].getName() + ", ";
            }
            return details + lecturers[lecturersSize - 1].getName();
        }
        return details;
    }
}
