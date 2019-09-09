package com.tng.timesheetapp.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tng.timesheetapp.model.weekview.*;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tng.timesheetapp.model.employee.Employee;
import com.tng.timesheetapp.model.employeeproject.EmployeeProject;
import com.tng.timesheetapp.model.employeetimesheet.EmployeeTimeSheet;
import com.tng.timesheetapp.model.task.Task;

@CommonsLog
@Service
public class WeekViewService {

    @Autowired
    private EmployeeTimeSheetService employeeTimeSheetService;

    @Autowired
    private EmployeeProjectService employeeProjectService;

    @Autowired
    private TaskService taskService;

    private LocalDate firstOfCurrentWeek = LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1);

    public LocalDate getFirstOfCurrentWeek() {
        return firstOfCurrentWeek;
    }

    public List<WeekView> getAll() {

        List<WeekView> retList = new ArrayList<WeekView>();

//		projectService.getAll().stream().forEach(pro -> retList
//				.add(setWeekView(employeeTimeSheetService.getAllEmployeeTimeSheet(), pro, firstOfCurrentWeek)));
        return retList;

    }

    public WeekViewModel getByEmployee(Employee employee, String action) {

        log.info(action + " : " + firstOfCurrentWeek);

        if (action == null) {
            firstOfCurrentWeek = LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1);
        } else if (action.equalsIgnoreCase("next")) {
            firstOfCurrentWeek = firstOfCurrentWeek.plusWeeks(1);
        } else if (action.equalsIgnoreCase("previous")) {
            firstOfCurrentWeek = firstOfCurrentWeek.minusWeeks(1);
        }
