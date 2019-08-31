package com.tng.timesheetapp.employeetimesheetapproval;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tng.timesheetapp.employee.Employee;

@Service
public class EmployeeTimeSheetApprovalService {

	@Autowired
	private EmployeeTimeSheetApprovalRepository etsaRepo;

	public EmployeeTimeSheetApproval getByEmployeeAndProject(Employee employee, LocalDate date) {
		return etsaRepo.findByEmployeeAndDate(employee, date);

	}
}
