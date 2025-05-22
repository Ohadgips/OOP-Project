package OhadGipsAndTamirEliasy;

public class AlreadyInCommitteeExeception extends Exception {
    public AlreadyInCommitteeExeception() {
        super("Already In Committee");
    }
    public AlreadyInCommitteeExeception(String name) {
        super(name + " already exists in this committee");
    }
}
