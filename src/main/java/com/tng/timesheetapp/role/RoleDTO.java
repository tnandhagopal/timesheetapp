package com.tng.timesheetapp.role;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO {

	private List<Role> roles;

	public RoleDTO() {
		this.roles = new ArrayList<Role>();
	}

	public void add(Role role) {
		this.roles.add(role);
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
