package com.lucien.spirit.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lucien.spirit.admin.service.UserService;
import com.lucien.spirit.core.shiro.ShiroUser;

@Controller
public class LoginController {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:/home";
        }
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, String username, String password, boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            return "redirect:/home";
        }

        String loginKaptchaCode = request.getParameter("code");

        Session shiroSession = subject.getSession();
        Object kaptchaCode = shiroSession.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        if (kaptchaCode == null || !StringUtils.equalsIgnoreCase(loginKaptchaCode, kaptchaCode.toString())) {
            model.addAttribute("message", "验证码错误!");
            return "/login";
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe, request.getRemoteHost());
        try {
            subject.login(token);
            ShiroUser principal = (ShiroUser) subject.getPrincipal();
            userService.updateLastLogin(principal.getId());
            
            logger.info("Login successful!");
            return "redirect:/home";
        } catch (UnknownAccountException uae) {
            model.addAttribute("message", "用户不存在");
            logger.info("{} Unknown User!", username);
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("message", "密码不正确");
            logger.info("{} Incorrect Password!", username);
        } catch (LockedAccountException lae) {
            model.addAttribute("message", "用户被锁定");
            logger.info("{} User Locked!", username);
        } catch (AccountException ae) {
        	model.addAttribute("message", ae.getMessage());
            logger.info("{} {}", username, ae.getMessage());
		} catch (AuthenticationException ae) {
            model.addAttribute("message", "登录失败");
            logger.info("{} Authentication Failed!", username);
        }
        return "/login";
    }
    
}
