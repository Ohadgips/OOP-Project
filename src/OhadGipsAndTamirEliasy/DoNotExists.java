package OhadGipsAndTamirEliasy;

public class DoNotExists extends RuntimeException {
    public DoNotExists(String name) {

        System.out.printf("%s does not exist",name);
    }
}
