package dao;
import models.DB;
import models.Classified;
import org.sql2o.*;
import java.util.List;

public class Sql2oClassifiedDao implements ClassifiedDao{
    private final Sql2o sql2o;

    public Sql2oClassifiedDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Classified classified) {
        String sql = "INSERT INTO classifieds (title, description, story, departmentId) VALUES (:title, :description, :story, :departmentId)"; //raw sql
        try(Connection con = DB.sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(classified) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            classified.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Classified> getAll() {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM classifieds") //raw sql
                    .executeAndFetch(Classified.class); //fetch a list
        }
    }

    @Override
    public List<Classified> getAllClassifiedsByDepartment(int departmentId) {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM classifieds WHERE departmentId = :departmentId")
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Classified.class);
        }
    }

    @Override
    public Classified findById(int id) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM classifieds WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Classified.class); //fetch an individual item
        }
    }


    @Override
    public void update(int id, String newTitle, String newStory){
        String sql = "UPDATE classifieds SET title = :name WHERE id=:id";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("title", newTitle)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from classifieds WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }


    @Override
    public void clearAllClassifieds() {
        String sql = "DELETE from classifieds";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}





