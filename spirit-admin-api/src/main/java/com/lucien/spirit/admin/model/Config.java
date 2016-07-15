package com.lucien.spirit.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

import com.lucien.spirit.common.model.BaseModel;

/**
 * 系统参数实体
 * @Title: Config.java 
 * @Package com.lucien.spirit.module.system.model 
 * @Description: TODO 
 * @author lucien   
 * @date 2016年1月31日 下午10:31:47 
 * @version V1.0
 */
@Entity
@Table(name = "sys_config", uniqueConstraints = { @UniqueConstraint(columnNames = { "PKEY" }) })
public class Config extends BaseModel {

	private static final long serialVersionUID = -1674759655161039983L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "PKEY", unique = true)
	@NotEmpty
	private String key;
	
	@Column(name = "PVALUE")
	@NotEmpty
	private String value;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATOR")
	private String creator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
