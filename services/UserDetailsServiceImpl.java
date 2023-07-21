package com.project.hr.services;

import com.project.hr.entities.Employee;
import com.project.hr.repo.IEmployeeRepositories;
import com.project.hr.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private IEmployeeRepositories iEmployeeRepositories;

    public UserDetailsServiceImpl(IEmployeeRepositories iEmployeeRepositories) {
        this.iEmployeeRepositories = iEmployeeRepositories;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = iEmployeeRepositories.findByUserName(username);
        return JwtUserDetails.create(employee);
    }

    public UserDetails loadUserById(Long id){
        Employee employee = iEmployeeRepositories.findById(id).get();
        return JwtUserDetails.create(employee);
    }




}
