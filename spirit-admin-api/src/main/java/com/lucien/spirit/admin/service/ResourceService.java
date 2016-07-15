package com.lucien.spirit.admin.service;

import java.util.List;

import com.lucien.spirit.admin.model.Resource;

public interface ResourceService {
    
    List<Resource> findAll();
    
    List<Resource> findTopList();

    void delete(Long id);

	void save(Resource resource);
	
	void update(Resource resource);

	Resource findOne(Long id);
}
