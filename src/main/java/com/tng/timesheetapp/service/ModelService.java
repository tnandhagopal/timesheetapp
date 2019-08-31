package com.tng.timesheetapp.service;

import com.tng.timesheetapp.model.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;

@Service
public class ModelService {

    @Autowired
    private MyUserDetailsService userDetailsService;

    public void setGenericFields(Principal principal, Model model, String title) {

        Employee user = userDetailsService.findUserByPrincipal(principal);

        model.addAttribute("title", title);
        model.addAttribute("username",
                user.getFirstName() + " " + user.getSecondName() + " ( " + user.getUserName() + " )");

    }

}
