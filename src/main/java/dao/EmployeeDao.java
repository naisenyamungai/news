package dao;
import models.Employee;
import java.util.List;

public interface EmployeeDao {

    List<Employee> getAll();

    List<Employee> getAllEmployeesBySection(int sectionId);

    void add(Employee employee);

    Employee findById(int id);

    void update(int id, String role);

    void deleteById(int id);

    void clearAllEmployees();
}
