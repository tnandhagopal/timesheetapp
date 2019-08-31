package com.tng.timesheetapp.model.employeeleave;

import java.time.LocalDate;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tng.timesheetapp.model.employee.Employee;

@Entity
@Table(name = "employee_leave")
public class EmployeeLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "el_id")
	private int id;

	@Basic
	@Column(name = "el_date")
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "el_emp_id", referencedColumnName = "emp_id")
	private Employee employee;

	@Basic
	@Column(name = "el_status")
	private String status;

	@Basic
	@Column(name = "el_created_date")
	private LocalDateTime createdDate;

	@Basic
	@Column(name = "el_created_by")
	private String createdBy;

	@Basic
	@Column(name = "el_updated_date")
	private LocalDateTime updatedDate;

	@Basic
	@Column(name = "el_updated_by")
	private String updatedBy;

	public EmployeeLeave(int id, LocalDate date, Employee employee, String status, LocalDateTime createdDate,
			String createdBy, LocalDateTime updatedDate, String updatedBy) {
		super();
		this.id = id;
		this.date = date;
		this.employee = employee;
		this.status = status;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
