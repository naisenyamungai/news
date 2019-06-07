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


        //get: show all sections
        get("/sections", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Section> allSections = sectionDao.getAll();
            model.put("sections", allSections);
            return new ModelAndView(model, "sections.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show new department form
        get("/sections/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Department> departments = departmentDao.getAll();
            model.put("departments", departments);
            return new ModelAndView(model, "sectionform.hbs");
        }, new HandlebarsTemplateEngine());



        //department: process new department form
        post("/sections", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            List<Department> allDepartments = departmentDao.getAll();
            model.put("departments", allDepartments);
            String section_name = req.queryParams("section_name");
            int departmentId = Integer.parseInt(req.queryParams("departmentId"));
            Section newSection = new Section(section_name, departmentId);
            sectionDao.add(newSection);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        //get: show all employees
        get("/employees", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Employee> allEmployees = employeeDao.getAll();
            model.put("employees", allEmployees);
            return new ModelAndView(model, "employees.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show new employee form
        get("/employees/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Section> sections = sectionDao.getAll();
            model.put("sections", sections);
            return new ModelAndView(model, "employeeform.hbs");
        }, new HandlebarsTemplateEngine());



        //department: process new department form
        post("/employees", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            List<Section> allSections = sectionDao.getAll();
            model.put("sections", allSections);
            String role = req.queryParams("role");
            String first_name = req.queryParams("first_name");
            String last_name = req.queryParams("last_name");
            String staff = req.queryParams("staff");
            int sectionId = Integer.parseInt(req.queryParams("sectionId"));
            Employee newEmployee = new Employee(role, first_name, last_name, staff, sectionId);
            employeeDao.add(newEmployee);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


    }
}
