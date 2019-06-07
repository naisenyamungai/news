package dao;
import models.Hod;
import java.util.List;

public interface HodDao {
    List<Hod> getAll();

    void add(Hod hod);

    Hod findById(int id);

    void update(int id, String role);

    void deleteById(int id);

    void clearAllHods();
}
