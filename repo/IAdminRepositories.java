package com.project.hr.repo;

import com.project.hr.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepositories extends JpaRepository<Admin, Long> {
}
