package OhadGipsAndTamirEliasy;

public class NotADoctor extends RuntimeException {
    public NotADoctor(String name) {
        System.out.printf("%s is not at least a doctor\n", name);
    }
}
