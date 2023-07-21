package com.project.hr.services;

import com.project.hr.entities.Admin;
import com.project.hr.repo.IAdminRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private IAdminRepositories iAdminRepositories;

    public AdminService(IAdminRepositories iAdminRepositories) {
        this.iAdminRepositories = iAdminRepositories;
    }

    public List<Admin> getAllAdmins() {
        return iAdminRepositories.findAll();
    }

    public Admin createOneAdmin(Admin newAdmin) {
        Admin toSave = new Admin();
        toSave.setUserName(newAdmin.getUserName());
        toSave.setPassword(newAdmin.getPassword());
        return iAdminRepositories.save(toSave);
    }

    public Admin getOneAdmin(Long adminId) {
        return iAdminRepositories.findById(adminId).orElse(null);
    }

    public Admin updateOneAdmin(Long adminId, Admin updateAdmin) {
        Optional<Admin> admin = iAdminRepositories.findById(adminId);
        if(admin.isPresent()){
            Admin foundAdmin = admin.get();
            foundAdmin.setUserName(updateAdmin.getUserName());
            foundAdmin.setPassword(updateAdmin.getPassword());
            iAdminRepositories.save(foundAdmin);
            return foundAdmin;
        }else {
            return null;
        }
    }

    public void deleteOneAdmin(Long adminId) {
        iAdminRepositories.deleteById(adminId);
    }
}
