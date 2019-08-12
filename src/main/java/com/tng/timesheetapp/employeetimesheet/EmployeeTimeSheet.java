package com.tng.timesheetapp.employeetimesheet;

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

import com.tng.timesheetapp.employeeproject.EmployeeProject;

@Entity
@Table(name = "Employee_Time_Sheet")
public class EmployeeTimeSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ets_id")
	private int id;

	@Basic
	@Column(name = "ets_date")
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "ets_ep_id", referencedColumnName = "ep_id")
	private EmployeeProject employeeProject;

	@Basic
	@Column(name = "ets_time")
	private int time = 0;

	@Basic
	@Column(name = "ets_created_date")
	private LocalDateTime createdDate;

	@Basic
	@Column(name = "ets_created_by")
	private String createdBy;

	@Basic
	@Column(name = "ets_updated_date")
	private LocalDateTime updatedDate;

	@Basic
	@Column(name = "ets_updated_by")
	private String updatedBy;

	public EmployeeTimeSheet() {

	}

	public EmployeeTimeSheet(EmployeeProject employeeProject, LocalDate date, int time) {
		super();
		this.date = date;
		this.employeeProject = employeeProject;
		this.time = time;
		this.createdDate = LocalDateTime.now();
		this.createdBy = "ADMIN";
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

	public EmployeeProject getEmployeeProject() {
		return employeeProject;
	}

	public void setEmployeeProject(EmployeeProject employeeProject) {
		this.employeeProject = employeeProject;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getTimeString() {

		try {
			return String.valueOf(time);
		} catch (Exception e) {
			return "0";
		}
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
