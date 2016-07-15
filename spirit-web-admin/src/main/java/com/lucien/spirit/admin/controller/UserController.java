package com.lucien.spirit.admin.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSON;
import com.lucien.spirit.admin.model.Role;
import com.lucien.spirit.admin.model.User;
import com.lucien.spirit.admin.service.RoleService;
import com.lucien.spirit.admin.service.UserService;
import com.lucien.spirit.admin.utils.PasswordHelper;
import com.lucien.spirit.common.constants.PageConstants;
import com.lucien.spirit.common.constants.UserConstants;
import com.lucien.spirit.shiro.realm.JpaRealm;

@Controller
@RequestMapping("/security/user")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    JpaRealm jpaRealm;

    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "message", required = false) String message, Model model, User user) {
        int pageNumber = page != null ? page : PageConstants.DEFAULT_PAGE_NUM;
        Page<User> paging = userService.findForPagination(pageNumber, PageConstants.DEFAULT_PAGE_SIZE, user);
        model.addAttribute("paging", paging);
        if (message != null) {
            model.addAttribute("message", message);
        }
        model.addAttribute("user", user);
        return "/security/user/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid User user, BindingResult bindingResult, Model model) {
    	String message = null;
    	Map<String, String> map = new HashMap<String, String>();
    	User temp = userService.findByName(user.getName());
    	if (temp != null && temp.getId() != null) {
    		message = "用户 " + user.getName() + " 已经存在!";
    	} else {
    	    user.setStatus(UserConstants.STATUS_ENABLE);
    		user = PasswordHelper.generatePassword(user);
            userService.save(user);
            message = "用户 " + user.getName() + " 创建成功!";
    	}
        map.put("message", message);
        return new ModelAndView(new RedirectView("list"), map);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String edit(@PathVariable("id") Long id) {
        User user = userService.findOne(id);
        String json = JSON.toJSONString(user);
        logger.debug("{}", json);
        return json;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid User user) {
    	String message = null;
    	Map<String, String> map = new HashMap<String, String>();
    	User temp = userService.findByName(user.getName());
    	if (temp != null && temp.getId() != user.getId()) {
    		message = "用户 " + user.getName() + " 已经存在!";
    	} else {
    		userService.update(user);
    		// TODO 如果用户名称修改，需要更新待shiro缓存的用户名
    		// TODO 不生效 jpaRealm.clearCachedAuthenticationInfo(SecurityUtils.getSubject().getPrincipals());
    		// TODO 成功 jpaRealm.clearAllCachedAuthenticationInfo();
    		message = "用户 " + user.getName() + " 编辑成功!";
    	}
        map.put("message", message);
        return new ModelAndView(new RedirectView("list"), map);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model model) {
        userService.delete(id);
        return "redirect:/security/user/list";
    }
    
    @RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
    public String grant(Model model, @PathVariable("id") Long id) {
        User user = userService.findOneAndRole(id);
        model.addAttribute(user);
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "/security/user/grant";
    }

    @RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
    public String grant(Model model, Long[] roleId, @PathVariable("id") Long id) {
    	if (roleId != null && roleId.length > 0) {
    		Set<Role> roles = new HashSet<Role>();
    		for (Long rId : roleId) {
    			Role role = new Role();
    			role.setId(rId);
    			roles.add(role);
    		}
    		User user = userService.findOne(id);
    		user.setRoles(roles);
    		userService.save(user);
    		jpaRealm.clearAllCachedAuthorizationInfo();
    	}
    	return "redirect:/security/user/list";
    }
}
