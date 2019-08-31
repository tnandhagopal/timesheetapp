package com.tng.timesheetapp.model.employeerole;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tng.timesheetapp.model.employee.Employee;

@Repository
public interface EmployeeRoleRepository extends CrudRepository<EmployeeRole, Number> {
	List<EmployeeRole> findByEmployee(Employee employee);

}
