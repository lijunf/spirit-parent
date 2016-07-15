package com.lucien.spirit.admin.model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotEmpty;

import com.lucien.spirit.common.model.BaseModel;

@Entity
@Table(name = "sys_roles", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) })
// @Cacheable
// @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, include = "non-lazy")
public class Role extends BaseModel {

    private static final long serialVersionUID = 7788827369928527248L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NaturalId
    @NotEmpty
    @Column(name = "NAME")
    @Size(min = 2)
    private String name;

    @Basic(optional = true)
    @Column(length = 255, name = "DESCRIPTION")
    private String description;

    /**
	 * 拥有权限
	 */
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	// @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
	@JoinTable(name = "sys_role_resources", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "RESOURCE_ID") })
	private List<Resource> resource;

    @Version
    @Column(name = "OPT_LOCK")
    private int versionNum = 0;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
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

    public List<Resource> getResource() {
		return resource;
	}

	public void setResource(List<Resource> resource) {
		this.resource = resource;
	}

	public int getVersionNum() {

        return versionNum;
    }

    public void setVersionNum(int versionNum) {

        this.versionNum = versionNum;
    }

    /**
     * @return the roles List
     */
    @Transient
    public String getViewPermissions() {

        StringBuilder sb = new StringBuilder("");

        if (resource != null && resource.size() > 0) {
        	for (Iterator<Resource> it = getResource().iterator(); it.hasNext();) {
        		sb.append(it.next().getId());
                if (it.hasNext())
                    sb.append(",");
            }
        }

        return sb.toString();
    }
    
    public String getViewResources() {
        StringBuilder sb = new StringBuilder("");

        if (resource != null && resource.size() > 0) {
	        for (Iterator<Resource> it = getResource().iterator(); it.hasNext();) {
	            sb.append(it.next().getName());
	            if (it.hasNext())
	                sb.append(",");
	        }
        }
        
        return sb.toString();
    }
}
