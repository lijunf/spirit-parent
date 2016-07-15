package com.lucien.spirit.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucien.spirit.admin.dao.DictTypeDao;
import com.lucien.spirit.admin.model.DictType;

@Service
public class DictTypeServiceImpl implements DictTypeService {

	@Autowired
	private DictTypeDao dictTypeDao;
	
	public List<DictType> findAll() {
		return dictTypeDao.findAll();
	}
	
	public void save(DictType dictType) {
		dictTypeDao.save(dictType);
	}

	public DictType findOne(String dictTypeId) {
		return dictTypeDao.findOne(dictTypeId);
	}

	public void delete(String id) {
		dictTypeDao.delete(id);
	}
}
