package com.project.hr.services;

import com.project.hr.entities.Admin;
import com.project.hr.entities.Employee;
import com.project.hr.repo.IAdminRepositories;
import com.project.hr.repo.IEmployeeRepositories;
import com.project.hr.requests.CreateEmployeeRequests;
import com.project.hr.requests.UpdateEmployeeRequests;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private IEmployeeRepositories iEmployeeRepositories;
    private AdminService adminService;

    public EmployeeService(IEmployeeRepositories iEmployeeRepositories, AdminService adminService) {
        this.iEmployeeRepositories = iEmployeeRepositories;
        this.adminService = adminService;
    }

    public List<Employee> getAllEmployees() {
        return iEmployeeRepositories.findAll();
    }

    public Employee createOneEmployee(Employee employee) {
        return iEmployeeRepositories.save(employee);
    }
    
    public Employee getOneEmployee(Long employeeId) {
        return iEmployeeRepositories.findById(employeeId).orElse(null);
    }

    public Employee updateOneEmployee(Long employeeId, UpdateEmployeeRequests newEmployeeRequests) {
        Optional<Employee> employee = iEmployeeRepositories.findById(employeeId);
        if(employee.isPresent()){
            Employee toUpdate = employee.get();
            toUpdate.setUserName(newEmployeeRequests.getUserName());
            toUpdate.setPassword(newEmployeeRequests.getPassword());
            iEmployeeRepositories.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOneEmployee(Long employeeId) {
        iEmployeeRepositories.deleteById(employeeId);
    }

    public Employee getOneUserByUserName(String userName) {
        return iEmployeeRepositories.findByUserName(userName);
    }
}
