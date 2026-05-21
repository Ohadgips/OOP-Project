package OhadGipsAndTamirEliasy;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
import java.io.Serializable;

public class DoNotExists extends Exception implements Serializable {
    public DoNotExists(String name) {
        super(name+" does not exist");
    }
}
