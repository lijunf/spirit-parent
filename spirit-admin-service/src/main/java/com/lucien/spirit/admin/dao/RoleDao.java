package com.lucien.spirit.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.admin.model.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

}
