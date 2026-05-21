package OhadGipsAndTamirEliasy;

import java.io.Serializable;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class NotADoctor extends DoNotExists implements Serializable {
    public NotADoctor(String name) {
        super(name+" is not a doctor or a professor");
    }
}
