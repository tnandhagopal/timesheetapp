package com.tng.timesheetapp.approvalstatus;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "approval_status")
public class ApprovalStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "as_id")
	private int id;

	@Basic
	@Column(name = "as_name")
	private String name;

	@Basic
	@Column(name = "as_created_date")
	private LocalDateTime createdDate;

	@Basic
	@Column(name = "as_created_by")
	private String createdBy;

	@Basic
	@Column(name = "as_updated_date")
	private LocalDateTime updatedDate;

	@Basic
	@Column(name = "as_updated_by")
	private String updatedBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
