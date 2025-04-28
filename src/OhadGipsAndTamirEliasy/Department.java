package OhadGipsAndTamirEliasy;

public class Department {
    String name;
    int numOfStudents;
    Lecturer[] lecturers;
    int lecturersSize;
    public Department(String name, int numOfStudents) {
        setName(name);
        setNumOfStudents(numOfStudents);
        lecturers = new Lecturer[1];
        lecturersSize = 0;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }
    public Lecturer[] getLecturers() {
        return lecturers;
    }
    public int getLecturersSize() {
        return lecturersSize;
    }
    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }

    public void addLecturer(Lecturer lecturer){
        if (lecturers.length <= lecturersSize)
            setLecturers(Committee.resizeLecturers(lecturers));
        lecturers[lecturersSize] = lecturer;
        lecturersSize++;
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
}

