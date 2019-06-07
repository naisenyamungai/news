package dao;
import models.Article;
import java.util.List;

public interface ArticleDao {
    List<Article> getAll();

    void add(Article article);

    Article findById(int id);

    void update(int id, String title, String description);

    void deleteById(int id);

    void clearAllArticles();
}




