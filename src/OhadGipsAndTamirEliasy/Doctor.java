package OhadGipsAndTamirEliasy;

public class Doctor extends Lecturer{
    protected String[] articles;
    protected int articlesSize;
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
}
