package com.tng.timesheetapp.controller.common;

import java.security.Principal;

import com.tng.timesheetapp.service.ModelService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CommonsLog
@Controller
@PreAuthorize("hasAnyRole('USER')")
public class LoginController {

    @Autowired
    private ModelService modelService;

    @RequestMapping("/home")
    @GetMapping
    public String login(Principal principal, Model model) {
        log.info("home");
        boolean hasAdminRole = false;
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            hasAdminRole = authentication.getAuthorities().stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
            if (hasAdminRole) {
                modelService.setGenericFields(principal, model , " Home");

                return "home/adminHome";
            } else {
                return "redirect:/weekview/edit";
            }
        } catch (Exception e) {
            return "redirect:/home";
        }

    }

    @RequestMapping("/")
    public String home(Principal principal, Model model) {

        return "redirect:/home";

    }

}
