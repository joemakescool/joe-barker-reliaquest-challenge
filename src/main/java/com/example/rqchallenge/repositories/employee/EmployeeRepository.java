package com.example.rqchallenge.repositories.employee;

import com.example.rqchallenge.api.HttpRequestApi;
import com.example.rqchallenge.entities.Employee;
import com.example.rqchallenge.entities.EmployeeHighestSalaryComparable;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository {

    @Autowired
    HttpRequestApi httpRequestApi;


    public List<Employee> getAllEmployees() throws IOException, URISyntaxException, InterruptedException, ParseException {

        String employeeList = httpRequestApi.httpRequestGetAllEmployees(); // get the data off this list for it to send back all the employees
        ArrayList<Employee> employeeArrayList = new ArrayList<>(); // for the parsing of Employee

        // parse the information out for the employee id
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(employeeList);

        JsonNode t = jsonNode.get("data"); // where the data for the request is, this contains the list of employees
        for (int i = 0; i < t.size(); i++) {
            JsonNode d = t.get(i);
            String e = d.toString();
            Employee employee = objectMapper.readValue(e, Employee.class); // parse in JSON into an Employee object
            employeeArrayList.add(employee);
        }

        return employeeArrayList;
    }

    public List<EmployeeHighestSalaryComparable> getEmployeeHighestSalaryComparable() throws IOException, URISyntaxException, InterruptedException, ParseException {

        String employeeList = httpRequestApi.httpRequestGetAllEmployees(); // get the data off this list for it to send back all the employees
        List<EmployeeHighestSalaryComparable> employeeArrayList = new ArrayList<>(); // for the parsing of Employee

        // parse the information out for the employee id
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(employeeList);

        JsonNode t = jsonNode.get("data"); // where the data for the request is, this contains the list of employees
        for (int i = 0; i < t.size(); i++) {
            JsonNode d = t.get(i);
            String e = d.toString();
            EmployeeHighestSalaryComparable employee = objectMapper.readValue(e, EmployeeHighestSalaryComparable.class); // parse in JSON into an Employee object
            employeeArrayList.add(employee);
        }

        return employeeArrayList;
    }

    public Employee getEmployeeById(String id) throws URISyntaxException, IOException, InterruptedException {

        String employeeList = httpRequestApi.httpRequestGetEmployeeById(id); // get the data off this list for it to send data response

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(employeeList);

        JsonNode t = jsonNode.get("data"); // where the data for the request is, this contains the list of employees

        return objectMapper.readValue(t.toString(), Employee.class);
    }

    public String createEmployee(Map<String, Object> employeeInput) throws URISyntaxException, IOException, InterruptedException {
        StringBuilder status = new StringBuilder();

        HttpRequestApi httpRequestApi = new HttpRequestApi();
        // parse the employeeInput map
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ ");
        for (Map.Entry<String, Object> entry : employeeInput.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            stringBuilder.append(key).append(": ").append(value).append(",");
        }
        stringBuilder.append(" }");

        String response = httpRequestApi.httpRequestCreateEmployee(stringBuilder.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        JsonNode t = jsonNode.get("status");
        status.append(t);

        return status.toString();
    }
}
