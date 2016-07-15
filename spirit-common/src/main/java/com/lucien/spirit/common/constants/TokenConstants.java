package com.lucien.spirit.common.constants;

/**
 * 防重复提交Token相关常量类.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:03:58
 * <p>Version: 1.0
 */
public interface TokenConstants {

    /**
     * 默认防重复提交的token名称.
     */
    String DEFAULT_TOKEN_NAME = "spirit.token";

    /**
     * 重复提交表单提示的错误信息.
     */
    String FORM_INVALID = "表单已失效，请刷新后再试！";
}
