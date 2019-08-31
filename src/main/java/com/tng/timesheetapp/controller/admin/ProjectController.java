package com.tng.timesheetapp.controller.admin;

import com.tng.timesheetapp.model.project.Project;
import com.tng.timesheetapp.service.ModelService;
import com.tng.timesheetapp.service.ProjectService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@CommonsLog
@Controller
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin/project")

public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModelService modelService;

    @GetMapping("/all")
    public String getProjects(Principal principal, Model model) {
        modelService.setGenericFields(principal, model, "Projects");
        model.addAttribute("projects", projectService.getAll());
        return "admin/projects";

    }

    @GetMapping("/create")
    public String addProject(Principal principal, Model model) {
        modelService.setGenericFields(principal, model, "Add Project");
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
        modelService.setGenericFields(principal, model, "Edit Projects");

        Optional<Project> optionalProject = projectService.getById(id);

        optionalProject.ifPresent(project -> {
            log.info(project.getId() + " : " + project.getName());
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
