package com.example.rqchallenge.controllers.employee;

import com.example.rqchallenge.entities.Employee;
import com.example.rqchallenge.repositories.EmployeeRepository;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rq")
public class EmployeeController implements IEmployeeController {


    @RequestMapping("/hello")
    public String controllerSaysHello() {
        System.out.println("EmployeeController says hello :)!");
        return "Hello from employee controller :)!";
    }

    @Override
    @RequestMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return null;
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
        return null;
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) {
        return null;
    }

    // use the comparator thing for arraylists
    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        return null;
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        return null;
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) {
        return null;
    }
}
