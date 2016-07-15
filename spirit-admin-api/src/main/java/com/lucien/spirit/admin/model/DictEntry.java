package com.lucien.spirit.admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lucien.spirit.common.model.BaseModel;

/**
 * 数据字典值
 * @Title: DictEntry.java 
 * @Package com.lucien.spirit.module.system.model 
 * @Description: TODO 
 * @author lucien   
 * @date 2016年1月31日 下午10:02:40 
 * @version V1.0
 */
@Entity
@Table(name = "sys_dict_entry")
public class DictEntry extends BaseModel {

	private static final long serialVersionUID = -458427357935608210L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "DICTTYPEID")
	private DictType dictType;
	
	@Column(name = "DICTID", length = 128)
	private String dictId;
	
	@Column(name = "DICTNAME")
	private String dictName;
	
	@Column(name = "STATUS")
	private int status;
	
	@Column(name = "ORDERNO")
	private int orderNo;
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DictType getDictType() {
		return dictType;
	}

	public void setDictType(DictType dictType) {
		this.dictType = dictType;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
}
