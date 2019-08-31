package com.tng.timesheetapp.service;

import java.time.LocalDate;

import com.tng.timesheetapp.model.employeetimesheetapproval.EmployeeTimeSheetApproval;
import com.tng.timesheetapp.model.employeetimesheetapproval.EmployeeTimeSheetApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tng.timesheetapp.model.employee.Employee;

@Service
public class EmployeeTimeSheetApprovalService {

	@Autowired
	private EmployeeTimeSheetApprovalRepository etsaRepo;

	public EmployeeTimeSheetApproval getByEmployeeAndProject(Employee employee, LocalDate date) {
		return etsaRepo.findByEmployeeAndDate(employee, date);

	}
}
