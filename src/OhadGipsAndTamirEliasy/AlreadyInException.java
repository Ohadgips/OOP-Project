package OhadGipsAndTamirEliasy;

import java.io.Serializable;

// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class AlreadyInException extends Exception implements Serializable {
    public AlreadyInException(String name) {
        super(name + " already part of this committee ");
    }
    public AlreadyInException(String msg,String name) {
        super(name + msg);
    }
}
