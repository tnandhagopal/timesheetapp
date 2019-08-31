package com.tng.timesheetapp.controller.admin;

import java.security.Principal;
import java.time.LocalDateTime;

import com.tng.timesheetapp.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tng.timesheetapp.model.employee.EmployeeDetails;
import com.tng.timesheetapp.service.MyUserDetailsService;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin/employee")
public class EmployeeController {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private ModelService modelService;

	@GetMapping("/all")
	public String getEmployees(Principal principal, Model model) {
		modelService.setGenericFields(principal, model,"Employees");
		model.addAttribute("employees", userDetailsService.getAllEmployee());
		return "admin/employees";

	}

	@GetMapping("/create")
	public String addEmployee(Principal principal, Model model) {
		modelService.setGenericFields(principal, model,"Add Employee");

		model.addAttribute("employeeDetails", userDetailsService.getEmployeeDetails());

		return "admin/createEmployee";

	}

	@PostMapping("/create")
	public String saveEmployee(@ModelAttribute EmployeeDetails employeeDetails, Principal principal, Model model) {
		employeeDetails.getEmployee().setCreatedBy(principal.getName());
		employeeDetails.getEmployee().setCreatedDate(LocalDateTime.now());
		if (userDetailsService.setEmployeeDetails(employeeDetails))
			return "redirect:/admin/employee/all";
		else
			return "redirect:/admin/employee/create";

	}
}
