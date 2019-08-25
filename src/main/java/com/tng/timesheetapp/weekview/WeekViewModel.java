package com.tng.timesheetapp.weekview;

import java.util.ArrayList;
import java.util.List;

public class WeekViewModel {

	private List<WeekView> weekviewList;

	private WeekViewTableCols weekViewTableCols;

	private String currentWeek;

	private Boolean isEditable;

	public WeekViewModel() {
		this.weekviewList = new ArrayList<WeekView>();
		this.weekViewTableCols = new WeekViewTableCols();
		this.currentWeek = new String();
		this.isEditable = true;
	}

	public Boolean getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}

	public String getCurrentWeek() {
		return currentWeek;
	}

	public void setCurrentWeek(String currentWeek) {
		this.currentWeek = currentWeek;
	}

	public List<WeekView> getWeekviewList() {
		return weekviewList;
	}

	public void setWeekviewList(List<WeekView> weekviewList) {
		this.weekviewList = weekviewList;
	}

	public WeekViewTableCols getWeekViewTableCols() {
		return weekViewTableCols;
	}

	public void setWeekViewTableCols(WeekViewTableCols weekViewTableCols) {
		this.weekViewTableCols = weekViewTableCols;
	}

}
