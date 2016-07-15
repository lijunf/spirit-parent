package com.lucien.spirit.core.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 条件接口 用户提供条件表达式接口.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:20:43
 * <p>Version: 1.0
 */
public interface Criterion {
    /**
     * 条件类型枚举.
     * <p>User: lijunf
     * <p>Date: 2016年2月24日 下午4:21:36
     * <p>Version: 1.0
     */
    enum Operator {
        EQ, NE, LIKE, GT, LT, GTE, LTE, AND, OR
    }

    /**
     * 生成Predicate.
     * @param root
     * @param query
     * @param builder
     * @return
     */
    Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder);
}
