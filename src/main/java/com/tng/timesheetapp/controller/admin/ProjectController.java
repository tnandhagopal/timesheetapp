package com.tng.timesheetapp.controller.admin;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tng.timesheetapp.model.employee.Employee;
import com.tng.timesheetapp.model.employee.UserPrincipal;
import com.tng.timesheetapp.model.project.Project;
import com.tng.timesheetapp.service.ProjectService;

@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping("/all")
	public String getProjects(Principal principal, Model model) {

		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();

		model.addAttribute("username",
				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");

		model.addAttribute("projects", projectService.getAll());
		return "admin/projects";

	}

	@GetMapping("/create")
	public String addProject(Principal principal, Model model) {

		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();

		model.addAttribute("username",
				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");

		model.addAttribute("project", new Project());

		return "admin/createProject";

	}

	@PostMapping("/create")
	public String saveProject(@ModelAttribute Project project, Principal principal, Model model) {
		project.setCreatedBy(principal.getName());
		project.setCreatedDate(LocalDateTime.now());
		project.setCode(project.getCode().toUpperCase());
		if (projectService.insert(project))
			return "redirect:/admin/project/all";
		else
			return "redirect:/admin/project/create";

	}

	@GetMapping("/edit{id}")
	public String editProject(@RequestParam int id, Principal principal, Model model) {

		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();

		model.addAttribute("username",
				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");

		Optional<Project> optionalProject = projectService.getById(id);

		optionalProject.ifPresent(project -> {
			System.out.println(project.getId() + " : " + project.getName());
			model.addAttribute("project", project);
		});

		// model.addAttribute("project", project);

		return "admin/editProject";

	}

	@PutMapping("/edit")
	public String editProject(@ModelAttribute Project project, Principal principal, Model model) {

		project.setUpdatedBy(principal.getName());
		project.setUpdatedDate(LocalDateTime.now());
		project.setCode(project.getCode().toUpperCase());
		if (projectService.update(project))
			return "redirect:/admin/project/all";
		else
			return "redirect:/admin/project/edit?id=" + project.getId();

	}

}
