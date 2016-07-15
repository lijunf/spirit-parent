package com.lucien.spirit.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucien.spirit.admin.dao.RoleDao;
import com.lucien.spirit.admin.model.Role;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Transactional
    public void update(Role role) {
        Role roleTemp = this.roleDao.findOne(role.getId());
        roleTemp.setDescription(role.getDescription());
        roleTemp.setName(role.getName());
        roleTemp.setResource(role.getResource());
        this.roleDao.save(roleTemp);
    }

	public void save(Role role) {
		roleDao.save(role);
	}

	public Role findOne(long id) {
		return roleDao.findOne(id);
	}

	public List<Role> findAll() {
		return roleDao.findAll();
	}

    public void delete(Long id) {
        roleDao.delete(id);
    }
}
