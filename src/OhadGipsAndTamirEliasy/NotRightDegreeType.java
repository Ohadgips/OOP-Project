package OhadGipsAndTamirEliasy;

public class NotRightDegreeType extends Exception {
    public NotRightDegreeType() {
      String str = "Lecturer degree type is not as committee degree type";
      super(str);
    }
}
