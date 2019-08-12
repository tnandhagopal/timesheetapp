package com.tng.timesheetapp.employeeproject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tng.timesheetapp.employee.Employee;
import com.tng.timesheetapp.project.Project;

@Repository
public interface EmployeeProjectRepository extends CrudRepository<EmployeeProject, Number> {
	List<EmployeeProject> findByEmployee(Employee employee);

	EmployeeProject findByEmployeeAndProject(Employee employee, Project project);
	
	
}
