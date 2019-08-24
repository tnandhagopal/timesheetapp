package com.tng.timesheetapp.employeetimesheet;

import java.time.LocalDate;
import java.util.List;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tng.timesheetapp.employeeproject.EmployeeProject;
import com.tng.timesheetapp.task.Task;

public interface EmployeeTimeSheetRepository extends CrudRepository<EmployeeTimeSheet, String> {
	List<EmployeeTimeSheet> findByEmployeeProject(EmployeeProject employeeProject);

	List<EmployeeTimeSheet> findByEmployeeProjectAndDateBetween(EmployeeProject employeeProject, LocalDate startDate,
			LocalDate endDate);

	EmployeeTimeSheet findByEmployeeProjectAndDate(EmployeeProject employeeProject, LocalDate date);

	EmployeeTimeSheet findByEmployeeProjectAndTaskAndDate(EmployeeProject employeeProject, Task task, LocalDate date);

	@Query("select ets from EmployeeTimeSheet ets where ets.employeeProject = :employeeProject and ets.date between :startDate and :endDate group by ets.task")
	List<EmployeeTimeSheet> findByEmployeeProjectAndDateBetweenGroupByTask(EmployeeProject employeeProject,
			LocalDate startDate, LocalDate endDate);
=======
import org.springframework.data.repository.CrudRepository;

import com.tng.timesheetapp.employeeproject.EmployeeProject;

public interface EmployeeTimeSheetRepository extends CrudRepository<EmployeeTimeSheet, String> {
	List<EmployeeTimeSheet> findByEmployeeProject(EmployeeProject employeeProject);

	List<EmployeeTimeSheet> findByEmployeeProjectAndDateBetween(EmployeeProject employeeProject, LocalDate startDate,
			LocalDate endDate);

	EmployeeTimeSheet findByEmployeeProjectAndDate(EmployeeProject employeeProject, LocalDate date);
>>>>>>> refs/remotes/origin/master
}
