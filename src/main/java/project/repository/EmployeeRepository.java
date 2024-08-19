package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentNameContainingIgnoreCase(String departmentName);
}