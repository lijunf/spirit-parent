package com.lucien.spirit.shiro.spring;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucien.spirit.admin.dao.ResourceDao;
import com.lucien.spirit.admin.model.Resource;

/**
 * 动态创建shiro filterchaindefinitions.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:39:07
 * <p>Version: 1.0
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

    /**
     * 资源Dao
     */
    @Autowired
    ResourceDao resourceDao;
    
    /**
     * 参数配置的shiro权限规则
     */
    private String filterChainDefinitions;
    
    /** 
     * 默认premission字符串 
     */  
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";  

    /**
     * 权限map
     */
    public Section getObject() throws Exception {
        Ini ini = new Ini();  
        //加载默认的url  
        ini.load(filterChainDefinitions);  
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);

        List<Resource> resources = resourceDao.findAll();
        if (resources == null || resources.size() == 0) {
            initResource();
            resources = resourceDao.findAll();
        }
        for (Resource resource : resources) {
        	String url = resource.getHref();
        	if (url.contains(";")) {
        		// 如果资源权限中保护分号，则代表多个权限
        		String[] urls = url.split(";");
        		for (String _url : urls) {
        			section.put(_url, MessageFormat.format(PREMISSION_STRING, resource.getPermission()));
        		}
        	} else {
        		section.put(url, MessageFormat.format(PREMISSION_STRING, resource.getPermission()));
        	}
        }
        return section;
    }
    
    /**
     * 初始化资源
     */
    private void initResource() {
        Resource resource = null;
        Resource permRes = new Resource("permission:manage", "权限管理", "/", null, Resource.TYPE_MENU, 1, "glyphicon-cog");
        resourceDao.saveAndFlush(permRes);
        
        Resource userResource = new Resource("user:query", "用户管理", "/security/user/list", permRes.getId(), Resource.TYPE_MENU, 1, "glyphicon-user");
        resourceDao.saveAndFlush(userResource);
        resource = new Resource("user:add", "添加用户", "/security/user/create", userResource.getId(), Resource.TYPE_BTN, 1);
        resourceDao.save(resource);
        resource = new Resource("user:edit", "编辑用户", "/security/user/edit/**;/security/user/grant/**", userResource.getId(), Resource.TYPE_BTN, 2);
        resourceDao.save(resource);
        resource = new Resource("user:delete", "删除用户", "/security/user/delete/**", userResource.getId(), Resource.TYPE_BTN, 3);
        resourceDao.save(resource);
        
        Resource roleResource = new Resource("role:query", "角色管理", "/security/role/list", permRes.getId(), Resource.TYPE_MENU, 2, "glyphicon-asterisk");
        resourceDao.saveAndFlush(roleResource);
        resource = new Resource("role:add", "添加角色", "/security/role/create", roleResource.getId(), Resource.TYPE_BTN, 1);
        resourceDao.save(resource);
        resource = new Resource("role:edit", "编辑角色", "/security/role/edit/**", roleResource.getId(), Resource.TYPE_BTN, 2);
        resourceDao.save(resource);
        resource = new Resource("role:delete", "删除角色", "/security/role/delete/**", roleResource.getId(), Resource.TYPE_BTN, 3);
        resourceDao.save(resource);
        
        Resource resourceResource = new Resource("resource:query", "资源管理", "/security/resource/list", permRes.getId(), Resource.TYPE_MENU, 3, "glyphicon-th-list");
        resourceDao.saveAndFlush(resourceResource);
        resource = new Resource("resource:add", "添加资源", "/security/resource/create", resourceResource.getId(), Resource.TYPE_BTN, 1);
        resourceDao.save(resource);
        resource = new Resource("resource:edit", "编辑资源", "/security/resource/edit/**", resourceResource.getId(), Resource.TYPE_BTN, 2);
        resourceDao.save(resource);
        resource = new Resource("resource:delete", "删除资源", "/security/resource/delete/**", resourceResource.getId(), Resource.TYPE_BTN, 3);
        resourceDao.save(resource);
        
        Resource sysRes = new Resource("system:manage", "系统管理", "/", null, Resource.TYPE_MENU, 2, "glyphicon-wrench");
        resourceDao.saveAndFlush(sysRes);
        
        Resource configResource = new Resource("config:query", "系统参数", "/system/config/list", sysRes.getId(), Resource.TYPE_MENU, 1, "glyphicon-glass");
        resourceDao.saveAndFlush(configResource);
        resource = new Resource("config:add", "添加参数", "/system/config/create", configResource.getId(), Resource.TYPE_BTN, 1);
        resourceDao.save(resource);
        resource = new Resource("config:edit", "编辑参数", "/system/config/edit/**", configResource.getId(), Resource.TYPE_BTN, 2);
        resourceDao.save(resource);
        resource = new Resource("config:delete", "删除参数", "/system/config/delete/**", configResource.getId(), Resource.TYPE_BTN, 3);
        resourceDao.save(resource);
        
        Resource dictResource = new Resource("dict:query", "字典管理", "/system/dict/list", sysRes.getId(), Resource.TYPE_MENU, 2, "glyphicon-hdd");
        resourceDao.saveAndFlush(dictResource);
        resource = new Resource("dict:add", "添加字典", "/system/dict/create", dictResource.getId(), Resource.TYPE_BTN, 1);
        resourceDao.save(resource);
        resource = new Resource("dict:edit", "编辑字典", "/system/dict/edit/**", dictResource.getId(), Resource.TYPE_BTN, 2);
        resourceDao.save(resource);
        resource = new Resource("dict:delete", "删除字典", "/system/dict/delete/**", dictResource.getId(), Resource.TYPE_BTN, 3);
        resourceDao.save(resource);
        
        Resource logResource = new Resource("log:query", "系统日志", "/system/log/list", sysRes.getId(), Resource.TYPE_MENU, 3, "glyphicon-italic");
        resourceDao.save(logResource);
        
        Resource cusRes = new Resource("person:manage", "客户关系", "/", null, Resource.TYPE_MENU, 3, "glyphicon-star");
        resourceDao.saveAndFlush(cusRes);
        
        Resource personResource = new Resource("person:query", "客户管理", "/person/list", cusRes.getId(), Resource.TYPE_MENU, 1, "glyphicon-user");
        resourceDao.saveAndFlush(personResource);
        resource = new Resource("person:add", "添加客户", "/person/create", personResource.getId(), Resource.TYPE_BTN, 1);
        resourceDao.save(resource);
        resource = new Resource("person:edit", "编辑客户", "/person/edit/**", personResource.getId(), Resource.TYPE_BTN, 2);
        resourceDao.save(resource);
        resource = new Resource("person:delete", "删除客户", "/person/delete/**", personResource.getId(), Resource.TYPE_BTN, 3);
        resourceDao.save(resource);
        
        
    }
    
    /** 
     * 通过filterChainDefinitions对默认的url过滤定义 
     * @param filterChainDefinitions 默认的url过滤定义 
     */  
    public void setFilterChainDefinitions(String filterChainDefinitions) {  
        this.filterChainDefinitions = filterChainDefinitions;  
    } 

    public Class<?> getObjectType() {
        return Map.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
