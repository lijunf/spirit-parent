package com.lucien.spirit.core.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

/**
 * 简单条件表达式.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:25:45
 * <p>Version: 1.0
 */
public class SimpleExpression implements Criterion {

    /**
     * 属性名.
     */
    private String fieldName;
    
    /**
     * 对应值.
     */
    private Object value;
    
    /**
     * 计算符.
     */
    private Operator operator;

    /**
     * 构造函数.
     * @param fieldName
     * @param value
     * @param operator
     */
    protected SimpleExpression(String fieldName, Object value, Operator operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    /**
     * 获取属性名.
     * @return
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * 获取对应的值.
     * @return
     */
    public Object getValue() {
        return value;
    }

    /**
     * 获取操作符.
     * @return
     */
    public Operator getOperator() {
        return operator;
    }

    /*
     * (non-Javadoc)
     * @see com.lucien.spirit.core.jpa.Criterion#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery, javax.persistence.criteria.CriteriaBuilder)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path expression = null;
        if (fieldName.contains(".")) {
            String[] names = StringUtils.split(fieldName, ".");
            expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }
        } else {
            expression = root.get(fieldName);
        }

        switch (operator) {
        case EQ:
            return builder.equal(expression, value);
        case NE:
            return builder.notEqual(expression, value);
        case LIKE:
            return builder.like((Expression<String>) expression, "%" + value + "%");
        case LT:
            return builder.lessThan(expression, (Comparable) value);
        case GT:
            return builder.greaterThan(expression, (Comparable) value);
        case LTE:
            return builder.lessThanOrEqualTo(expression, (Comparable) value);
        case GTE:
            return builder.greaterThanOrEqualTo(expression, (Comparable) value);
        default:
            return null;
        }
    }
}
