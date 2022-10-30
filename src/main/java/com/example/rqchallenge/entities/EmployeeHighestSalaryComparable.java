package com.example.rqchallenge.entities;

// trying to extend behavior of a class without changing the concrete class itself.
// I want to be able to compare Employees by their names or even ids too and sort them with those.
// So I'd made another class for that. I tried to use the decorator pattern.
public class EmployeeHighestSalaryComparable extends Employee implements Comparable<Employee> {

    // comparing the salaries. The highest salary will be what goes first/in front.
    // return 1 if the passed in object is bigger than the current/this object
    // return -1 passed in object is less than the current/this object
    // return 0 if they are the same
    @Override
    public int compareTo(Employee o) {
        return o.getEmployeeSalary().compareTo(this.getEmployeeSalary());
    }
}
