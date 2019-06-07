package models;
import java.util.Objects;
import java.util.ArrayList;

public class Article {
    private String title;
    private String description;
    private String story;
    private int id;

    public Article(String title, String description, String Story) {
        this.title = title;
        this.description = description;
        this.story = story;
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getStory() {
        return story;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return getId() == article.getId() &&
                Objects.equals(getTitle(), article.getTitle()) &&
                Objects.equals(getDescription(), article.getDescription());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getId());
    }
}

