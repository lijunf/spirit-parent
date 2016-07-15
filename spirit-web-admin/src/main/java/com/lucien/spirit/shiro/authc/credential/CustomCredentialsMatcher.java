package com.lucien.spirit.shiro.authc.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucien.spirit.admin.dao.UserDao;
import com.lucien.spirit.admin.model.User;
import com.lucien.spirit.core.shiro.ShiroUser;

/**
 * 验证密码服务,可以提供密码错误登录次数的限制.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:33:44
 * <p>Version: 1.0
 */
public class CustomCredentialsMatcher extends HashedCredentialsMatcher {
	
    /**
     * 用户Dao.
     */
	@Autowired
    UserDao userDao;

	/**
	 * 验证密码不能出错3次.
	 */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        boolean result = super.doCredentialsMatch(token, info);
        ShiroUser principal = (ShiroUser) info.getPrincipals().getPrimaryPrincipal();
        if (!result) {
        	User user = userDao.findOne(principal.getId());
            if (user.getErrorNum() > 2) {	
            	throw new LockedAccountException();	// 密码输错3次
            } else {
            	user.setErrorNum(user.getErrorNum() + 1);
            	userDao.save(user);
            }
        } else {
        	if (principal.getErrorNo() != 0) {
        		User user = userDao.findOne(principal.getId());
        		user.setErrorNum(0);
        		userDao.save(user);
        		principal.setErrorNo(0);
        	}
        }
        return result;
    }
}
