package dao;
import models.Division;
import models.Director;
import models.Department;
import java.util.List;

public interface DivisionDao {
    List<Division> getAll();

    void add(Division division);

    Division findById(int id);

    List<Director> getAllDirectorsByDivision(int divisionId);

    List<Department> getAllDepartmentsByDivision(int divisionId);

    void update(int id, String division_name);

    void deleteById(int id);

    void clearAllDivisions();

}
