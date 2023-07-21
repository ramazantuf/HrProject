package com.project.hr.controllers;

import com.project.hr.entities.Admin;
import com.project.hr.services.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Admin> getAllAdmins(){
        return adminService.getAllAdmins();
    }

    @PostMapping
    public Admin createOneAdmin(@RequestBody Admin newAdmin){
        return adminService.createOneAdmin(newAdmin);
    }

    @GetMapping("/{adminId}")
    public Admin getOneAdmin(@PathVariable Long adminId){
        return adminService.getOneAdmin(adminId);
    }

    @PutMapping("/{adminId}")
    public Admin updateOneAdmin(@PathVariable Long adminId, @RequestBody Admin updateAdmin){
        return adminService.updateOneAdmin(adminId, updateAdmin);
    }

    @DeleteMapping("/{adminId}")
    public void deleteOneAdmin(@PathVariable Long adminId){
        adminService.deleteOneAdmin(adminId);
    }






}
