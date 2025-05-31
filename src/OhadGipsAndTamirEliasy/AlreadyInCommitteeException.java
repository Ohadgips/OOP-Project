package OhadGipsAndTamirEliasy;

public class AlreadyInCommitteeException extends Exception {
    public AlreadyInCommitteeException(String name) {
        super(name + " already part of this committee");
    }
    public AlreadyInCommitteeException(String msg,String name) {
        super(name + msg);
    }
}
