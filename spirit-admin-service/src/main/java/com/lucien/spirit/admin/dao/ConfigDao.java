package com.lucien.spirit.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.admin.model.Config;

@Repository
public interface ConfigDao extends JpaRepository<Config, Long> {

	/**
	 * 根据key查找config
	 * @return
	 */
	public Config findByKey(String key);
}
