package com.lucien.spirit.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucien.spirit.admin.dao.DictEntryDao;
import com.lucien.spirit.admin.model.DictEntry;

@Service
public class DictEntryServiceImpl implements DictEntryService {

	@Autowired
	private DictEntryDao dictEntryDao;
	
	public void save(DictEntry dictEntry) {
		dictEntryDao.save(dictEntry);
	}

	public List<DictEntry> findByDictTypeId(String dictTypeId) {
		return dictEntryDao.findByDictTypeId(dictTypeId);
	}

    public DictEntry findOne(String dictTypeId, String dictId) {
        return dictEntryDao.findOne(dictTypeId, dictId);
    }

    public void delete(Long id) {
        dictEntryDao.delete(id);
    }
}
