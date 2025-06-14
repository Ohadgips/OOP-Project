package OhadGipsAndTamirEliasy;

import java.io.Serializable;

public class DoNotExists extends Exception implements Serializable {
    public DoNotExists(String name) {
        super(name+" does not exist");
    }
}
