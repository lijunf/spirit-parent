package com.lucien.spirit.admin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lucien.spirit.common.model.BaseModel;

/**
 * 系统日志实体
 * @Filename : Log.java
 * @Package : com.lucien.spirit.module.system.model
 * @Description : TODO
 * @author : lijunf
 * @CreateDate : 2016年2月14日
 */
@Entity
@Table(name = "sys_log")
public class Log extends BaseModel {

    private static final long serialVersionUID = 2553497189197593032L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "LOGINID")
    private String loginId;
    
    @Column(name = "IPADDR")
    private String ipAddr;
    
    @Column(name = "PRIORITY")
    private String priority;

    @Column(name = "LOGDATE")
    private Date logDate;
    
    @Column(name = "CLASS")
    private String clazz;
    
    @Column(name = "METHOD")
    private String method;
    
    @Column(name = "MSG")
    private String msg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
