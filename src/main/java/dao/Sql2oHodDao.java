package dao;
import models.Hod;
import models.DB;
import org.sql2o.*;
import java.util.List;

public class Sql2oHodDao implements HodDao{
    private final Sql2o sql2o;

    public Sql2oHodDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Hod hod) {
        String sql = "INSERT INTO hods (role, first_name, last_name, staff, departmentId) VALUES (:role, :first_name, :last_name, :staff, :departmentId)"; //raw sql
        try(Connection con = DB.sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(hod) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            hod.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Hod> getAll() {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM hods") //raw sql
                    .executeAndFetch(Hod.class); //fetch a list
        }
    }


    @Override
    public Hod findById(int id) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM hods WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Hod.class); //fetch an individual item
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
        String sql = "DELETE from hods WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllHods() {
        String sql = "DELETE from hods";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
