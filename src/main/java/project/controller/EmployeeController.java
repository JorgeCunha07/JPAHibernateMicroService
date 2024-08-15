package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import project.entity.Employee;
import project.service.EmployeeService;
import java.nio.file.AccessDeniedException;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, @RequestParam Long departmentId) {
        Employee createdEmployee = employeeService.createEmployee(employee, departmentId);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) throws AccessDeniedException {
        return employeeService.updateEmployee(id, updatedEmployee);
    }

    @GetMapping("/search")
    public List<Employee> searchByDepartment(@RequestParam String departmentName) {
        return employeeService.searchByDepartment(departmentName);
    }
}
