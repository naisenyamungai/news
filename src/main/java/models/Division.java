package models;

import java.util.Objects;
import java.util.ArrayList;

public class Division {
    private String division_name;
    private int id;
    private ArrayList<Director> directors;
    private ArrayList<Department> departments;

    public Division(String division_name){
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
        Division division = (Division) o;
        return getId() == division.getId() &&
                Objects.equals(getDivision_name(), division.getDivision_name()) &&
                Objects.equals(directors, division.directors) &&
                Objects.equals(departments, division.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDivision_name(), getId());
    }

}
