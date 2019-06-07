package dao;
import models.Director;
import java.util.List;

public interface DirectorDao {
    List<Director> getAll();

    void add(Director director);

    Director findById(int id);

    void update(int id, String role);

    void deleteById(int id);

    void clearAllDirectors();
}
