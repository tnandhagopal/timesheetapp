package com.tng.timesheetapp.project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {

	Project findByCode(String code);
}
