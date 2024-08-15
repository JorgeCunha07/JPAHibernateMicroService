package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import project.entity.Department;
import project.repository.DepartmentRepository;
import project.repository.EmployeeRepository;
import project.entity.Employee;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatusCode.valueOf(401), "Employee not found")
        );

        if (!"Finances".equalsIgnoreCase(employee.getDepartment().getName())) {
            employee.setName(updatedEmployee.getName());
            employee.setBirthDate(updatedEmployee.getBirthDate());

            return employeeRepository.save(employee);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401),"Cannot edit Finance employees");
        }
    }

    public List<Employee> searchByDepartment(String departmentName) {
        return employeeRepository.findByDepartmentNameContainingIgnoreCase(departmentName);
    }

    public Employee createEmployee(Employee employee, Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Department not found"));

        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }


}
