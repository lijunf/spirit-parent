package com.lucien.spirit.admin.service;

import java.util.List;

import com.lucien.spirit.admin.model.Config;

public interface ConfigService {

	List<Config> findAll();

	void save(Config config);

	Config findOne(Long id);

	void delete(Long id);

	Config findByKey(String key);
}
