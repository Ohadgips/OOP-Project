package OhadGipsAndTamirEliasy;

import java.io.Serializable;

public class NotADoctor extends DoNotExists implements Serializable {
    public NotADoctor(String name) {
        super(name+" is not a doctor or a professor");
    }
}
