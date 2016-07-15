package com.lucien.spirit.listener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.shiro.crypto.hash.Sha512Hash;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lucien.spirit.admin.dao.RoleDao;
import com.lucien.spirit.admin.model.Resource;
import com.lucien.spirit.admin.model.Role;
import com.lucien.spirit.admin.model.User;
import com.lucien.spirit.admin.service.ResourceService;
import com.lucien.spirit.admin.service.UserService;
import com.lucien.spirit.common.constants.UserConstants;

/**
 * 系统初始化数据,数据库不限.登录账户为 admin 密码admin.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:27:33
 * <p>Version: 1.0
 */
public class SysInitListener implements ServletContextListener {
    
    /*
     * (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        ResourceService resourceService = webApplicationContext.getBean(ResourceService.class);
        UserService userService = webApplicationContext.getBean(UserService.class);
        List<Resource> topResourceList = resourceService.findTopList();
        // TODO 考虑采用ehcache缓存
        sce.getServletContext().setAttribute("topResourceList", topResourceList);
        
        User user = userService.findByName("admin");
        if (user != null && user.getId() != null) {
        	return;
        }
        
        /*PersonService personService = webApplicationContext.getBean(PersonService.class);

        List<Person> personList = new ArrayList<Person>();
        for (int i = 0; i < 100; i++) {
            String chars = "abcdefghijklmnopqrstuvwxyz";
            String name = String.valueOf(chars.charAt((int) (Math.random() * 26)));
            Person person = new Person();
            person.setName(name);
            person.setAge((i % 100) + 1);
            personList.add(person);
        }
        personService.save(personList);*/

        List<Resource> resources = resourceService.findAll();

        RoleDao roleDao = webApplicationContext.getBean(RoleDao.class);
        Role role = new Role();
        role.setName("admin");
        role.setDescription("系统管理员");
        role.setResource(resources);
        roleDao.saveAndFlush(role);

        Set<Role> roles = new HashSet<Role>();
        roles.add(role);

        byte[] passwordSalt = UUID.randomUUID().toString().getBytes();
        user = new User();
        // TODO 改到配置文件中去
        user.setRoles(roles);
        user.setName("admin");
        user.setPasswordSalt(passwordSalt);
        user.setPassword("admin");
        user.setRealName("李俊锋");
        user.setMobile("13524595283");
        user.setEmail("lijunf@163.com");
        user.setStatus(UserConstants.STATUS_ENABLE);
        String passwordHash = new Sha512Hash(user.getPassword(), user.getName() + new String(passwordSalt), 99)
                .toString();
        user.setPasswordHash(passwordHash);
        userService.save(user);
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
