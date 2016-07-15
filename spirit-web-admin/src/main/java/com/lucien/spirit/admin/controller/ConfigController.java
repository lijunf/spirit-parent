package com.lucien.spirit.admin.controller;

import java.util.List;

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

import com.lucien.spirit.admin.model.Config;
import com.lucien.spirit.admin.service.ConfigService;

@Controller
@RequestMapping("/system/config")
public class ConfigController {

    @Autowired
    ConfigService configService;

    protected static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @RequestMapping("/list")
    public String list(Model model) {
    	List<Config> configList = configService.findAll();
        model.addAttribute("configList", configList);
        return "/system/config/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        Config config = new Config();
        model.addAttribute(config);
        return "/system/config/form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@Valid Config config, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getModel());
            return "/system/config/form";
        }
        Config temp = configService.findByKey(config.getKey());
        if (temp != null && temp.getId() != null) {
        	model.addAttribute("message", config.getKey() + "已经存在");
        	return "/system/config/form";
        }
        configService.save(config);
        return "redirect:/system/config/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
    	Config config = configService.findOne(id);
        model.addAttribute(config);
        return "/system/config/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@Valid Config config, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getModel());
            return "/system/config/form";
        }
        Config temp = configService.findByKey(config.getKey());
        if (temp != null && temp.getId() != config.getId()) {
        	model.addAttribute("message", config.getKey() + "已经存在");
        	return "/system/config/form";
        }
        configService.save(config);
        return "redirect:/system/config/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
    	configService.delete(id);
        return "redirect:/system/config/list";
    }

}
