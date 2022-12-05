package com.example.rqchallenge.controllers.employee;

import com.example.rqchallenge.entities.Employee;
import com.example.rqchallenge.services.EmployeeService;
import com.example.rqchallenge.errors.CustomErrorException;
import com.example.rqchallenge.validator.ContactNumberConstraint;
import com.example.rqchallenge.validator.EvenNumber;
import com.example.rqchallenge.validator.StringValidator;
import lombok.Getter;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rq")
@Validated
public class EmployeeController implements IEmployeeController {

    @Autowired
    EmployeeService employeeService;


    @RequestMapping("/hello")
    public String controllerSaysHello() {
        return "Employee Controller says hello!";
    }

    @GetMapping("/validatePathVariableExmp/{id}")
    public ResponseEntity<String> validatePathVariableExmp(@PathVariable("id") @Valid Long id) {
        System.out.println("THe id number is " + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/validatePhone/{s}")
    public String validatePhoneNumber(
            @PathVariable("s") String searchString
    ) {
        System.out.println("Print the phone number: " + searchString.toString());
//        if (result.hasErrors()) {
//            return "Has errors boy!";
//        }
//        m.addAttribute("message", "Successfully saved phone " + searchString.toString());

        return "Validating the input";
    }


    /**
     *
     * @return - the list of all the employees
     */
    @Override
    @RequestMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = null;
        try {
            employeeList = employeeService.getAllEmployeesService();
        } catch (IOException | URISyntaxException | ParseException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.accepted().body(employeeList);
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

        List<Employee> employeeList = null;
        try {
            employeeList = employeeService.getEmployeesByNameSearchService(searchString);
        } catch (IOException | URISyntaxException | ParseException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.accepted().body(employeeList);
    }

    /**
     * gets the employee from the id off the server
     * @param id - the id of the employee
     * @return - the object of Employee of that id
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String id) {

        Employee employee = null;
        try {
            employee = employeeService.getEmployeeByIdService(id);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.accepted().body(employee);
    }

    /**
     * description -  this should return a single integer indicating the highest salary of all employees
     * @return - integer of the highest salary
     */
    @Override
    @GetMapping("/highestSalary")
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {

        Integer highestSalary = null;
        try {
            highestSalary = employeeService.getHighestSalaryOfEmployeesService();
        } catch (IOException | URISyntaxException | ParseException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.accepted().body(highestSalary);
    }

    /**
     * description -  this should return a list of the top 10 employees based off of their salaries
     * @return - list of the top 10 highest salaries
     */
    @Override
    @GetMapping("/topTenHighestEarningEmployeeNames")
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {

        List<String> highestEarnersList = null;
        try {
            highestEarnersList = employeeService.getTopTenHighestEarningEmployeeNamesService();
        } catch (IOException | URISyntaxException | ParseException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.accepted().body(highestEarnersList);
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

        String status = null;
        try {
            status = employeeService.createEmployeeService(employeeInput);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.accepted().body(status);
    }

    /**
     * the API on the dummy api.com isn't or wasn't working for me. API down.
     * So I just returned the name of the employee that is to be deleted.
     * description - this should delete the employee with specified id given
     * @param id - the id of the employee to be deleted
     * @return - the name of the deleted employee
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(String id) {
        String employeeDeleted = null;
        try {
            employeeDeleted = employeeService.deleteEmployeeService(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.accepted().body(employeeDeleted);
    }
}
