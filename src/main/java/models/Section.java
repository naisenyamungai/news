package models;
import java.util.Objects;
import java.util.ArrayList;

public class Section {
    private String section_name;
    private int id;
    private int departmentId;
    private ArrayList<Employee> employees;

    public Section(String section_name, int departmentId){
        this.section_name = section_name;
        this.departmentId = departmentId;
        this.id = id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setSection_name(String section_name){
        this.section_name = section_name;
    }

    public void setDepartmentId(int departmentId){
        this.departmentId = departmentId;
    }

    public String getSection_name(){
        return section_name;
    }

    public int getId(){
        return id;
    }

    public int getDepartmentId(){
        return departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return getId() == section.getId() &&
                Objects.equals(getSection_name(), section.getSection_name()) &&
                Objects.equals(employees, section.employees);

    }

    @Override
    public int hashCode() {
        return Objects.hash(getSection_name(), getId());
    }

}

