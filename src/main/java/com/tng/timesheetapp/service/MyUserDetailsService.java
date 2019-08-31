package com.tng.timesheetapp.service;

import java.util.List;

import com.tng.timesheetapp.model.employee.Employee;
import com.tng.timesheetapp.model.employee.EmployeeDetails;
import com.tng.timesheetapp.model.employee.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tng.timesheetapp.model.employee.UserPrincipal;
import com.tng.timesheetapp.model.role.RoleRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

//	@Autowired
//	private ProjectRepository projectRepository;

	@Autowired
	private EmployeeRoleService employeeRoleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee user = userRepository.findByUserName(username);
		if (user == null) {
			System.out.println("user nul exception!!");
			throw new UsernameNotFoundException("User 404");
		}

		// System.out.println("user : " + user.getUserName());
		// System.out.println("user : " + (new BCryptPasswordEncoder(10).
		// user.getPassword());
		return new UserPrincipal(user);
	}

	public List<Employee> getAllEmployee() {
		return userRepository.findAll();
	}

	public boolean save(Employee employee) {

		if (userRepository.findByUserName(employee.getUserName()) == null) {
			employee.setStatus("NEW");
			employee.setPassword(new BCryptPasswordEncoder(10).encode(employee.getPassword()));
			userRepository.save(employee);
			return true;
		}

		return false;
	}

	public EmployeeDetails getEmployeeDetails() {

		EmployeeDetails employeeDetails = new EmployeeDetails();

		employeeDetails.setRoles(roleRepository.findAll());
		// employeeDetails.setProjects(projectRepository.findAll());

		return employeeDetails;
	}

	public Boolean setEmployeeDetails(EmployeeDetails employeeDetails) {

		if (userRepository.findByUserName(employeeDetails.getEmployee().getUserName()) == null) {
			employeeDetails.getEmployee().setStatus("NEW");
			employeeDetails.getEmployee()
					.setPassword(new BCryptPasswordEncoder(10).encode(employeeDetails.getEmployee().getPassword()));
			// employeeDetails.getEmployee().setRoles(employeeDetails.getRoles());

			employeeDetails.setEmployee(userRepository.save(employeeDetails.getEmployee()));
		}

		employeeDetails.getRoles().stream().forEach(role -> {

			employeeRoleService.setEmployeeRoleByEmployeeAndRole(employeeDetails.getEmployee(),
					roleRepository.findById(role.getId()).get());

		});

		return true;

	}

}
