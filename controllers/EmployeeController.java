package com.project.hr.controllers;

import com.project.hr.entities.Employee;
import com.project.hr.requests.UpdateEmployeeRequests;
import com.project.hr.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public Employee createOneEmployee(@RequestBody Employee newEmployeeRequests){
        return employeeService.createOneEmployee(newEmployeeRequests);
    }

    @GetMapping("/{employeeId}")
    public Employee getOneEmployee(@PathVariable Long employeeId){
        return employeeService.getOneEmployee(employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee updateOneEmployee(@PathVariable Long employeeId, @RequestBody UpdateEmployeeRequests newEmployeeRequests){
        return employeeService.updateOneEmployee(employeeId, newEmployeeRequests);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteOneEmployee(@PathVariable Long employeeId){
        employeeService.deleteOneEmployee(employeeId);
    }
}
