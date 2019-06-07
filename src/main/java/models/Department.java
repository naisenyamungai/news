package models;
import java.util.Objects;
import java.util.ArrayList;

public class Department {
    private String department_name;
    private int id;
    private int divisionId;
    private ArrayList<Hod> hods;
    private ArrayList<Section> sections;

    public Department(String department_name, int divisionId){
        this.department_name = department_name;
        this.divisionId = divisionId;
        this.id = id;
    }
    public void setId(int id){
        this.id = id;
    }

    public void setDepartment_name(String department_name){
        this.department_name = department_name;
    }

    public void setDivisionId(int divisionId){
        this.divisionId = divisionId;
    }

    public String getDepartment_name(){
        return department_name;
    }

    public int getId(){
        return id;
    }

    public int getDivisionId(){
        return divisionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department department = (Department) o;
        return getId() == department.getId() &&
                Objects.equals(getDepartment_name(), department.getDepartment_name()) &&
                Objects.equals(hods, department.hods) &&
                Objects.equals(sections, department.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartment_name(), getId());
    }


}
