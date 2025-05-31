package OhadGipsAndTamirEliasy;

public class DoNotExists extends Exception {
    public DoNotExists(String name) {
        super(name+" does not exist");
    }
}
