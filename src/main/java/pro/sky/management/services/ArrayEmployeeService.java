package pro.sky.management.services;

import org.springframework.stereotype.Service;
import pro.sky.management.domain.Employee;
import pro.sky.management.exceptions.EmployeeAlreadyAddedException;
import pro.sky.management.exceptions.EmployeeNotFoundException;
import pro.sky.management.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ArrayEmployeeService implements EmployeeService {
    private static final int ARRAY_SIZE = 10;
    private Employee[] employees = new Employee[ARRAY_SIZE];
    private int size = 0;

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee emp = new Employee(firstName, lastName);
        if (size < employees.length) {
            if (Objects.isNull(findEmployee(emp))) {
                employees[size] = emp;
                size++;
            } else {
                throw new EmployeeAlreadyAddedException("Двойную зарплату захотел, хитрец?");
            }
        } else {
            throw new EmployeeStorageIsFullException("Прием сотрудников не производится, наша фирма не резиновая");
        }
        return emp;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee emp = findEmployee(firstName, lastName);
        int i = 0;
        for (i = 0; i < employees.length; i++) {
            if (employees[i].equals(emp)) break;
        }
        Employee[] temp = new Employee[ARRAY_SIZE];
        System.arraycopy(employees, 0, temp, 0, i);
        System.arraycopy(employees, i + 1, temp, i, size - 1 - i);
        employees = temp;
        return emp;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee emp = findEmployee(new Employee(firstName, lastName));
        if (Objects.isNull(emp)) {
            throw new EmployeeNotFoundException("Мы таких не держим");
        }
        return emp;
    }

    private Employee findEmployee(Employee e) {
        for (var emp : employees) {
            if (e.equals(emp)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(List.of(employees));
    }
}