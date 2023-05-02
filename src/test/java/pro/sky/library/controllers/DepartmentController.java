package pro.sky.library.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.library.model.Employee;
import pro.sky.library.services.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public String hello() {
        return "Welcome to Department Controller";
    }

    @GetMapping("/max-salary")
    public Employee maxSalaryInDept(@RequestParam("departmentId") Integer id) {
        return service.findMaxSalaryInDept(id);
    }

    @GetMapping("/min-salary")
    public Employee minSalaryInDept(@RequestParam("departmentId") Integer id) {
        return service.findMinSalaryInDept(id);
    }

    @GetMapping("/all")
    public List<Employee> getEmployeesByDept(@RequestParam(value = "departmentId", required = false) Integer id) {
        if (id != null) {
            return service.getEmployeesOfDept(id);
        }
        return service.getStaffByDept();
    }
}