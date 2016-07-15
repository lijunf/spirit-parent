package com.lucien.spirit.core.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lucien.spirit.common.constants.TokenConstants;

/**
 * 防重复提交Token处理类.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:32:42
 * <p>Version: 1.0
 */
public class TokenHandler {

    /**
     * 日志对象.
     */
    private static final Logger logger = LoggerFactory.getLogger(TokenHandler.class);

    /**
     * 验证表单中的token是否合法.
     * @return  合法返回null
     */
    public static String validToken(HttpServletRequest request) {
        String token = request.getParameter(TokenConstants.DEFAULT_TOKEN_NAME);
        
        String msg = TokenConstants.FORM_INVALID;
        if (token == null) {
            logger.warn("表单已失效，token不能为空");
            return msg;
        }
        HttpSession session = request.getSession();
        String sessionToken = (String) session.getAttribute(TokenConstants.DEFAULT_TOKEN_NAME);
        if (!token.equals(sessionToken)) {
            logger.warn("表单已失效，input_token:" + token + ", session_token:" + sessionToken);
            return msg;
        }
        if (sessionToken != null) {
            session.removeAttribute(TokenConstants.DEFAULT_TOKEN_NAME);
        }
        return null;
    }

    /**
     * 重新设置token.
     * @param token
     */
    public static void resetToken(HttpServletRequest request) {
        String token = request.getParameter(TokenConstants.DEFAULT_TOKEN_NAME);
        if (token != null && token.length() > 0) {
            HttpSession session = request.getSession();
            session.setAttribute(TokenConstants.DEFAULT_TOKEN_NAME, token);
            logger.debug("表单token重置");
        }
    }
}
