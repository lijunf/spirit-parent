package com.lucien.spirit.admin.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lucien.spirit.admin.model.Log;
import com.lucien.spirit.admin.service.LogService;
import com.lucien.spirit.common.constants.FormatConstant;
import com.lucien.spirit.common.constants.PageConstants;
import com.lucien.spirit.common.utils.DateUtil;
import com.lucien.spirit.common.utils.StringUtil;

@Controller
@RequestMapping("/system/log")
public class LogController {

    @Autowired
    LogService logService;

    protected static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) Integer page, Model model, 
            Log log, String beginDate, String endDate) {
        int pageNumber = page != null ? page : PageConstants.DEFAULT_PAGE_NUM;

        Date begin = StringUtil.isNotBlank(beginDate) ? DateUtil.parseDate(beginDate + " 00:00:00") : null;
        Date end = StringUtil.isNotBlank(endDate) ? DateUtil.parseDate(endDate + " 23:59:59") : null;
        
        Page<Log> paging = logService.findAllForPagination(pageNumber, PageConstants.DEFAULT_PAGE_SIZE, log, begin, end);
        model.addAttribute("paging", paging);
        model.addAttribute("log", log);
        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        return "/system/log/list";
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat(FormatConstant.DEFALUT_DATETIME_PATTERN);
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
