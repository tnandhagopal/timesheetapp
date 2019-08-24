package com.tng.timesheetapp.admin;

import java.security.Principal;
import java.time.LocalDate;

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

import com.tng.timesheetapp.employee.Employee;
import com.tng.timesheetapp.employee.MyUserDetailsService;
import com.tng.timesheetapp.login.UserPrincipal;
import com.tng.timesheetapp.project.Project;
import com.tng.timesheetapp.project.ProjectService;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private MyUserDetailsService employeeService;

	@GetMapping("/projects")
	public String getProjects(Principal principal, Model model) {

		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();

		model.addAttribute("username",
				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");

		model.addAttribute("projects", projectService.getAll());
		return "admin/projects";

	}

	@GetMapping("/employees")
	public String getEmployees(Principal principal, Model model) {

		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();

		model.addAttribute("username",
				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");

		model.addAttribute("employees", employeeService.getAllEmployee());
		return "admin/employees";

	}

	@GetMapping("/project/create")
	public String addProject(Principal principal, Model model) {

		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();

		model.addAttribute("username",
				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");

		model.addAttribute("project", new Project());

		return "admin/createProject";

	}

	@PostMapping("/project/create")
	public String saveProject(@ModelAttribute Project project, Principal principal, Model model) {
		project.setCreatedBy(principal.getName());
		project.setCreatedDate(LocalDate.now());
		project.setCode(project.getCode().toUpperCase());
		if (projectService.save(project))
			return "redirect:/admin/projects";
		else
			return "redirect:/admin/project/create";

	}
	
	@GetMapping("/employee/create")
	public String addEmployee(Principal principal, Model model) {

		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();

		model.addAttribute("username",
				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");

		model.addAttribute("employee", new Employee());

		return "admin/createEmployee";

	}

	@PostMapping("/employee/create")
	public String saveEmployee(@ModelAttribute Employee employee, Principal principal, Model model) {
		employee.setCreatedBy(principal.getName());
		employee.setCreatedDate(LocalDate.now());
		//employee.setCode(project.getCode().toUpperCase());
		if (employeeService.save(employee))
			return "redirect:/admin/employees";
		else
			return "redirect:/admin/employee/create";

	}
}
