package com.lucien.spirit.admin.dao;


import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucien.spirit.admin.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	/**
	 * 根据用户名查询用户
	 * @param name
	 * @return
	 */
	public User findByName(String name);
	
	/**
	 * 修改最后登录时间
	 * @param id
	 * @param date
	 */
	@Modifying
	@Transactional
    @Query(nativeQuery=true, value="UPDATE sys_users SET LAST_LOGIN = :date WHERE ID = :id")
	public void updateLastLogin(@Param("id") Long id, @Param("date") Date date);
}
