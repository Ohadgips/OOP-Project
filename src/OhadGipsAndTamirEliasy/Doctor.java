package OhadGipsAndTamirEliasy;

public class Doctor extends Lecturer{
    protected String[] articles;   // pointer - arr of str
    protected int articlesSize;    // size of the arr how many str
    public Doctor(String name, int id, Degree kindOfDegree, String nameOfDegree, int wage,String[] articles,int articlesSize)  {
        super(name, id, kindOfDegree, nameOfDegree, wage);
        setArticles(articles);
        setArticlesSize(articlesSize);
    }

    public void setArticlesSize(int articlesSize) {
        this.articlesSize = articlesSize;
    }

    public void setArticles(String[] articles) {
        this.articles = articles;
    }

    public int getArticlesSize() {
        return articlesSize;
    }

    @Override
    public String toString() {
        String details = super.toString();
        details += "\nHis Articles: ";
        if (articlesSize > 0) {
            for (int i = 0; i < articlesSize - 1; i++) {
                details += articles[i] + ", ";
            }
            return details + articles[articlesSize - 1];
        }
        return details;
    }


    // it is not necessary to write the equals function - we get it from the Lecturer
}




