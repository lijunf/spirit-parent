package com.lucien.spirit.admin.service;

import org.springframework.data.domain.Page;

import com.lucien.spirit.admin.model.User;

public interface UserService {
    
    Page<User> findForPagination(int page, int size, User user);

	void save(User user);
	
	void update(User user);
	
	User findOne(long id);
	
	User findByName(String name);
	
	void delete(long id);

    User findOneAndRole(Long id);

    void updateLastLogin(Long id);
}
