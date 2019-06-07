package dao;
import models.DB;
import models.Article;
import org.sql2o.*;
import java.util.List;

public class Sql2oArticleDao implements ArticleDao{
    private final Sql2o sql2o;

    public Sql2oArticleDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Article article) {
        String sql = "INSERT INTO articles (title, description, story) VALUES (:title, :description, :story)"; //raw sql
        try(Connection con = DB.sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(article) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            article.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Article> getAll() {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM articles") //raw sql
                    .executeAndFetch(Article.class); //fetch a list
        }
    }

    @Override
    public Article findById(int id) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM articles WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Article.class); //fetch an individual item
        }
    }


    @Override
    public void update(int id, String newTitle, String newStory){
        String sql = "UPDATE articles SET title = :name WHERE id=:id";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("title", newTitle)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from articles WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }


    @Override
    public void clearAllArticles() {
        String sql = "DELETE from articles";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    
}




