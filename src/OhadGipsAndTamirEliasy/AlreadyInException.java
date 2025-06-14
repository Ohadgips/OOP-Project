package OhadGipsAndTamirEliasy;

import java.io.Serializable;

public class AlreadyInException extends Exception implements Serializable {
    public AlreadyInException(String name) {
        super(name + " already part of this committee ");
    }
    public AlreadyInException(String msg,String name) {
        super(name + msg);
    }
}
