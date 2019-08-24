package com.tng.timesheetapp.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepo;

	public List<Task> getAll() {

		List<Task> retList = new ArrayList<Task>();

		taskRepo.findAll().forEach(retList::add);

		return retList;
	}

	public boolean update(Task task) {

		Optional<Task> tasktForUpdate = taskRepo.findById(task.getId());

		tasktForUpdate.ifPresent(t -> {
			t.setName(task.getName());
			t.setUpdatedDate(task.getUpdatedDate());
			t.setUpdatedBy(task.getUpdatedBy());
			taskRepo.save(t);
		});

		return true;

	}

	public boolean insert(Task task) {

		if (taskRepo.findByName(task.getName()) == null) {
			taskRepo.save(task);
			return true;
		} else {
			return false;
		}

	}

	public Optional<Task> getById(int id) {
		return taskRepo.findById(id);
	}

}
