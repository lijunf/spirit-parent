package com.lucien.spirit.admin.service;

import java.util.List;

import com.lucien.spirit.admin.model.DictType;

public interface DictTypeService {

	List<DictType> findAll();
	
	void save(DictType dictType);

	DictType findOne(String dictTypeId);

	void delete(String id);
}
