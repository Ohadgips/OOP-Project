package OhadGipsAndTamirEliasy;

public class Committee {
    String name;
    Lecturer[] lecturers;
    int lecturersSize;
    Lecturer chairperson;

    public int getLecturersSize() {
        return lecturersSize;
    }

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

    public void setName(String name) {
        this.name = name;
    }
    public void setChairperson(Lecturer chairperson) {
        if (chairperson.getKindOfDegree() == Lecturer.Degree.Master) {
            this.chairperson = chairperson;
        }
        else{
            System.out.println("This lecturer can't be a chairperson. chairperson must have a doctoral degree");
        }
    }


    public boolean canBeChairperson(Lecturer lecturer){
        return lecturer.getKindOfDegree() == Lecturer.Degree.Master;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public boolean existsInLecturer(Lecturer lecturer) {
        for (int i = 0; i < lecturersSize; i++) {
            if (lecturers[i] == lecturer) {
                return true;
            }
        }
        return false;
    }

    public boolean addLecturer(Lecturer lecturer){
        if (existsInLecturer(lecturer)) {
            System.out.printf("%s already exists in this committee",lecturer.getName());
            return false;
        }
        else {
            resizeLecturers();
            lecturers[lecturersSize++] = lecturer;
            return true;
        }
    }
    public boolean removeLecturer(Lecturer lecturer) {
        boolean lecturerPlace = false;
        for (int i = 0; i < lecturersSize; i++) {
            if (lecturerPlace) {
                lecturers[i - 1] = lecturers[i];
            } else if (lecturers[i] == lecturer) {
                lecturerPlace = true;
                if (i +1 >= lecturersSize) {
                    lecturers[i] = null;
                }
            }
            lecturersSize--;
        }
        if (!lecturerPlace) {
            System.out.printf("%s already not in this committee", lecturer.getName());
            return false;
        }
        return true;
    }



    public String getName() {
        return name;
    }

    public Lecturer getChairperson() {
        return chairperson;
    }

    public boolean setChairperson(Lecturer chairperson) {
        if (chairperson.getKindOfDegree() == Lecturer.Degree.Doctoral) {
            this.chairperson = chairperson;
            return true;
        }
        else{
            System.out.println("This lecturer can't be a chairperson. chairperson must have a doctoral degree");
            return false;
        }
    }

}
