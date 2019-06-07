package dao;
import models.DB;
import models.Division;
import models.Department;
import models.Director;
import org.sql2o.*;
import java.util.List;

public class Sql2oDivisionDao implements DivisionDao{
    private final Sql2o sql2o;

    public Sql2oDivisionDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Division division) {
        String sql = "INSERT INTO divisions (division_name) VALUES (:division_name)"; //raw sql
        try(Connection con = DB.sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(division) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            division.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Division> getAll() {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM divisions") //raw sql
                    .executeAndFetch(Division.class); //fetch a list
        }
    }

    @Override
    public Division findById(int id) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM divisions WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Division.class); //fetch an individual item
        }
    }

    @Override
    public void update(int id, String newDepartment_name){
        String sql = "UPDATE divisions SET division_name = :name WHERE id=:id";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", newDepartment_name)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from divisions WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }


   @Override
    public void clearAllDivisions() {
        String sql = "DELETE from divisions";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }



    @Override
    public List<Director> getAllDirectorsByDivision(int divisionId) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM directors WHERE divisionId = :divisionId")
                    .addParameter("divisionId", divisionId)
                    .executeAndFetch(Director.class);
        }
    }

    @Override
    public List<Department> getAllDepartmentsByDivision(int divisionId) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM departments WHERE divisionId = :divisionId")
                    .addParameter("divisionId", divisionId)
                    .executeAndFetch(Department.class);
        }
    }
}
