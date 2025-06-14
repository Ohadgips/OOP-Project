package OhadGipsAndTamirEliasy;

import java.io.Serializable;
import java.util.ArrayList;

public class Doctor extends Lecturer implements Serializable {
    protected ArrayList<String> articles;   // pointer - arr of str
    public Doctor(String name, int id, Degree kindOfDegree, String nameOfDegree, int wage,ArrayList<String> articles)  {
        super(name, id, kindOfDegree, nameOfDegree, wage);

        setArticles(articles);
    }

    public ArrayList<String> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<String> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        String details = super.toString();
        details += "\nHis Articles: ";
        for (int i = 0; i < articles.size(); i++) {
            details += articles.get(i);
            if (i < articles.size() - 1) {
                details += (", ");
            }
        }
        return details + "\n";
    }

    @Override
    public boolean equals(Object lecturer) {
        if (!super.equals(lecturer)) return false;
        if (!(lecturer instanceof Doctor doctor)) return false;
        else return articles.equals(doctor.articles);
    }
}






