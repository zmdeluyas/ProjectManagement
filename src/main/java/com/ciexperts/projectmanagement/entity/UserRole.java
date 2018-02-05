package com.ciexperts.projectmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="User_Role")
public class UserRole {

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "increment")
	@GeneratedValue(generator="idGenerator")
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="role_name", nullable=false)
	private String roleName;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private Set<User> user;*/

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/*public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}*/
	
}
