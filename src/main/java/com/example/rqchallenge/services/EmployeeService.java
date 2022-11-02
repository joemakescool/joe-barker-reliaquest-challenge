package com.example.rqchallenge.services;

import com.example.rqchallenge.entities.Employee;
import com.example.rqchallenge.entities.EmployeeHighestSalaryComparable;
import com.example.rqchallenge.repositories.employee.EmployeeRepository;
import com.example.rqchallenge.errors.CustomErrorException;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployeesService() throws IOException, URISyntaxException, ParseException, InterruptedException {
        return employeeRepository.getAllEmployees();
    }

    /**
     *  this finds all employees whose name contains or matches the string input provided
     * @param searchString - string to search on
     * @return - the list of employees the fit the string parameter description
     */
    public List<Employee> getEmployeesByNameSearchService(String searchString) throws IOException, URISyntaxException, ParseException, InterruptedException {

        List<Employee> matching = new ArrayList<>(); // list that matches the string

        List<Employee> employeeList = employeeRepository.getAllEmployees();// get the list of employees
        for (Employee employee: employeeList) {
            String employeeName = employee.getEmployeeName();
            if (employeeName.contains(searchString)) { // search and find what matches the string
                matching.add(employee);
            }
        }

        return matching;
    }

    /**
     *
     * @param id - id of the employee
     * @return - an employee object
     */
    public Employee getEmployeeByIdService(String id) throws URISyntaxException, IOException, InterruptedException {
        id = id.trim();
        Employee employee = null;

        employee = employeeRepository.getEmployeeById(id);

        if (employee == null) {
            throw new CustomErrorException("Employee is not found");
        }

        return employee;
    }

    /**
     * gets the list of employees, and loops through them all while keeping track of the highest
     * @return - integer of the highest salary
     */
    public Integer getHighestSalaryOfEmployeesService() throws IOException, URISyntaxException, ParseException, InterruptedException {
        Integer highestSalary = 0;

        List<Employee> employeeList = employeeRepository.getAllEmployees();

        for (Employee employee: employeeList) {
            Integer salary = employee.getEmployeeSalary();
            if (salary > highestSalary) {
                highestSalary = salary;
            }
        }

        return highestSalary;
    }

    /**
     * finds the top ten earners by getting all the employees, sorting them from highest to lowest and returning the top 10.
     * @return - a list of the top ten earning employees
     */
    public List<String> getTopTenHighestEarningEmployeeNamesService() throws IOException, URISyntaxException, ParseException, InterruptedException {

        List<String> highestEarnersNameList = new ArrayList<>();

        // get the whole list of employees
        List<EmployeeHighestSalaryComparable> employeeList = employeeRepository.getEmployeeHighestSalaryComparable();

        // sort that list from highest to lower
        Collections.sort(employeeList);

        // do a loop to get the top 10 and put the names into that list and send it back
        for (int i = 0; i < 10; i++) {
            Employee employee = employeeList.get(i);
            String name = employee.getEmployeeName();
            highestEarnersNameList.add(name);
        }

        return highestEarnersNameList;
    }

    public String createEmployeeService(Map<String, Object> employeeInput) throws URISyntaxException, IOException, InterruptedException {
        return employeeRepository.createEmployee(employeeInput);
    }

    public String deleteEmployeeService(String id) throws Exception {
        id = id.trim();
        return employeeRepository.deleteEmployeeById(id);
    }
}
