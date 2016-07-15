package com.lucien.spirit.admin.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.lucien.spirit.common.model.BaseModel;

/**
 * 数据字典定义
 * @Title: DictType.java 
 * @Package com.lucien.spirit.module.system.model 
 * @Description: TODO 
 * @author lucien   
 * @date 2016年1月31日 下午10:02:28 
 * @version V1.0
 */
@Entity
@Table(name = "sys_dict_type")
public class DictType extends BaseModel {

	private static final long serialVersionUID = -8251831648431817959L;

	@Id
	@Column(name = "DICTTYPEID", length = 128)
	private String dictTypeId;
	
	@Column(name = "DICTTYPENAME")
	private String dictTypeName;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "ORDERNO")
	private int orderNo;
	
	@Column(name = "CREATOR")
	private String creator;
	
	@OneToMany(mappedBy = "dictType", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("orderNo")
	private List<DictEntry> dictEntrys;

	public String getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(String dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public List<DictEntry> getDictEntrys() {
        return dictEntrys;
    }

    public void setDictEntrys(List<DictEntry> dictEntrys) {
        this.dictEntrys = dictEntrys;
    }

    public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
