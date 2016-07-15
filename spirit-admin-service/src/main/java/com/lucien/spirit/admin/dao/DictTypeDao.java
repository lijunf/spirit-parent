package com.lucien.spirit.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.admin.model.DictType;

@Repository
public interface DictTypeDao extends JpaRepository<DictType, String> {

}
