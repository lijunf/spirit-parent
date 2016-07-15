package com.lucien.spirit.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lucien.spirit.admin.model.Resource;
import com.lucien.spirit.admin.model.Role;
import com.lucien.spirit.admin.service.ResourceService;
import com.lucien.spirit.admin.service.RoleService;
import com.lucien.spirit.shiro.realm.JpaRealm;

@Controller
@RequestMapping("/security/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    ResourceService resourceService;
    
    @Autowired
    JpaRealm jpaRealm;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList", roleList);
        return "/security/role/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        Role role = new Role();
        model.addAttribute(role);
        return "/security/role/form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(Model model, HttpServletRequest request) {
        String name = request.getParameter("roleName");
        String desc = request.getParameter("roleDesc");
        String resourceStr = request.getParameter("resourceStr");
        Role role = new Role();
        role.setName(name);
        role.setDescription(desc);
        
        String[] resourceArray = resourceStr.split(",");
        List<Resource> resources = new ArrayList<Resource>();
        for (String resId : resourceArray) {
            Resource resource = new Resource(Long.parseLong(resId));
            resources.add(resource);
        }
        role.setResource(resources);
        roleService.save(role);
        jpaRealm.clearAllCachedAuthorizationInfo();
        return "redirect:/security/role/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        Role role = roleService.findOne(id);
        model.addAttribute("role", role);
        return "/security/role/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(Model model, @PathVariable("id") Long id, 
    		String roleName, String roleDesc, String resourceStr) {
        Role role = this.roleService.findOne(id);
        role.setName(roleName);
        role.setDescription(roleDesc);
        
        String[] resourceArray = resourceStr.split(",");
        List<Resource> resources = new ArrayList<Resource>();
        for (String resId : resourceArray) {
            Resource resource = new Resource(Long.parseLong(resId));
            resources.add(resource);
        }
        role.setResource(resources);
        this.roleService.save(role);
        jpaRealm.clearAllCachedAuthorizationInfo();
        return "redirect:/security/role/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model model) {
        logger.debug("delete successful!");
        roleService.delete(id);
        return "redirect:/security/role/list";
    }
}
