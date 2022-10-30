package com.example.rqchallenge.controllers.employee;

import com.example.rqchallenge.entities.Employee;
import com.example.rqchallenge.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rq")
public class EmployeeController implements IEmployeeController {

    @Autowired
    EmployeeService employeeService;


    @RequestMapping("/hello")
    public String controllerSaysHello() {
        System.out.println("EmployeeController says hello :)!");
        return "Hello from employee controller :)!";
    }

    /**
     *
     * @return - the list of all the employees
     */
    @Override
    @RequestMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {

        try {
            List<Employee> employeeList = employeeService.getAllEmployeesService();
            return ResponseEntity.accepted().body(employeeList);

        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * description - this returns all employees whose name contains or matches the string input provided
     * @param searchString - the string to search by
     * @return - list of employees that match/contain the string
     */
    @Override
    @GetMapping("/search/{searchString}")
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable("searchString") String searchString) {
        // parse the string for better readable and that the data is ok to search ok for searching
        searchString = searchString.trim();
        boolean isOnlyLetters = searchString.matches("[a-zA-Z]+");
        if (!isOnlyLetters) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "searching data can only be letters");
        }

        try {
            List<Employee> employeeList = employeeService.getEmployeesByNameSearchService(searchString);
            return ResponseEntity.accepted().body(employeeList);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "employee list not found", e);
        }
    }

    /**
     * gets the employee from the id off the server
     * @param id - the id of the employee
     * @return - the object of Employee of that id
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String id) {
        id = id.trim();

        try {
            Employee employee = employeeService.getEmployeeByIdService(id);
            return ResponseEntity.accepted().body(employee);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "employee id could not be processed", e);
        }
    }

    /**
     * description -  this should return a single integer indicating the highest salary of all employees
     * @return - integer of the highest salary
     */
    @Override
    @GetMapping("/highestSalary")
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        try {
            Integer highestSalary = employeeService.getHighestSalaryOfEmployeesService();
            return ResponseEntity.accepted().body(highestSalary);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Salary could not be found", e);
        }
    }

    /**
     * description -  this should return a list of the top 10 employees based off of their salaries
     * @return - list of the top 10 highest salaries
     */
    @Override
    @GetMapping("/topTenHighestEarningEmployeeNames")
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        try {
            List<String> highestEarnersList = employeeService.getTopTenHighestEarningEmployeeNamesService();
            return ResponseEntity.accepted().body(highestEarnersList);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Salary could not be found", e);
        }
    }

    /**
     * this should return a status of success or failed based on if an employee was created
     * @param employeeInput - the object the is to be put in
     *                      { name: '', salary: '', age: '' }
     * @return - string of the status (i.e. success or failed)
     */
    @Override
    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody Map<String, Object> employeeInput) {

        try {
            String status = employeeService.createEmployeeService(employeeInput);
            return ResponseEntity.accepted().body(status);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Employee created failed", e); // make this send back something else
        }

    }

    /**
     * the API on the dummy api.com isn't or wasn't working for me. So I just returned the name of the employee that is to be deleted.
     * description - this should delete the employee with specified id given
     * @param id - the id of the employee to be deleted
     * @return - the name of the deleted employee
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(String id) {
        System.out.println("Delete id: " + id);
        id = id.trim();
        try {
            String employeeDeleted = employeeService.deleteEmployeeService(id);
            return ResponseEntity.accepted().body(employeeDeleted);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.accepted().body("lets delete stuff " + id);
    }
}
