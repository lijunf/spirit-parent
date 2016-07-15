package com.lucien.spirit.core.taglib;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.lucien.spirit.common.constants.TokenConstants;

/**
 * 表单防重复提交Token标签.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:42:14
 * <p>Version: 1.0
 */
public class TokenTag extends TagSupport {

    /**
     * 序列号.
     */
    private static final long serialVersionUID = -5060882405545964960L;
    
    /**
     * 文本框模板.
     */
    public static String INPUT_TEXT = "<input type=\"hidden\" name=\"{0}\" value=\"{1}\">";

    public int doStartTag() throws JspException {
        String tokenValue = UUID.randomUUID().toString();
        
        pageContext.getSession().setAttribute(TokenConstants.DEFAULT_TOKEN_NAME, tokenValue);
        
        String text = MessageFormat.format(INPUT_TEXT, TokenConstants.DEFAULT_TOKEN_NAME, tokenValue);;

        JspWriter out = pageContext.getOut();
        try {
            out.print(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SKIP_BODY;
    }
}
