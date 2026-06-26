package OhadGipsAndTamirEliasy;

import java.io.Serializable;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class NotRightDegreeType extends Exception implements Serializable {
    public NotRightDegreeType() {
      super("Lecturer degree type is not as committee degree type");
    }
}
