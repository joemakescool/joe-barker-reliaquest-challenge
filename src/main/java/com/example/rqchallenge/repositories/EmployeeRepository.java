package com.example.rqchallenge.repositories;

import com.example.rqchallenge.api.HttpRequestApi;
import com.example.rqchallenge.entities.Employee;
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

@Repository
public class EmployeeRepository {

    @Autowired
    HttpRequestApi httpRequestApi;

    ArrayList<Employee> employeeArrayList = new ArrayList<>();


    public List<Employee> getAllEmployees() throws IOException, URISyntaxException, InterruptedException, ParseException {

        if (employeeArrayList.size() == 0) {
            String employeeList = httpRequestApi.httpRequestGetAllEmployees(); // get the data off this list for it to send back all the employees

            // parse the information out for the employee id
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(employeeList);

            JsonNode t = jsonNode.get("data");
            for (int i = 0; i < t.size(); i++) {
                JsonNode d = t.get(i);
                String e = d.toString();
                Employee employee = objectMapper.readValue(e, Employee.class);
                employeeArrayList.add(employee);
            }
        }

        return employeeArrayList;
    }

}
