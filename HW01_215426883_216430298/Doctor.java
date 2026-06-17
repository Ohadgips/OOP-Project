package OhadGipsAndTamirEliasy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
// Submitted By: Tamir Eliasy 216430298 & Ohad Gips 215426883
public class Doctor extends Lecturer implements Serializable {
    protected HashSet<String> articles;   // pointer - arr of str
    public Doctor(String name, int id, Degree kindOfDegree, String nameOfDegree, int wage,HashSet<String> articles)  {
        super(name, id, kindOfDegree, nameOfDegree, wage);

        setArticles(articles);
    }

    public HashSet<String> getArticles() {
        return articles;
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), articles);
    }

    public void setArticles(HashSet<String> articles) {
        this.articles = articles != null ? articles : new HashSet<>();
    }

    @Override
    public String toString() {
        StringBuilder details = new StringBuilder(super.toString());
        details.append("\nHis Articles: ");
        Iterator<String> it = articles.iterator();
        while (it.hasNext()) {
            details.append(it.next());
            if (it.hasNext()) details.append(", ");
        }
        return details.append("\n").toString();
    }

    @Override
    public boolean equals(Object lecturer) {
        if (!super.equals(lecturer)) return false;
        if (!(lecturer instanceof Doctor doctor)) return false;
        else return articles.equals(doctor.articles);
    }
}






