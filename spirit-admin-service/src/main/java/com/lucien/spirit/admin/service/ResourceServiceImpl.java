package com.lucien.spirit.admin.service;

import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.lucien.spirit.admin.dao.ResourceDao;
import com.lucien.spirit.admin.model.Resource;

@Service
public class ResourceServiceImpl implements ResourceService {
    
    @Autowired
    ResourceDao resourceDao;
    
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Resource> findTopList() {
        List<Resource> topResourceList = resourceDao.findTopList();
        for (Resource resource : topResourceList) {
            List<Resource> subReses = resource.getSubResource();
            if (subReses != null) {
                for (Resource subRes : subReses) {
                    List<Resource> subReses2 = subRes.getSubResource();
                    if (subReses2 != null) {
                        for (Resource subRes2 : subReses2) {
                            Hibernate.initialize(subRes2);     // 强制刷新整棵资源数，只支持三层结构
                        }
                    }
                }
            }
        }
        return topResourceList;
    }

    @Transactional
    public void delete(Long id) {
        resourceDao.deleteRoleById(id);
        resourceDao.delete(id);
    }

	public void save(Resource resource) {
		resourceDao.save(resource);
	}
	
	public void update(Resource resource) {
	    Resource temp = resourceDao.findOne(resource.getId());
	    temp.setParent(resource.getParent());
	    temp.setResType(resource.getResType());
	    temp.setName(resource.getName());
	    temp.setPermission(resource.getPermission());
	    temp.setHref(resource.getHref());
	    temp.setOrderNo(resource.getOrderNo());
	    temp.setIconCls(resource.getIconCls());
	    temp.setDescription(resource.getDescription());
	    resourceDao.save(temp);
	}

	public Resource findOne(Long id) {
		return resourceDao.findOne(id);
	}
	
	/**
	 * 刷新资源树全局缓存
	 * @param context
	 */
    public void refreshResourceCache() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        if (webApplicationContext != null) {
            ServletContext context = webApplicationContext.getServletContext();
            context.setAttribute("topResourceList", findTopList());
        }
	    // TODO 现在是所有资源一起刷新，以后考虑只刷新单个
		/*List<Resource> topResourceList = (List<Resource>) context.getAttribute("topResourceList");
		for (Resource resource : topResourceList) {
		    if (resource.getId() == id) {
		        topResourceList.remove(resource);
		        resource = findOne(id);
		        topResourceList.add(resource);
		        return;
		    }
		}*/
	}

}
