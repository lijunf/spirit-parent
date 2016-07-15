package com.lucien.spirit.admin.service;

import java.util.List;

import com.lucien.spirit.admin.model.Role;

public interface RoleService {

    void update(Role role);

	void save(Role role);

	Role findOne(long id);

	List<Role> findAll();

    void delete(Long id);
}
