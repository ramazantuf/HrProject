package com.project.hr.controllers;

import com.project.hr.entities.Admin;
import com.project.hr.requests.CreateEmployeeRequests;
import com.project.hr.requests.EmployeeRequest;
import com.project.hr.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hr.entities.Employee;
import com.project.hr.responses.AuthResponse;
import com.project.hr.security.JwtTokenProvider;
import com.project.hr.services.EmployeeService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private EmployeeService employeeService;

    private PasswordEncoder passwordEncoder;

    private AdminService adminService;


    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, EmployeeService employeeService, PasswordEncoder passwordEncoder, AdminService adminService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody EmployeeRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);



        Employee employee = employeeService.getOneUserByUserName(loginRequest.getUserName());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Bearer " + jwtToken);
        authResponse.setUserId(employee.getId());

        return authResponse;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody CreateEmployeeRequests registerRequest) {
        AuthResponse authResponse = new AuthResponse();
        if(employeeService.getOneUserByUserName(registerRequest.getUserName()) != null) {
            authResponse.setMessage("Username already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }


        Employee employee = new Employee();
        Admin admin = adminService.getOneAdmin(registerRequest.getAdminId());

        employee.setUserName(registerRequest.getUserName());
        employee.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        employee.setAdmin(admin);

        employeeService.createOneEmployee(employee);


        authResponse.setMessage("User successfully registered.");
        authResponse.setUserId(registerRequest.getId());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
}