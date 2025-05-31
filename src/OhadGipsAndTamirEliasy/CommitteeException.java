package OhadGipsAndTamirEliasy;

public class CommitteeException extends Exception {

    public CommitteeException() {
        super("This lecturer can't be a chairperson. chairperson must be a doctor or professor;");
    }
    public CommitteeException(String message) {
        super(message);
    }
}
