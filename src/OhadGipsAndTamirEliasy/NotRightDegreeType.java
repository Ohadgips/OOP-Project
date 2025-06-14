package OhadGipsAndTamirEliasy;

import java.io.Serializable;

public class NotRightDegreeType extends Exception implements Serializable {
    public NotRightDegreeType() {
      String str = "Lecturer degree type is not as committee degree type";
      super(str);
    }
}
