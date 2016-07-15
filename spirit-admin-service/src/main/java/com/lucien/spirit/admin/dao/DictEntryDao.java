package com.lucien.spirit.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.admin.model.DictEntry;

@Repository
public interface DictEntryDao extends JpaRepository<DictEntry, Long> {

	@Query("SELECT e FROM DictEntry e WHERE e.dictType.dictTypeId = :dictTypeId ORDER BY e.orderNo ")
	public List<DictEntry> findByDictTypeId(@Param("dictTypeId") String dictTypeId);
	
	@Query("SELECT e FROM DictEntry e WHERE e.dictType.dictTypeId = :dictTypeId AND e.dictId = :dictId")
	DictEntry findOne(@Param("dictTypeId") String dictTypeId, @Param("dictId") String dictId);
}
