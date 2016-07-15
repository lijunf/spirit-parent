package com.lucien.spirit.admin.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.lucien.spirit.common.model.BaseModel;

/**
 * 用户后台资源资源权限表--RBAC权限管理实体
 * 
 * @author lucien
 *
 */
@Entity
@Table(name = "sys_resources")
// @Cacheable
// @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, include = "non-lazy")
public class Resource extends BaseModel {

	private static final long serialVersionUID = -7699511964868984541L;
	
	/** 资源类型-菜单级别 */
	public static int TYPE_MENU = 1;
	/** 资源类型-按钮级别 */
	public static int TYPE_BTN = 2;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	/**
	 * shiro权限
	 */
	@Column(length = 20, name = "PERMISSION")
	private String permission;

	/**
	 * 资源类型
	 */
	@Column(name = "RES_TYPE")
	private Integer resType;

	/**
	 * 权限名称
	 */
	@Column(length = 200, name = "NAME")
	private String name;

	/**
	 * 权限描述
	 */
	@Column(length = 200, name = "DESCRIPTION")
	private String description;
	
	@Column(length = 50, name = "ICON_CLS")
	private String iconCls;

	/**
	 * 排序
	 */
	@Column(length = 3, name = "ORDER_NO")
	private Integer orderNo;

	/**
	 * 父节点
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private Resource parent;

	/**
	 * 子节点
	 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("orderNo")
	private List<Resource> subResource;

	/**
	 * 链接
	 */
	@Column(length = 200, name = "HREF")
	private String href;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime = new Date();

	@Version
	@Column(name = "OPT_LOCK")
	private int versionNum = 0;
	
	public Resource() {
        
    }
    
    public Resource(Long id) {
        this.id = id;
    }
    
    public Resource(String permission, String name, String href, Long pid, Integer resType, Integer orderNo) {
        this.permission = permission;
        this.name = name;
        this.description = name;
        this.href = href;
        if (pid != null) {
            this.parent = new Resource(pid);
        }
        this.resType = resType;
        this.orderNo = orderNo;
    }
    
    public Resource(String permission, String name, String href, Long pid, Integer resType, Integer orderNo, String iconCls) {
        this.permission = permission;
        this.name = name;
        this.description = name;
        this.href = href;
        if (pid != null) {
            this.parent = new Resource(pid);
        }
        this.resType = resType;
        this.orderNo = orderNo;
        this.iconCls = iconCls;
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getResType() {
		return resType;
	}

	public void setResType(Integer resType) {
		this.resType = resType;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
    public String getIconCls() {
        return iconCls;
    }
 
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public List<Resource> getSubResource() {
		return subResource;
	}

	public void setSubResource(List<Resource> subResource) {
		this.subResource = subResource;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}
	
    /**
	 * 获取资源层级显示名称
	 * @return
	 */
	public String getText() {
		if (parent == null) {
			return name;
		} else {
			if (TYPE_MENU == resType) {
				return "——" + name;
			} else {
				return "————" + name;
			}
		}
	}
}
