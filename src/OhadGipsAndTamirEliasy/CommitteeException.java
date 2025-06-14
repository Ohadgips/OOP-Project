package OhadGipsAndTamirEliasy;

import java.io.Serializable;

public class CommitteeException extends Exception implements Serializable {

    public CommitteeException() {
        super("This lecturer can't be a chairperson. chairperson must be a doctor or professor;");
    }
}
