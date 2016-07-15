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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.lucien.spirit.admin.model.DictEntry;
import com.lucien.spirit.admin.model.DictType;
import com.lucien.spirit.admin.service.DictEntryService;
import com.lucien.spirit.admin.service.DictTypeService;
import com.lucien.spirit.common.constants.DictConstants;

@Controller
@RequestMapping("/system/dict")
public class DictController {

    @Autowired
    DictTypeService dictTypeService;
    @Autowired
    DictEntryService dictEntryService;

    protected static final Logger logger = LoggerFactory.getLogger(DictController.class);

    @RequestMapping("/list")
    public String list(Model model) {
    	List<DictType> dictTypeList = dictTypeService.findAll();
        model.addAttribute("dictTypeList", dictTypeList);
        return "/system/dict/list";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
    	DictType dictType = new DictType();
        model.addAttribute(dictType);
        return "/system/dict/form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@Valid DictType dictType, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getModel());
            return "/system/dict/form";
        }
        DictType temp = dictTypeService.findOne(dictType.getDictTypeId());
        if (temp != null && dictType.getDictTypeId() != null) {
        	model.addAttribute("message", dictType.getDictTypeId() + "已经存在");
        	return "/system/dict/form";
        }
        dictTypeService.save(dictType);
        return "redirect:/system/dict/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") String id, Model model) {
    	DictType dictType = dictTypeService.findOne(id);
        model.addAttribute(dictType);
        return "/system/dict/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@Valid DictType dictType, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getModel());
            return "/system/dict/form";
        }
        DictType temp = dictTypeService.findOne(dictType.getDictTypeId());
        if (temp != null && temp.getDictTypeId() != dictType.getDictTypeId()) {
        	model.addAttribute("message", dictType.getDictTypeId() + "已经存在");
        	return "/system/dict/form";
        }
        dictTypeService.save(dictType);
        return "redirect:/system/dict/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) {
    	dictTypeService.delete(id);
        return "redirect:/system/dict/list";
    }
    
    @RequestMapping("/entry/list/{dictTypeId}")
    public String listEntry(Model model, @PathVariable("dictTypeId") String dictTypeId) {
        List<DictEntry> dictEntryList = dictEntryService.findByDictTypeId(dictTypeId);
        model.addAttribute("dictEntryList", dictEntryList);
        model.addAttribute("dictTypeId", dictTypeId);
        return "/system/dict/entry/list";
    }

    @RequestMapping(value = "/entry/create", method = RequestMethod.POST)
    public String saveEntry(@Valid DictEntry dictEntry, String dictTypeId, BindingResult bindingResult, Model model) {
        DictEntry dictEntryTemp = dictEntryService.findOne(dictTypeId, dictEntry.getDictId());
        if (dictEntryTemp != null && dictEntryTemp.getId() != null) {
            // TODO 字典常量已经存在，给用户提示信息
        } else {
            dictEntry.setDictType(dictTypeService.findOne(dictTypeId));
            dictEntry.setStatus(DictConstants.STATUS_ENABLE);
            dictEntryService.save(dictEntry);
        }
        return "redirect:/system/dict/entry/list/" + dictTypeId;
    }
    
    @RequestMapping(value = "/entry/edit/{dictTypeId}/{dictId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String editEntry(@PathVariable("dictTypeId") String dictTypeId, @PathVariable("dictId") String dictId) {
        DictEntry dictEntry = dictEntryService.findOne(dictTypeId, dictId);
        String json = JSON.toJSONString(dictEntry);
        return json;
    }
    
    @RequestMapping(value = "/entry/edit", method = RequestMethod.POST)
    public String updateEntry(@Valid DictEntry dictEntry, String dictTypeId, BindingResult bindingResult, Model model) {
        dictEntry.setDictType(dictTypeService.findOne(dictTypeId));
        dictEntry.setStatus(DictConstants.STATUS_ENABLE);
        dictEntryService.save(dictEntry);
        return "redirect:/system/dict/entry/list/" + dictTypeId;
    }
    
    @RequestMapping(value = "/entry/delete/{dictTypeId}/{id}", method = RequestMethod.GET)
    public String deleteEntry(@PathVariable("dictTypeId") String dictTypeId, @PathVariable("id") Long id) {
        dictEntryService.delete(id);
        return "redirect:/system/dict/entry/list/" + dictTypeId;
    }
}
