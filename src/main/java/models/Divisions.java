package models;

import java.util.Objects;
import java.util.ArrayList;

public class Divisions {
    private String division_name;
    private int id;
    private ArrayList<Directors> directors;
    private ArrayList<Departments> departments;

    public Divisions(String division_name){
        this.division_name = division_name;
    }

    public void setDivision_name(String division_name){
        this.division_name = division_name;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDivision_name(){
        return division_name;
    }

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Divisions divisions = (Divisions) o;
        return getId() == divisions.getId() &&
                Objects.equals(getDivision_name(), divisions.getDivision_name()) &&
                Objects.equals(directors, divisions.directors) &&
                Objects.equals(departments, divisions.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDivision_name(), getId());
    }

}
