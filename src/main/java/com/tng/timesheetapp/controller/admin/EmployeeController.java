package com.tng.timesheetapp.controller.admin;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tng.timesheetapp.model.employee.Employee;
import com.tng.timesheetapp.model.employee.EmployeeDetails;
import com.tng.timesheetapp.service.MyUserDetailsService;
import com.tng.timesheetapp.model.employee.UserPrincipal;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin/employee")
public class EmployeeController {

	@Autowired
	private MyUserDetailsService employeeService;

	@GetMapping("/all")
	public String getEmployees(Principal principal, Model model) {

		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();

		model.addAttribute("username",
				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");

		model.addAttribute("employees", employeeService.getAllEmployee());
		return "admin/employees";

	}

//	@GetMapping("/create")
//	public String addEmployee(Principal principal, Model model) {
//
//		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
//
//		Employee employee = ((UserPrincipal) userDetails).getUser();
//
//		model.addAttribute("username",
//				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");
//
//		model.addAttribute("employee", new Employee());
//
//		return "admin/createEmployee";
//
//	}

	@GetMapping("/create")
	public String addEmployee(Principal principal, Model model) {

		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();

		model.addAttribute("username",
				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");

		model.addAttribute("employeeDetails", employeeService.getEmployeeDetails());

		return "admin/createEmployee";

	}

	@PostMapping("/create")
	public String saveEmployee(@ModelAttribute EmployeeDetails employeeDetails, Principal principal, Model model) {
		employeeDetails.getEmployee().setCreatedBy(principal.getName());
		employeeDetails.getEmployee().setCreatedDate(LocalDateTime.now());
		if (employeeService.setEmployeeDetails(employeeDetails))
			return "redirect:/admin/employee/all";
		else
			return "redirect:/admin/employee/create";

	}
}
