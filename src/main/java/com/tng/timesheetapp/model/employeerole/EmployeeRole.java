package com.tng.timesheetapp.model.employeerole;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.tng.timesheetapp.model.employee.Employee;
import com.tng.timesheetapp.model.role.Role;

@Entity
@Table(name = "employee_role", schema = "ts")
@SequenceGenerator(name = "er_seq", sequenceName = "ts.er_seq")
public class EmployeeRole {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "er_seq")
	@Column(name = "er_id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "er_emp_id", referencedColumnName = "emp_id")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "er_role_id", referencedColumnName = "role_id")
	private Role role;

	@Basic
	@Column(name = "er_created_date")
	private LocalDateTime createdDate;

	@Basic
	@Column(name = "er_created_by")
	private String createdBy;

	@Basic
	@Column(name = "er_updated_date")
	private LocalDateTime updatedDate;

	@Basic
	@Column(name = "er_updated_by")
	private String updatedBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
