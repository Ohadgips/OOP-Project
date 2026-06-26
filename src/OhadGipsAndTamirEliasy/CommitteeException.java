package OhadGipsAndTamirEliasy;

import java.io.Serializable;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class CommitteeException extends Exception implements Serializable {

    public CommitteeException(String s) {
        super("This lecturer can't be a chairperson. chairperson must be a doctor or professor;");
    }
}
