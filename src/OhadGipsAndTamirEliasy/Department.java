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

    public int getNumOfStudents() {
        return numOfStudents;
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
        String details = "Department name is: " + getName()
                + "\nNumber Of Students: "+ getNumOfStudents() +
                "\nLecturers in this department: ";
        if(getLecturersSize() > 0) {
            for (int i = 0; i < getLecturersSize() - 1; i++)
                details = details + getLecturers()[i].getName() + " ,";
            return details + getLecturers()[getLecturersSize() - 1].getName();
        }
        return details;
    }

    @Override
    public boolean equals(Object obj) { // the function return true if it is the same
        if (obj == null) return false;
        else if (!(obj instanceof Department)) return false;
        else {
            Department other = (Department) obj;
            if (!name.equals(other.name)) return false;
            else if (numOfStudents != other.numOfStudents) return false;
            else if (lecturersSize != other.lecturersSize) return false;
            else if (!lecturers[0].equals(other.lecturers[0])) return false;
            return true;
        }
    }




}

