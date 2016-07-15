package com.lucien.spirit.common.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 实体基类,重写toString,hashCode,equals方法.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:30:04
 * <p>Version: 1.0
 */
public class BaseModel implements Serializable {

    /**
     * 自动生成的序列号
     */
    private static final long serialVersionUID = 7369795311057059048L;

    /**
     * 重写toString方法.
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * 重写hashCode方法.
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * 重写equals方法.
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
