package com.tng.timesheetapp.model.employeetimesheetapproval;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tng.timesheetapp.model.employee.Employee;

public interface EmployeeTimeSheetApprovalRepository extends JpaRepository<EmployeeTimeSheetApproval, Integer> {

	EmployeeTimeSheetApproval findByEmployeeAndDate(Employee employee, LocalDate date);

}
