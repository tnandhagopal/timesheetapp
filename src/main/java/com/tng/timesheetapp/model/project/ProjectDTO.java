package com.tng.timesheetapp.model.project;

import java.util.ArrayList;
import java.util.List;

public class ProjectDTO {

	private List<Project> projects;

	public ProjectDTO() {
		this.projects = new ArrayList<Project>();

	}

	public ProjectDTO(List<Project> projects) {
		super();
		this.projects = projects;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void addProject(Project project) {
		this.projects.add(project);
	}

}
