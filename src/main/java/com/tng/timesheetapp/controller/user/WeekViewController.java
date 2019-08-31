package com.tng.timesheetapp.controller.user;

import java.security.Principal;

import com.tng.timesheetapp.model.weekview.WeekViewDto;
import com.tng.timesheetapp.model.weekview.WeekViewModel;
import com.tng.timesheetapp.service.ModelService;
import com.tng.timesheetapp.service.MyUserDetailsService;
import com.tng.timesheetapp.service.WeekViewService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyRole('USER')")
@RequestMapping("/weekview")
@CommonsLog
public class WeekViewController {

    @Autowired
    private WeekViewService weekViewService;

    @Autowired
    private ModelService modelService;

    @Autowired
	private MyUserDetailsService userDetailsService;

    @PostMapping(value = "/save", params = "action=Save")
    public String save(@ModelAttribute WeekViewDto form, Principal principal, Model model) {
        log.info("save called");

        weekViewService.save(userDetailsService.findUserByPrincipal(principal), form);

        return "redirect:/weekview/edit?action=same";

    }

    @PostMapping(value = "/save", params = "action=SubmitForApproval")
    public String submitForApproval(@ModelAttribute WeekViewDto form, Principal principal, Model model) {
       log.info("submit called");

        weekViewService.save(userDetailsService.findUserByPrincipal(principal), form);

        return "redirect:/weekview/edit?action=same";

    }

    @GetMapping("/edit{action}")
    public String edit(@RequestParam(value = "action", required = false) String action, Principal principal,
                       Model model) {

        modelService.setGenericFields(principal, model,"My Time Sheet");

        WeekViewModel weekViewModel = weekViewService.getByEmployee(userDetailsService.findUserByPrincipal(principal), action);

        model.addAttribute("weekViewModel", weekViewModel);

        model.addAttribute("form", new WeekViewDto(weekViewModel.getWeekviewList()));

        if (weekViewModel.getIsEditable()) {
            return "user/weekViewEdit";
        } else {
            return "user/weekView";
        }

    }

    @GetMapping
    public String get(@RequestParam(value = "action", required = false) String action, Principal principal,
                      Model model) {
//
//		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
//
//		Employee employee = ((UserPrincipal) userDetails).getUser();
//		model.addAttribute("weekviews", weekViewService.getByEmployee(employee, action));
//		model.addAttribute("mon",
//				"Mon " + weekViewService.getFirstOfCurrentWeek().format(DateTimeFormatter.ofPattern("dd")));
//		model.addAttribute("tus",
//				"Thu " + weekViewService.getFirstOfCurrentWeek().plusDays(1).format(DateTimeFormatter.ofPattern("dd")));
//		model.addAttribute("wed",
//				"Wed " + weekViewService.getFirstOfCurrentWeek().plusDays(2).format(DateTimeFormatter.ofPattern("dd")));
//		model.addAttribute("thu",
//				"Thu " + weekViewService.getFirstOfCurrentWeek().plusDays(3).format(DateTimeFormatter.ofPattern("dd")));
//		model.addAttribute("fri",
//				"Fri " + weekViewService.getFirstOfCurrentWeek().plusDays(4).format(DateTimeFormatter.ofPattern("dd")));
//		model.addAttribute("sat",
//				"Sat " + weekViewService.getFirstOfCurrentWeek().plusDays(5).format(DateTimeFormatter.ofPattern("dd")));
//		model.addAttribute("sun",
//				"Sun " + weekViewService.getFirstOfCurrentWeek().plusDays(6).format(DateTimeFormatter.ofPattern("dd")));
//		model.addAttribute("date",
//				"From " + weekViewService.getFirstOfCurrentWeek().format(DateTimeFormatter.ofPattern("dd MMM YYYY"))
//						+ " to " + weekViewService.getFirstOfCurrentWeek().plusDays(6)
//								.format(DateTimeFormatter.ofPattern("dd MMM YYYY")));
//		model.addAttribute("username",
//				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");

        return "redirect:/weekview/edit";

    }

}
