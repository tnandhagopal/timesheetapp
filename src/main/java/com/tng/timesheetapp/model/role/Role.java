package com.tng.timesheetapp.model.role;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "ts")
@SequenceGenerator(name = "role_seq",sequenceName="ts.role_seq")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
	@Column(name = "role_id")
	private int id;

	@Basic
	@Column(name = "role_name")
	private String name;

	@Basic
	@Column(name = "role_created_date")
	private LocalDateTime createdDate;

	@Basic
	@Column(name = "role_created_by")
	private String createdBy;

	@Basic
	@Column(name = "role_updated_date")
	private LocalDateTime updatedDate;

	@Basic
	@Column(name = "role_updated_by")
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
