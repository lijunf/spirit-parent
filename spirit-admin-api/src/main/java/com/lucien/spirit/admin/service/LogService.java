package com.lucien.spirit.admin.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.lucien.spirit.admin.model.Log;

public interface LogService {

	List<Log> findAll();

    Page<Log> findAllForPagination(int page, int size, Log log, Date beginDate, Date endDate);
}
