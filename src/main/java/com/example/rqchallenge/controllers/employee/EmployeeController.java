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

    // use the comparator thing for arraylists
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

    @Override
    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Map<String, Object> employeeInput) {

        try {
            employeeService.createEmployee(employeeInput);
            Employee employee = new Employee();
            employee.setEmployeeName("Joe Barker");
            return ResponseEntity.accepted().body(employee);

        } catch (Exception e) {
            System.out.println("hello i am a man " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // make this send back something else
        }

    }

    @Override
    @GetMapping("/delete")
    public ResponseEntity<String> deleteEmployeeById(String id) {
        System.out.println("Delete id: " + id);

        try{
            employeeService.deleteEmployee();
            return ResponseEntity.accepted().body("Success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed");
        }
    }
}
