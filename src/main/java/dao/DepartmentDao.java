package dao;
import models.Department;
import models.Hod;
import models.Section;
import java.util.List;

public interface DepartmentDao {
    List<Department> getAll();

    void add(Department department);

    Department findById(int id);

    List<Hod> getAllHodsByDepartment(int departmentId);

    List<Section> getAllSectionsByDepartment(int departmentId);

    void update(int id, String department_name);

    void deleteById(int id);

    void clearAllDepartments();

}
