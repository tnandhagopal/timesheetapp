package com.tng.timesheetapp.model.project;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "project", schema = "ts")
@SequenceGenerator(name = "pro_seq",sequenceName="ts.pro_seq",schema = "ts")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pro_seq")
	@Column(name = "pro_id")
	private int id;

	@Basic
	@Column(name = "pro_code")
	private String code;

	@Basic
	@Column(name = "pro_name")
	private String name;

	@Basic
	@Column(name = "pro_created_date")
	private LocalDateTime createdDate;

	@Basic
	@Column(name = "pro_created_by")
	private String createdBy;

	@Basic
	@Column(name = "pro_updated_date")
	private LocalDateTime updatedDate;

	@Basic
	@Column(name = "pro_updated_by")
	private String updatedBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
