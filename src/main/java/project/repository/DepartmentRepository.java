package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}