package pro.sky.management.services;

import pro.sky.management.domain.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastname);
    Employee removeEmployee(String firstName, String lastname);
    Employee findEmployee(String firstName, String lastname);

    List<Employee> getEmployees();
}