package com.tng.timesheetapp.model.weekview;

import com.tng.timesheetapp.model.employeeproject.EmployeeProject;
import com.tng.timesheetapp.model.task.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WeekView {

    private int mon;
    private int tus;
    private int wed;
    private int thu;
    private int fri;
    private int sat;
    private int sun;
    private int total;

    private EmployeeProject employeeProject;

    private Task task;

    private LocalDate firstOfCurrentWeek;

    public WeekView(EmployeeProject employeeProject, Task task, LocalDate firstOfCurrentWeek) {
        this.mon = 0;
        this.tus = 0;
        this.wed = 0;
        this.thu = 0;
        this.fri = 0;
        this.sat = 0;
        this.sun = 0;
        this.total = 0;
        this.employeeProject = employeeProject;
        this.task = task;
        this.firstOfCurrentWeek = firstOfCurrentWeek;
    }
//
//	public WeekView() {
//		this.mon = 0;
//		this.tus = 0;
//		this.wed = 0;
//		this.thu = 0;
//		this.fri = 0;
//		this.sat = 0;
//		this.sun = 0;
//		this.total = 0;
//		this.employeeProject = new EmployeeProject();
//		this.firstOfCurrentWeek = LocalDate.now();
//		this.task = new Task();
//	}
//
//	public WeekView(int mon, int tus, int wed, int thu, int fri, int sat, int sun, EmployeeProject employeeProject,
//			Task task, LocalDate firstOfCurrentWeek) {
//		super();
//		this.mon = mon;
//		this.tus = tus;
//		this.wed = wed;
//		this.thu = thu;
//		this.fri = fri;
//		this.sat = sat;
//		this.sun = sun;
//		this.employeeProject = employeeProject;
//		this.firstOfCurrentWeek = firstOfCurrentWeek;
//		this.total = this.sun + this.mon + this.tus + this.wed + this.thu + this.fri + this.sat;
//	}

//	public Task getTask() {
//		return task;
//	}
//
//	public void setTask(Task task) {
//		this.task = task;
//	}
//
//	public LocalDate getFirstOfCurrentWeek() {
//		return firstOfCurrentWeek;
//	}
//
//	public void setFirstOfCurrentWeek(LocalDate firstOfCurrentWeek) {
//		this.firstOfCurrentWeek = firstOfCurrentWeek;
//	}
//
//	public EmployeeProject getEmployeeProject() {
//		return employeeProject;
//	}
//
//	public void setEmployeeProject(EmployeeProject employeeProject) {
//		this.employeeProject = employeeProject;
//
//	}
//
//	public int getSun() {
//		return sun;
//	}
//
//	public void setSun(int sun) {
//		this.sun = sun;
//	}
//
//	public int getMon() {
//		return mon;
//	}
//
//	public void setMon(int mon) {
//		this.mon = mon;
//	}
//
//	public int getTus() {
//		return tus;
//	}
//
//	public void setTus(int tus) {
//		this.tus = tus;
//	}
//
//	public int getWed() {
//		return wed;
//	}
//
//	public void setWed(int wed) {
//		this.wed = wed;
//	}
//
//	public int getThu() {
//		return thu;
//	}
//
//	public void setThu(int thu) {
//		this.thu = thu;
//	}
//
//	public int getFri() {
//		return fri;
//	}
//
//	public void setFri(int fri) {
//		this.fri = fri;
//	}
//
//	public int getSat() {
//		return sat;
//	}
//
//	public void setSat(int sat) {
//		this.sat = sat;
//	}
//
////	public Project getProject() {
////		return project;
////	}
////
////	public void setProject(Project project) {
////		this.project = project;
////	}
//
//	public int getTotal() {
//		this.total = this.sun + this.mon + this.tus + this.wed + this.thu + this.fri + this.sat;
//		return total;
//	}
//
//	public void setTotal(int total) {
//		this.total = total;
//	}

}
