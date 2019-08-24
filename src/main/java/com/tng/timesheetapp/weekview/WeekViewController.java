package com.tng.timesheetapp.weekview;

import java.security.Principal;
<<<<<<< HEAD

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
import org.springframework.web.bind.annotation.RequestParam;

import com.tng.timesheetapp.employee.Employee;
import com.tng.timesheetapp.login.UserPrincipal;

@Controller
@PreAuthorize("hasAnyRole('USER')")
@RequestMapping("/weekview")
public class WeekViewController {

	@Autowired
	private WeekViewService weekViewService;

	@GetMapping
	public String getWeekView(@RequestParam(value = "action", required = false) String action, Principal principal,
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

	@PostMapping("/save")
	public String saveWeekView(@ModelAttribute WeekViewDto form, Principal principal, Model model) {
		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();

		weekViewService.save(employee, form);

		return "redirect:/weekview/edit?action=same";

	}

	@GetMapping("/edit{action}")
	public String edit(@RequestParam(value = "action", required = false) String action, Principal principal,
			Model model) {


		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();

		WeekViewModel weekViewModel = weekViewService.getByEmployee(employee, action);


		model.addAttribute("weekViewModel", weekViewModel);

		model.addAttribute("username",
				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");

		model.addAttribute("form", new WeekViewDto(weekViewModel.getWeekviewList()));

		return "user/weekViewEdit";
=======
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.tng.timesheetapp.employee.Employee;
import com.tng.timesheetapp.login.UserPrincipal;

@Controller
@PreAuthorize("hasAnyRole('USER')")
@RequestMapping("/weekview")
public class WeekViewController {

	@Autowired
	private WeekViewService weekViewService;

	@GetMapping
	public String getWeekView(@RequestParam(value = "action", required = false) String action, Principal principal,
			Model model) {

		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();
		model.addAttribute("weekviews", weekViewService.getByEmployee(employee, action));
		model.addAttribute("mon",
				"Mon " + weekViewService.getFirstOfCurrentWeek().format(DateTimeFormatter.ofPattern("dd")));
		model.addAttribute("tus",
				"Thu " + weekViewService.getFirstOfCurrentWeek().plusDays(1).format(DateTimeFormatter.ofPattern("dd")));
		model.addAttribute("wed",
				"Wed " + weekViewService.getFirstOfCurrentWeek().plusDays(2).format(DateTimeFormatter.ofPattern("dd")));
		model.addAttribute("thu",
				"Thu " + weekViewService.getFirstOfCurrentWeek().plusDays(3).format(DateTimeFormatter.ofPattern("dd")));
		model.addAttribute("fri",
				"Fri " + weekViewService.getFirstOfCurrentWeek().plusDays(4).format(DateTimeFormatter.ofPattern("dd")));
		model.addAttribute("sat",
				"Sat " + weekViewService.getFirstOfCurrentWeek().plusDays(5).format(DateTimeFormatter.ofPattern("dd")));
		model.addAttribute("sun",
				"Sun " + weekViewService.getFirstOfCurrentWeek().plusDays(6).format(DateTimeFormatter.ofPattern("dd")));
		model.addAttribute("date",
				"From " + weekViewService.getFirstOfCurrentWeek().format(DateTimeFormatter.ofPattern("dd MMM YYYY"))
						+ " to " + weekViewService.getFirstOfCurrentWeek().plusDays(6)
								.format(DateTimeFormatter.ofPattern("dd MMM YYYY")));
		model.addAttribute("username",
				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");

		return "user/WeekView";

	}

	@PostMapping("/save")
	public String saveWeekView(@ModelAttribute WeekViewDto form, Principal principal, Model model) {
		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();

		weekViewService.save(employee, form);

		return "redirect:/weekview/edit?action=same";

	}

	@GetMapping("/edit{action}")
	public String edit(@RequestParam(value = "action", required = false) String action, Principal principal,
			Model model) {

		// List<WeekView> weekviews = new ArrayList<>();

		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

		Employee employee = ((UserPrincipal) userDetails).getUser();

		WeekViewModel weekViewModel = weekViewService.getByEmployee(employee, action);

		// weekviews = weekViewModel.getWeekviewList();

		model.addAttribute("weekViewModel", weekViewModel);
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

		model.addAttribute("username",
				employee.getFirstName() + " " + employee.getSecondName() + " ( " + employee.getUserName() + " )");

		model.addAttribute("form", new WeekViewDto(weekViewModel.getWeekviewList()));

		return "user/WeekViewEdit";
>>>>>>> refs/remotes/origin/master

	}

}
