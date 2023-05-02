package pro.sky.management.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pro.sky.management.domain.Employee;
import pro.sky.management.exceptions.BadRequestException;
import pro.sky.management.exceptions.EmployeeAlreadyAddedException;
import pro.sky.management.exceptions.EmployeeNotFoundException;
import pro.sky.management.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class ListEmployeeService implements EmployeeService{
    List<Employee> employees = new ArrayList<>();

    // выбрасывать исключение о переполнении тут как-то не очень логично,
    // ведь мы используем ArrayList чтобы переполнения не было, но введем
    // искусственное ограничение
    @Override
    public Employee addEmployee(String firstName, String lastName) {
        checkParameters(firstName, lastName);
        if (employees.size() > 10) {
            throw new EmployeeStorageIsFullException("Прием сотрудников не производится, наша фирма не резиновая");
        }
        // Если данные прошли проверку, убедиться, что имя и фамилия будут записаны в сотрудника с большой буквы.
        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);
        Employee e = new Employee(firstName, lastName);
        if (employees.contains(e)) {
            throw new EmployeeAlreadyAddedException("Двойную зарплату захотел, хитрец?");
        }
        employees.add(e);
        return e;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        checkParameters(firstName, lastName);
        int index = employees.indexOf(new Employee(firstName, lastName));
        if (index == -1) {
            throw new EmployeeNotFoundException("Мы таких не держим");
        }
        Employee e = employees.get(index);
        employees.remove(index);
        return e;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        checkParameters(firstName, lastName);
        int index = employees.indexOf(new Employee(firstName, lastName));
        if (index == -1) {
            throw new EmployeeNotFoundException("Мы таких не держим");
        }
        return employees.get(index);
    }

    @Override
    public List<Employee> getEmployees() {
        // не хочу отдавать ссылку на мой приватный список =0
        return new ArrayList<>(employees);
    }

    //Реализуйте проверку всех входящих от пользователя текстовых данных (имена сотрудников и фамилии) с помощью класса StringUtils.
    //Если проверка не пройдена, выбросить исключение, возвращающее статус 400 Bad Request.
    private boolean checkParameters(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new BadRequestException();
        }
        return true;
    }
}