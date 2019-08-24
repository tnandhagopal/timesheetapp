package com.tng.timesheetapp.employeerole;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tng.timesheetapp.employee.Employee;

@Repository
public interface EmployeeRoleRepository extends CrudRepository<EmployeeRole, Number> {
	List<EmployeeRole> findByEmployee(Employee employee);

}
