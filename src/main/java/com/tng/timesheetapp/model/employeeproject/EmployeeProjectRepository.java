package com.tng.timesheetapp.model.employeeproject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tng.timesheetapp.model.employee.Employee;
import com.tng.timesheetapp.model.project.Project;

@Repository
public interface EmployeeProjectRepository extends CrudRepository<EmployeeProject, Number> {
	List<EmployeeProject> findByEmployee(Employee employee);

	EmployeeProject findByEmployeeAndProject(Employee employee, Project project);
	
	
}
