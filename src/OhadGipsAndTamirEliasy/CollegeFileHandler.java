package OhadGipsAndTamirEliasy;

import java.io.*;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class CollegeFileHandler {

        //save college
        public static boolean saveCollege(College college ,String filename) throws IOException {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
                out.writeObject(college);
                return true;
            }
        }
        //load college
        public static College loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (College) in.readObject();
        }
    }

}
