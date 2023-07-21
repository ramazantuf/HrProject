package com.project.hr.repo;

import com.project.hr.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepositories extends JpaRepository<Employee, Long> {
    Employee findByUserName(String userName);
}
