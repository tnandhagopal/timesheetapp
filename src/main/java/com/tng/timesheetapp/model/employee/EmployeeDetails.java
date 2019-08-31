package com.tng.timesheetapp.model.employee;

import java.util.ArrayList;
import java.util.List;

import com.tng.timesheetapp.model.project.Project;
import com.tng.timesheetapp.model.role.Role;

public class EmployeeDetails {

	private Employee employee;

	private List<Role> roles;

	private List<Project> projects;

	public EmployeeDetails() {
		this.employee = new Employee();
		this.roles = new ArrayList<Role>();
		this.projects = new ArrayList<Project>();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> list) {
		this.roles = list;
	}

}
