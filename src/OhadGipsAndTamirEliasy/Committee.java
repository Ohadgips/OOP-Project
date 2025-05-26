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
    public boolean canBeChairperson(Lecturer lecturer) {
        if (lecturer.getKindOfDegree() == Lecturer.Degree.Doctoral || lecturer.getKindOfDegree() == Lecturer.Degree.Professional) return true;
        else {
            System.out.println("This lecturer can't be a chairperson. chairperson must be a doctor or professor;");
            return false;
        }
    }


    public boolean existsInLecturer(Lecturer lecturer) {
        for (int i = 0; i < lecturersSize; i++) {
            if (lecturers[i] == lecturer) {
                return true;
            }
        }
        return false;
    }
    public boolean addLecturer(Lecturer lecturer) {
        if (existsInLecturer(lecturer)) {
            throw new IllegalArgumentException(lecturer.getName() + " is already in the committee");
        }
        if (lecturers.length <= lecturersSize)
            setLecturers(resizeLecturers(lecturers));

        lecturers[lecturersSize] = lecturer;
        lecturersSize++;
        return true;
    }
    public boolean removeLecturer(Lecturer lecturer) {
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
        if (!lecturerPlace)
            return false;
        else {
            lecturersSize--;
            return true;
        }
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

    @Override
    public boolean equals(Object obj) { // the function return true if it is the same
        if (obj == null) return false;
        else if (!(obj instanceof Committee)) return false;
        else {
            Committee other = (Committee) obj;
            if (!name.equals(other.name)) return false;
            else if (!chairperson.equals(other.chairperson)) return false;
            else if (lecturersSize != other.lecturersSize) return false;
            else if (!lecturers[0].equals(other.lecturers[0])) return false;
            return true;
        }
    }

}
