package com.tng.timesheetapp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tng.timesheetapp.model.employeetimesheet.EmployeeTimeSheet;
import com.tng.timesheetapp.model.employeetimesheet.EmployeeTimeSheetRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tng.timesheetapp.model.employeeproject.EmployeeProject;
import com.tng.timesheetapp.model.task.Task;

@CommonsLog
@Service
public class EmployeeTimeSheetService {

    @Autowired
    private EmployeeTimeSheetRepository etsRepo;

    public List<EmployeeTimeSheet> getAllEmployeeTimeSheet() {
        List<EmployeeTimeSheet> retList = new ArrayList<EmployeeTimeSheet>();
        etsRepo.findAll().forEach(retList::add);
        return retList;
    }

    public List<EmployeeTimeSheet> getEmployeeTimeSheetByEmployeeProject(EmployeeProject employeeProject) {
        //List<EmployeeTimeSheet> retList = new ArrayList<EmployeeTimeSheet>();
        // etsRepo.findByEmployeeProject(employeeProject).forEach(retList::add);
        // return retList;
        return etsRepo.findByEmployeeProject(employeeProject);
    }

    public List<EmployeeTimeSheet> getEmployeeTimeSheetByEmployeeProjectAndDate(EmployeeProject employeeProject,
                                                                                LocalDate startDate, LocalDate endDate) {
//        List<EmployeeTimeSheet> retList = new ArrayList<EmployeeTimeSheet>();
//        etsRepo.findByEmployeeProjectAndDateBetweenGroupByTask(employeeProject, startDate, endDate)
//                .forEach(retList::add);
        //return retList;

        return etsRepo.findByEmployeeProjectAndDateBetween(employeeProject, startDate, endDate);
    }

    public boolean setEmployeeTimeSheet(EmployeeProject employeeProject, Task task, LocalDate date, int time) {
        String txt = null;
        boolean save = false;

        log.info("setEmployeeTimeSheet : " + employeeProject.getId() + " : " + employeeProject.getProject().getName() + " : " + date + " : " + time);

        EmployeeTimeSheet employeeTimeSheet = etsRepo.findByEmployeeProjectAndTaskAndDate(employeeProject, task, date);

        if (employeeTimeSheet == null && time > 0) {
            log.info("setEmployeeTimeSheet : is null.");
            employeeTimeSheet = new EmployeeTimeSheet(employeeProject, task, date, time);
            txt = "created";
            save = true;
        } else if (employeeTimeSheet != null) {
            log.info("setEmployeeTimeSheet : " + employeeTimeSheet.getDate() + " : " + employeeTimeSheet.getTime());
            if (employeeTimeSheet.getTime() != time && time < 24) {
                employeeTimeSheet.setTime(time);

                employeeTimeSheet.setUpdatedBy("ADMIN");
                employeeTimeSheet.setUpdatedDate(LocalDateTime.now());
                txt = "updated";
                save = true;

            }
        }

        if (save) {
            employeeTimeSheet = etsRepo.save(employeeTimeSheet);
            log.info(txt + " employeeTimeSheet id : " + employeeTimeSheet.getId());
            log.info("===========================================saved============================================");
        } else if (employeeTimeSheet != null) {
            log.info("not updated/inserted employeeTimeSheet id : " + employeeTimeSheet.getId());
        } else {
            log.info("not updated/inserted employeeTimeSheet id : ");
        }

        return true;
    }
}
