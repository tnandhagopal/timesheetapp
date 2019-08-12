package com.tng.timesheetapp.employeetimesheet;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tng.timesheetapp.employeeproject.EmployeeProject;

public interface EmployeeTimeSheetRepository extends CrudRepository<EmployeeTimeSheet, String> {
	List<EmployeeTimeSheet> findByEmployeeProject(EmployeeProject employeeProject);

	List<EmployeeTimeSheet> findByEmployeeProjectAndDateBetween(EmployeeProject employeeProject, LocalDate startDate,
			LocalDate endDate);

	EmployeeTimeSheet findByEmployeeProjectAndDate(EmployeeProject employeeProject, LocalDate date);
}
