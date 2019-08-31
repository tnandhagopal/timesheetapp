package com.tng.timesheetapp.service;

import java.time.LocalDateTime;
import java.util.List;

import com.tng.timesheetapp.model.employeerole.EmployeeRole;
import com.tng.timesheetapp.model.employeerole.EmployeeRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tng.timesheetapp.model.employee.Employee;
import com.tng.timesheetapp.model.role.Role;

@Service
public class EmployeeRoleService {

	@Autowired
	private EmployeeRoleRepository employeeRoleRepository;

	public List<EmployeeRole> getEmployeeRoleByEmployee(Employee employee) {

		return employeeRoleRepository.findByEmployee(employee);

	}

	public boolean setEmployeeRoleByEmployeeAndRole(Employee employee, Role role) {

		EmployeeRole employeeRole = new EmployeeRole();

		employeeRole.setEmployee(employee);
		employeeRole.setRole(role);

		employeeRole.setCreatedBy("ADMIN");
		employeeRole.setCreatedDate(LocalDateTime.now());

		employeeRoleRepository.save(employeeRole);
		return true;
	}

}
