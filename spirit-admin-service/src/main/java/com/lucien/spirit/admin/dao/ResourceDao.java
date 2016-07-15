package com.lucien.spirit.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.admin.model.Resource;

@Repository
public interface ResourceDao extends JpaRepository<Resource, Long> {

    /**
     * 查询顶级菜单
     * @return
     */
	@Query("SELECT r FROM Resource r WHERE r.parent is null)")
	public List<Resource> findTopList();
	
	/**
	 * 删除资源与角色的绑定关系
	 * @param id
	 */
	@Modifying
	@Query(nativeQuery=true, value="DELETE FROM sys_role_resources WHERE RESOURCE_ID = :id")
	public void deleteRoleById(@Param("id") Long id);
}
