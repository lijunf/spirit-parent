package com.lucien.spirit.admin.service;

import java.util.List;

import com.lucien.spirit.admin.model.DictEntry;

public interface DictEntryService {

	void save(DictEntry dictEntry);

	List<DictEntry> findByDictTypeId(String dictTypeId);

    DictEntry findOne(String dictTypeId, String dictId);

    void delete(Long id);
}
