package dao;
import models.Director;
import models.DB;
import org.sql2o.*;
import java.util.List;

public class Sql2oDirectorDao implements DirectorDao {
    private final Sql2o sql2o;

    public Sql2oDirectorDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Director director) {
        String sql = "INSERT INTO directors (role, first_name, last_name, staff, divisionId) VALUES (:role, :first_name, :last_name, :staff, :divisionId)"; //raw sql
        try(Connection con = DB.sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(director) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            director.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Director> getAll() {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM directors") //raw sql
                    .executeAndFetch(Director.class); //fetch a list
        }
    }


    @Override
    public Director findById(int id) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM directors WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Director.class); //fetch an individual item
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
        String sql = "DELETE from directors WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllDirectors() {
        String sql = "DELETE from directors";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
