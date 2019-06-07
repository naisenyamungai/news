package models;
import java.util.Objects;
import java.util.ArrayList;

public class Classified {
    private String title;
    private String description;
    private String story;
    private int id;
    private int departmentId;

    public Classified(String title, String description, String story, int departmentId) {
        this.title = title;
        this.description = description;
        this.story = story;
        this.departmentId = departmentId;
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

    public void setDepartmentId(int departmentId){
        this.departmentId = departmentId;
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

    public int getDepartmentId(){
        return departmentId;
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

