package com.example.rqchallenge.services;

import com.example.rqchallenge.entities.Employee;
import com.example.rqchallenge.entities.EmployeeHighestSalaryComparable;
import com.example.rqchallenge.repositories.employee.EmployeeRepository;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
     *  this should return all employees whose name contains or matches the string input provided
     * @param searchString - string to search on
     * @return - the list of employees the fit the string description
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

    public Employee getEmployeeByIdService(String id) throws URISyntaxException, IOException, InterruptedException {
        return employeeRepository.getEmployeeById(id);
    }

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

    public String deleteEmployeeService(String id) throws IOException, URISyntaxException, ParseException, InterruptedException {
        return employeeRepository.deleteEmployeeById(id);
    }


}
