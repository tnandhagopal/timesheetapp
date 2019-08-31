package com.tng.timesheetapp.service;

import java.util.List;

import com.tng.timesheetapp.model.employeeproject.EmployeeProject;
import com.tng.timesheetapp.model.employeeproject.EmployeeProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tng.timesheetapp.model.employee.Employee;
import com.tng.timesheetapp.model.project.Project;

@Service
public class EmployeeProjectService {

	@Autowired
	private EmployeeProjectRepository employeeProjectRepository;

	public List<EmployeeProject> getEmployeeProjectByEmployee(Employee employee) {

		return employeeProjectRepository.findByEmployee(employee);

	}

	public EmployeeProject getEmployeeProjectByEmployeeAndProject(Employee employee, Project project) {
		return employeeProjectRepository.findByEmployeeAndProject(employee, project);
	}

	public EmployeeProject getEmployeeProjectById(int id) {
		return employeeProjectRepository.findById(id).get();
	}
}