//		} else {
//			firstOfCurrentWeek = LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1);
//		}

        log.info(action + " : " + firstOfCurrentWeek);

        WeekViewModel weekViewModel = new WeekViewModel();

        List<WeekView> weekViewList = new ArrayList<>();

        employeeProjectService.getEmployeeProjectByEmployee(employee).stream().forEach(employeeProject -> {

            List<EmployeeTimeSheet> employeeTimeSheets = employeeTimeSheetService.getEmployeeTimeSheetByEmployeeProjectAndDate(employeeProject, firstOfCurrentWeek,
                    firstOfCurrentWeek.plusDays(6));

            employeeTimeSheets.stream().forEach(e -> {

                log.info("employeeTimeSheets::"
                        + e.getDate() + "::"
                        + e.getTime() + "::"
                        + e.getEmployeeProject().getProject().getName() + "::"
                        + e.getTask().getName()
                );

                weekViewModel.setWeekViewTableCols(setWeekViewFoot(weekViewModel.getWeekViewTableCols(), e.getDate(), e.getTime()));

            });

            Map<Task, List<EmployeeTimeSheet>> employeeTimeSheetsMap = employeeTimeSheets.stream()
                    .collect(Collectors.groupingBy(EmployeeTimeSheet::getTask));

            for (Entry<Task, List<EmployeeTimeSheet>> task : employeeTimeSheetsMap.entrySet()) {

                log.info("add week view for for task ::" + task.getKey().getName());
                WeekView weekView = setWeekView(task.getValue(), firstOfCurrentWeek, employeeProject, task.getKey());

                log.info("WeekView::"
                        + weekView.getEmployeeProject().getProject().getName() + ","
                        + weekView.getTask().getName() + ","
                        + weekView.getMon() + ","
                        + weekView.getTus() + ","
                        + weekView.getWed() + ","
                        + weekView.getThu() + ","
                        + weekView.getFri() + ","
                        + weekView.getSat() + ","
                        + weekView.getSun() + ","
                        + weekView.getTotal() + ","
                        + weekView.getFirstOfCurrentWeek()
                );

                weekViewList.add(weekView);
            }
        });

        weekViewModel.setWeekviewList(weekViewList);

        log.info("=========================");

        for (LocalDate date = firstOfCurrentWeek; date.isBefore(firstOfCurrentWeek.plusDays(7)); date = date.plusDays(1)) {
            log.info("Head " + date);
            weekViewModel.setWeekViewTableCols(setWeekViewHead(weekViewModel.getWeekViewTableCols(), date));
        }

        weekViewModel.setCurrentWeek("From " + firstOfCurrentWeek.format(DateTimeFormatter.ofPattern("dd MMM YYYY"))
                + " to " + firstOfCurrentWeek.plusDays(6).format(DateTimeFormatter.ofPattern("dd MMM YYYY")));

        return weekViewModel;
    }

    public void save(Employee employee, WeekViewDto weekviewdto) {

        log.info("save : firstOfCurrentWeek : " + firstOfCurrentWeek);

        if (weekviewdto == null)
            return;

        log.info("save : form.getWeekviews().size() = " + weekviewdto.getWeekviews().size());

        weekviewdto.getWeekviews().stream().forEach(weekview -> {
            log.info("weekview.employeeProject.id: " + weekview.getEmployeeProject().getId());

            weekview.setEmployeeProject(
                    employeeProjectService.getEmployeeProjectById(weekview.getEmployeeProject().getId()));
            log.info("weekview.employeeProject.project.name: " + weekview.getEmployeeProject().getProject().getName());

            weekview.setTask(taskService.getById(weekview.getTask().getId()).get());
            log.info("weekview.task.name: " + weekview.getTask().getName());

            employeeTimeSheetService.setEmployeeTimeSheet(weekview.getEmployeeProject(), weekview.getTask(),
                    firstOfCurrentWeek, weekview.getMon());

            employeeTimeSheetService.setEmployeeTimeSheet(weekview.getEmployeeProject(), weekview.getTask(),
                    firstOfCurrentWeek.plusDays(1), weekview.getTus());

            employeeTimeSheetService.setEmployeeTimeSheet(weekview.getEmployeeProject(), weekview.getTask(),
                    firstOfCurrentWeek.plusDays(2), weekview.getWed());

            employeeTimeSheetService.setEmployeeTimeSheet(weekview.getEmployeeProject(), weekview.getTask(),
                    firstOfCurrentWeek.plusDays(3), weekview.getThu());

            employeeTimeSheetService.setEmployeeTimeSheet(weekview.getEmployeeProject(), weekview.getTask(),
                    firstOfCurrentWeek.plusDays(4), weekview.getFri());

            employeeTimeSheetService.setEmployeeTimeSheet(weekview.getEmployeeProject(), weekview.getTask(),
                    firstOfCurrentWeek.plusDays(5), weekview.getSat());

            employeeTimeSheetService.setEmployeeTimeSheet(weekview.getEmployeeProject(), weekview.getTask(),
                    firstOfCurrentWeek.plusDays(6), weekview.getSun());

        });

    }

    private WeekView setWeekView(List<EmployeeTimeSheet> employeeTimeSheets, LocalDate firstOfCurrentWeek,
                                 EmployeeProject employeeProject, Task task) {

        AtomicReference<WeekView> weekView = new AtomicReference<>(new WeekView(employeeProject, task, firstOfCurrentWeek));

        employeeTimeSheets.stream().forEach(employeeTimeSheet -> {
            weekView.set(setWeekViewTime(weekView.get(), employeeTimeSheet.getDate(), employeeTimeSheet.getTime()));
            weekView.get().setTotal(weekView.get().getTotal() + employeeTimeSheet.getTime());
        });

        return weekView.get();
    }

    public WeekView setWeekViewTime(WeekView weekView, LocalDate date, int time) {
        switch (date.getDayOfWeek()) {
            case MONDAY:
                weekView.setMon(time);
                break;
            case TUESDAY:
                weekView.setTus(time);
                break;
            case WEDNESDAY:
                weekView.setWed(time);
                break;
            case THURSDAY:
                weekView.setThu(time);
                break;
            case FRIDAY:
                weekView.setFri(time);
                break;
            case SATURDAY:
                weekView.setSat(time);
                break;
            case SUNDAY:
                weekView.setSun(time);
                break;
            default:
                break;
        }
        return weekView;
    }

    private WeekViewTableCols setWeekViewFoot(WeekViewTableCols weekViewTableCols, LocalDate date, int time) {
        WeekViewContext weekViewContext = getWeekViewContext(weekViewTableCols, date);
        weekViewContext.setFoot(weekViewContext.getFoot() + time);
        return weekViewTableCols;
    }

    private WeekViewTableCols setWeekViewHead(WeekViewTableCols weekViewTableCols, LocalDate date) {
        WeekViewContext weekViewContext = getWeekViewContext(weekViewTableCols, date);
        weekViewContext.setHead(date.format(DateTimeFormatter.ofPattern("EEE dd")));
        return weekViewTableCols;
    }

    private WeekViewContext getWeekViewContext(WeekViewTableCols weekViewTableCols, LocalDate date) {
        WeekViewContext weekViewContext = null;

        switch (date.getDayOfWeek()) {
            case MONDAY:
                weekViewContext = weekViewTableCols.getMon();
                break;
            case TUESDAY:
                weekViewContext = weekViewTableCols.getTus();
                break;
            case WEDNESDAY:
                weekViewContext = weekViewTableCols.getWed();
                break;
            case THURSDAY:
                weekViewContext = weekViewTableCols.getThu();
                break;
            case FRIDAY:
                weekViewContext = weekViewTableCols.getFri();
                break;
            case SATURDAY:
                weekViewContext = weekViewTableCols.getSat();
                break;
            case SUNDAY:
                weekViewContext = weekViewTableCols.getSun();
                break;
            default:
                break;
        }

        return weekViewContext;
    }


}