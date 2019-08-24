package com.tng.timesheetapp.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

//	@Autowired
//	private ProjectService projectService;
//
//	@Autowired
//	private MyUserDetailsService employeeService;

//	@GetMapping("/projects")
//	public String getProjects(Principal principal, Model model) {
//
//		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
//
//		Employee employee = ((UserPrincipal) userDetails).getUser();
//
//		model.addAttribute("username",
//				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");
//
//		model.addAttribute("projects", projectService.getAll());
//		return "admin/projects";
//
//	}
//
//	@GetMapping("/employees")
//	public String getEmployees(Principal principal, Model model) {
//
//		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
//
//		Employee employee = ((UserPrincipal) userDetails).getUser();
//
//		model.addAttribute("username",
//				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");
//
//		model.addAttribute("employees", employeeService.getAllEmployee());
//		return "admin/employees";
//
//	}
//
//	@GetMapping("/project/create")
//	public String addProject(Principal principal, Model model) {
//
//		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
//
//		Employee employee = ((UserPrincipal) userDetails).getUser();
//
//		model.addAttribute("username",
//				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");
//
//		model.addAttribute("project", new Project());
//
//		return "admin/createProject";
//
//	}
//
//	@PostMapping("/project/create")
//	public String saveProject(@ModelAttribute Project project, Principal principal, Model model) {
//		project.setCreatedBy(principal.getName());
//		project.setCreatedDate(LocalDateTime.now());
//		project.setCode(project.getCode().toUpperCase());
//		if (projectService.insert(project))
//			return "redirect:/admin/projects";
//		else
//			return "redirect:/admin/project/create";
//
//	}
//
//	@GetMapping("/employee/create")
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
//
//	@PostMapping("/employee/create")
//	public String saveEmployee(@ModelAttribute Employee employee, Principal principal, Model model) {
//		employee.setCreatedBy(principal.getName());
//		employee.setCreatedDate(LocalDateTime.now());
//		// employee.setCode(project.getCode().toUpperCase());
//		if (employeeService.save(employee))
//			return "redirect:/admin/employees";
//		else
//			return "redirect:/admin/employee/create";
//
//	}
}
