package com.iiht.capsuleproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.capsuleproject.dao.Task_Repo;
import com.iiht.capsuleproject.model.Task;

@Service
public class TaskService_impl {
	@Autowired
	private Task_Repo task_repo;

	public Task addTask(Task task) {
		Task t = task_repo.save(task);
		return t;
	}

	public Task updateTask(Task task) {
		Task t = task_repo.save(task);
		return t;
	}

	public Task findTaskById(long task_id) {
		// TODO Auto-generated method stub
		Optional<Task> task = task_repo.findById(task_id);
		return task.isPresent() ? task.get() : null;
	}

	public List<Task> findAllTask() {
		// TODO Auto-generated method stub
		return (List<Task>) task_repo.findAll();

	}
	
	public void delete(Task task) {
		// TODO Auto-generated method stub
		task_repo.delete(task);
	}

}
