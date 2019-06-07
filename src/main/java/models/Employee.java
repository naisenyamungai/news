package models;
import org.sql2o.*;

import java.util.List;

public class Employee {
    private String first_name;
    private String middle_name;
    private String last_name;
    private String staff;
    private String role;
    private int sectionId;
    private int id;

    public Employee(String role, String first_name, String last_name, String staff, int sectionId){
        this.role = role;
        this.first_name = first_name;
        this.last_name = last_name;
        this.staff = staff;
        this.sectionId = sectionId;
        this.id = id;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    public void setMiddle_name(String middle_name){
        this.middle_name = middle_name;
    }


    public void setLast_name(String last_name){
        this.last_name = last_name;
    }


    public void setRole(String role){
        this.role= role;
    }

    public void setStaff(String staff){
        this.staff= staff;
    }

    public void setSectionId(int sectionId){
        this.sectionId = sectionId;
    }


    public String getFirst_name(){
        return first_name;
    }

    public String getLast_name(){
        return last_name;
    }

    public String getRole(){
        return role;
    }

    public String getStaff(){
        return staff;
    }

    public int getId(){
        return id;
    }

    public String getMiddle_name(){
        return middle_name;
    }


    public int getSectionId(){
        return sectionId;
    }

    @Override
    public boolean equals(Object otherEmployee){
        if (!(otherEmployee instanceof Employee)) {
            return false;
        } else {
            Employee newEmployee = (Employee) otherEmployee;
            return this.getFirst_name().equals(newEmployee.getFirst_name()) &&
                    this.getSectionId() == newEmployee.getSectionId();
        }
    }

}
