package dao;
import models.Employee;
import models.DB;
import org.sql2o.*;
import java.util.List;

public class Sql2oEmployeeDao implements EmployeeDao{
    private final Sql2o sql2o;

    public Sql2oEmployeeDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Employee employee) {
        String sql = "INSERT INTO employees (role, first_name, last_name, staff, sectionId) VALUES (:role, :first_name, :last_name, :staff, :sectionId)"; //raw sql
        try(Connection con = DB.sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(employee) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            employee.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Employee> getAll() {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM employees") //raw sql
                    .executeAndFetch(Employee.class); //fetch a list
        }
    }


    @Override
    public Employee findById(int id) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM employees WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Employee.class); //fetch an individual item
        }
    }

    @Override
    public void update(int id, String newRole){
        String sql = "UPDATE  SET role = :role WHERE id=:id";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("role", newRole)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from employees WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllEmployees() {
        String sql = "DELETE from employees";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
