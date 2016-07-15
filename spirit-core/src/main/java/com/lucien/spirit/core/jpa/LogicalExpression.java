package com.lucien.spirit.core.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 逻辑条件表达式 用于复杂条件时使用，如但属性多对应值的OR查询等.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:22:43
 * <p>Version: 1.0
 */
public class LogicalExpression implements Criterion {

    /**
     * 逻辑表达式中包含的表达式.
     */
    private Criterion[] criterion;
    /**
     * 计算符.
     */
    private Operator operator;

    /**
     * 构造方法.
     * @param criterions
     * @param operator
     */
    public LogicalExpression(Criterion[] criterions, Operator operator) {
        this.criterion = criterions;
        this.operator = operator;
    }

    /*
     * (non-Javadoc)
     * @see com.lucien.spirit.core.jpa.Criterion#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery, javax.persistence.criteria.CriteriaBuilder)
     */
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (int i = 0; i < this.criterion.length; i++) {
            predicates.add(this.criterion[i].toPredicate(root, query, builder));
        }
        switch (operator) {
        case OR:
            return builder.or(predicates.toArray(new Predicate[predicates.size()]));
        default:
            return null;
        }
    }
}
