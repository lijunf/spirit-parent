package com.lucien.spirit.admin.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lucien.spirit.admin.model.Resource;
import com.lucien.spirit.admin.service.ResourceService;

@Controller
@RequestMapping("/security/resource")
public class ResourceController {
    
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
    
    @Autowired
    ResourceService resourceService;

	@RequestMapping("/list")
    public String list(Model model) {
        return "/security/resource/list";
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model, Long pId) {
		Resource parent = null;
		Resource resource = new Resource();
		if (pId == null) {
			parent = new Resource();
			resource.setResType(Resource.TYPE_MENU);
		} else {
			parent = resourceService.findOne(pId);
			if (parent.getHref().equals("/")) {
				resource.setResType(Resource.TYPE_MENU);
			} else {
				resource.setResType(Resource.TYPE_BTN);
			}
		}
        model.addAttribute(resource);
        model.addAttribute("parent", parent);
        return "/security/resource/form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@Valid Resource resource, Long pId, BindingResult bindingResult, Model model) {
        logger.debug("create resource {}", resource);
        if (bindingResult.hasErrors()) {
            logger.warn("Error:{}", bindingResult.getModel());
            model.addAllAttributes(bindingResult.getModel());
            return "/security/resource/form";
        }
        if (pId != null) {
            Resource parent = new Resource(pId);
            resource.setParent(parent);
        }
        resourceService.save(resource);
        resourceService.refreshResourceCache();
        return "redirect:/security/resource/list";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
    	Resource resource = resourceService.findOne(id);
    	Resource parent = resource.getParent();
        model.addAttribute(resource);
        model.addAttribute("parent", parent);
        return "/security/resource/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@Valid Resource resource, Long pId, BindingResult bindingResult, Model model) {
        logger.debug("edit resource={}", resource);
        if (bindingResult.hasErrors()) {
            logger.warn("validation error={}", bindingResult.getModel());
            model.addAllAttributes(bindingResult.getModel());
            return "security/resource/form";
        }
        if (pId != null) {
            Resource parent = new Resource(pId);
            resource.setParent(parent);
        }
        resourceService.update(resource);
        resourceService.refreshResourceCache();
        return "redirect:/security/resource/list";
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model model) {
		logger.debug("delete resource");
        resourceService.delete(id);
        resourceService.refreshResourceCache();
        return "redirect:/security/resource/list";
    }
}
