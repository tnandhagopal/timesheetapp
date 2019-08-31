package com.tng.timesheetapp.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import com.tng.timesheetapp.model.project.Project;
import com.tng.timesheetapp.model.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository proRepo;

	public List<Project> getAll() {

		List<Project> retList = new ArrayList<Project>();

		proRepo.findAll().forEach(retList::add);

		return retList;
	}

	public boolean update(Project project) {

		Optional<Project> projectForUpdate = proRepo.findById(project.getId());

		projectForUpdate.ifPresent(p -> {
			p.setName(project.getName());
			p.setCode(project.getCode());
			p.setUpdatedDate(project.getUpdatedDate());
			p.setUpdatedBy(project.getUpdatedBy());
			proRepo.save(p);
		});

		return true;

	}

	public boolean insert(Project project) {

		if (proRepo.findByCode(project.getCode()) == null) {
			proRepo.save(project);
			return true;
		} else {
			return false;
		}

	}

	public Optional<Project> getById(int id) {
		return proRepo.findById(id);

	}

}
