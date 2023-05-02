package pro.sky.library.model;

import java.util.Objects;

public class Employee {
    private static int counter = 1;
    private final int id;
    private final String firstName;
    private final String lastName;
    private int dept;
    private double salary;

    public Employee(String firstName, String lastName, int dept, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dept = dept;
        this.salary = salary;
        id = counter;
        counter++;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getDepartment() {
        return dept;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + getFullName()
                + ", department: " + dept + ", salary: " + salary;
    }

    public void setDepartment(int dept) {
        this.dept = dept;
    }

    public void setSalary(double amount) {
        salary = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && dept == employee.dept
                && Double.compare(employee.salary, salary) == 0
                && Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dept, salary);
    }
}