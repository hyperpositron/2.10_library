package pro.sky.library.services;

import org.springframework.stereotype.Service;
import pro.sky.library.model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private List<Employee> staff = new ArrayList<>(List.of(
            new Employee("Harry", "Potter", 3, 11.0),
            new Employee("Hermoine", "Granger", 3, 55.5),
            new Employee("Ron", "Weasley", 3, 9.75),
            new Employee("Albus", "Dumbledore", 1, 100500.0),
            new Employee("Rubeus", "Hagrid", 2, 5.0),
            new Employee("Severus", "Snape", 2, 500.0),
            new Employee("Drako", "Malfoy", 4, 499.0),
            new Employee("Luna", "Lovegood", 5, 3.21),
            new Employee("Minerva", "Mcgonagall", 2, 750.0),
            new Employee("Remus", "Lupin", 2, 450.0)
    ));

    public Employee findMaxSalaryInDept(int deptNumber) {
        return staff.stream()
                .filter(e -> e.getDepartment() == deptNumber)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("No such employee"));
    }

    public Employee findMinSalaryInDept(int deptNumber) {
        return staff.stream()
                .filter(e -> e.getDepartment() == deptNumber)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("No such employee"));
    }

    public List<Employee> getEmployeesOfDept(int deptNumber) {
        return staff.stream()
                .filter(e -> e.getDepartment() == deptNumber)
                .collect(Collectors.toList());
    }

    public List<Employee> getStaffByDept() {
        return staff.stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.toList());
    }
}
