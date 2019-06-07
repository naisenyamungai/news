import static spark.Spark.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Sql2oDepartmentDao;
import dao.Sql2oDivisionDao;
import dao.Sql2oDirectorDao;
import dao.Sql2oHodDao;
import dao.Sql2oSectionDao;
import dao.Sql2oEmployeeDao;
import models.DB;
import models.Division;
import models.Director;
import models.Department;
import models.Hod;
import models.Section;
import models.Employee;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        Sql2o sql2o = DB.sql2o;
        Sql2oDivisionDao divisionDao = new Sql2oDivisionDao(sql2o);
        Sql2oDirectorDao directorDao = new Sql2oDirectorDao(sql2o);
        Sql2oDepartmentDao departmentDao = new Sql2oDepartmentDao(sql2o);
        Sql2oHodDao hodDao = new Sql2oHodDao(sql2o);
        Sql2oSectionDao sectionDao = new Sql2oSectionDao(sql2o);
        Sql2oEmployeeDao employeeDao = new Sql2oEmployeeDao(sql2o);


        get("/", (request, response) -> {
                    Map<String, Object> model = new HashMap<>();
                    return new ModelAndView(model, "index.hbs");
                }, new HandlebarsTemplateEngine()
        );

        //get: show all divisions
        get("/division", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Division> allDivisions = divisionDao.getAll();
            model.put("divisions", allDivisions);
            return new ModelAndView(model, "division.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show new division form
        get("/divisions/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Division> divisions = divisionDao.getAll();
            return new ModelAndView(model, "divisionform.hbs");
        }, new HandlebarsTemplateEngine());


        //division: process new division form
        post("/divisions", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            String division_name = req.queryParams("division_name");
            Division newDivision = new Division(division_name);
            divisionDao.add(newDivision);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        //get: show all departments
        get("/department", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Department> allDepartments = departmentDao.getAll();
            model.put("departments", allDepartments);
            return new ModelAndView(model, "department.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show new department form
        get("/departments/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Division> divisions = divisionDao.getAll();
            model.put("divisions", divisions);
            return new ModelAndView(model, "depform.hbs");
        }, new HandlebarsTemplateEngine());


//        //division: process new department form
//        post("/departments", (req, res) -> { //URL to make new task on POST route
//            Map<String, Object> model = new HashMap<>();
//            String department_name = req.queryParams("department_name");
//            Division newDivision = new Division(department_name);
//            departmentDao.add(newDepartment);
//            res.redirect("/");
//            return null;
//        }, new HandlebarsTemplateEngine());

        //department: process new department form
        post("/departments", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            List<Division> allDivisions = divisionDao.getAll();
            model.put("divisions", allDivisions);
            String department_name = req.queryParams("department_name");
            int divisionId = Integer.parseInt(req.queryParams("divisionId"));
            Department newDepartment = new Department(department_name, divisionId);
            departmentDao.add(newDepartment);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

    }
}
