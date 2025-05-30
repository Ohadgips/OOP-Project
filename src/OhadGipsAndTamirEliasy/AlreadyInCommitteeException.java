package OhadGipsAndTamirEliasy;

public class AlreadyInCommitteeException extends Exception {
    public AlreadyInCommitteeException() {
        super("Already In Committee");
    }
    public AlreadyInCommitteeException(String name) {
        super(name + " already exists in this committee");
    }
}
