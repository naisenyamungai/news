package dao;
import models.Section;
import models.Employee;
import java.util.List;

public interface SectionDao {
    List<Section> getAll();

    void add(Section section);

    Section findById(int id);

    List<Employee> getAllEmployeesBySection(int sectionId);

    void update(int id, String section_name);

    void deleteById(int id);

    void clearAllSections();
}
