package com.tng.timesheetapp.employeetimesheetapproval;

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

import com.tng.timesheetapp.approvalstatus.ApprovalStatus;
import com.tng.timesheetapp.employee.Employee;

@Entity
@Table(name = "employee_time_sheet_approval")
public class EmployeeTimeSheetApproval {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "etsa_id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "etsa_emp_id", referencedColumnName = "emp_id")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "etsa_as_id", referencedColumnName = "as_id")
	private ApprovalStatus approvalStatus;

	@Basic
	@Column(name = "etsa_date")
	private LocalDate date;

	@Basic
	@Column(name = "etsa_created_date")
	private LocalDateTime createdDate;

	@Basic
	@Column(name = "etsa_created_by")
	private String createdBy;

	@Basic
	@Column(name = "etsa_updated_date")
	private LocalDateTime updatedDate;

	@Basic
	@Column(name = "etsa_updated_by")
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

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
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
