package com.lucien.spirit.admin.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.lucien.spirit.common.model.BaseModel;

@Entity
@Table(name = "sys_users", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) })
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, include = "non-lazy")
public class User extends BaseModel {

    private static final long serialVersionUID = 906610198273179469L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NaturalId
    @NotEmpty
    @Column(name = "NAME")
    private String name;

    @Basic(optional = false)
    @Column(length = 255, name = "PASSWORD_HASH")
    private String passwordHash;

    @Column(length = 255, name = "PASSWORD_SALT")
    private byte[] passwordSalt;

    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Column(name = "REAL_NAME")
    private String realName;

    @Basic(optional = true)
    @Column(name = "MOBILE")
    private String mobile;
    
    @Basic(optional = true)
    @Column(name = "EMAIL")
    @Email
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_LOGIN")
    private Date lastLogin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;
    
    /**
     * 用户状态
     */
    @Column(name = "STATUS")
    private int status;
    
    /**
     * 密码出错次数
     */
    @Column(name = "ERROR_NUM")
    private int errorNum;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_roles", 
        joinColumns = { @JoinColumn(name = "USER_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    // @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    private Set<Role> roles = new HashSet<Role>();

    @Version
    @Column(name = "OPT_LOCK")
    private int versionNum = 0;

    @Transient
    private String password;

    @Transient
    private String storedPassword;

    @Transient
    public boolean isPasswordExpired() {
        if (expirationDate == null)
            return true;
        return (new Date()).after(expirationDate);
    }

    @Transient
    public String getViewRoles() {
        StringBuilder sb = new StringBuilder();

        for (Iterator<Role> it = getRoles().iterator(); it.hasNext();) {
            sb.append(it.next().getName());
            if (it.hasNext())
                sb.append(", ");
        }

        return sb.toString();
    }

    @PostLoad
    public void postLoad() {
        this.storedPassword = this.passwordHash;
    }

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        if (this.passwordHash != null && !this.passwordHash.equals(this.storedPassword))
            this.expirationDate = DateUtils.addDays(new Date(), 90);
    }

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(int errorNum) {
		this.errorNum = errorNum;
	}

	public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStoredPassword() {
        return storedPassword;
    }

    public void setStoredPassword(String storedPassword) {
        this.storedPassword = storedPassword;
    }
}
