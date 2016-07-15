package com.lucien.spirit.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.admin.model.Log;

@Repository
public interface LogDao extends JpaRepository<Log, Long>, JpaSpecificationExecutor<Log> {

}
