package OhadGipsAndTamirEliasy;

public class Department {
    String name;
    int numOfStudents;
    Lecturer[] lecturers;
    int lecturersSize;

    public void resizeLecturers() {
        if (lecturersSize >= lecturers.length) {
            Lecturer[] temp = new Lecturer[lecturersSize * 2];
            System.arraycopy(lecturers, 0, temp, 0, lecturersSize);
            lecturers = temp;
        }
    }

    public Department(String name, int numOfStudents) {
        setName(name);
        setNumOfStudents(numOfStudents);
        lecturers = new Lecturer[1];
        lecturersSize = 0;
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
        resizeLecturers();
        if (existsInLecturer(lecturer)) {
            System.out.println("Lecturer already exists in this department");
            return false;
        }
        else {
            lecturers[lecturersSize++] = lecturer;
            return true;
        }
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
}

