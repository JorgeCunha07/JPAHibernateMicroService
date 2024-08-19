package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.entity.Department;
import project.entity.Employee;
import project.enums.DepartmentType;
import project.repository.DepartmentRepository;
import project.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Employee> searchByDepartment(String departmentName) {
        return employeeRepository.findByDepartmentNameContainingIgnoreCase(departmentName);
    }

    public Employee createEmployee(Employee employee, Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));

        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        if (!DepartmentType.FINANCES.equals(existingEmployee.getDepartment().getType())) {
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setBirthDate(updatedEmployee.getBirthDate());
            return employeeRepository.save(existingEmployee);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot edit Finance employees");
        }
    }
}
