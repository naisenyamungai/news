package dao;
import models.Classified;
import java.util.List;

public interface ClassifiedDao {
    List<Classified> getAll();

    void add(Classified classified);

    Classified findById(int id);

    void update(int id, String title, String description);

    void deleteById(int id);

    void clearAllClassifieds();
}




