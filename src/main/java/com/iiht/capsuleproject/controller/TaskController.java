package com.iiht.capsuleproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.capsuleproject.model.Task;
import com.iiht.capsuleproject.service.TaskService_impl;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

@RestController

public class TaskController {

@Autowired
private TaskService_impl taskimpl;

/*Add Task*/
@PostMapping
public ResponseEntity<String> create(@RequestBody Task task) {
	ResponseEntity<String> resp = null;
	if (task == null) {
		resp = new ResponseEntity<>("Empty Task found", HttpStatus.NO_CONTENT);
	} else {
		taskimpl.addTask(task);
		resp = new ResponseEntity<>("Task details added successfully!!!" + task, HttpStatus.CREATED);
		System.out.println("Task details added succesfully!!!");
	}
	return resp;

}


@GetMapping("/task/{id}")
public ResponseEntity<?> getEmp(@PathVariable("id") Long task_id) {
	Task t = taskimpl.findTaskById(task_id);
	ResponseEntity<?> resp = null;
	if (t == null) {
		resp = new ResponseEntity<>("Task with id " + task_id + " not found", HttpStatus.NOT_FOUND);
	} else {
		resp = new ResponseEntity<>("Fetched Task details for task id " + task_id + " successfully!!! " + t,
				HttpStatus.OK);
	}
	return resp;
}
/* View Task */
@GetMapping
public ResponseEntity<List<Task>> findAllTask() {
	return new ResponseEntity<>(taskimpl.findAllTask(), HttpStatus.OK);
}



/*Update Task*/
@PutMapping("/task/{id}")
public ResponseEntity<String> updateTask(@PathVariable(value = "id") Long task_id, @Valid @RequestBody Task task) {
	Task t = taskimpl.findTaskById(task_id);
	ResponseEntity<String> resp = null;
	if (t == null) {
		resp = new ResponseEntity<>("Task with id " + task_id + " not found", HttpStatus.NOT_FOUND);
	} else {
		t.setTask(task.getTask());
		t.setPriority(task.getPriority());
		t.setParentTask(task.getParentTask());
		t.setStartDate(task.getStartDate());
		t.setEndDate(task.getEndDate());
		
		Task updateTask = taskimpl.updateTask(task);
		resp = new ResponseEntity<>("Task Updated successfully!!!" + updateTask, HttpStatus.OK);
	}
	return resp;

}
/* DELETE a Book */
@RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long taskId) {
	Task t = taskimpl.findTaskById(taskId);
	ResponseEntity<?> resp = null;
	if (t== null) {
		resp = new ResponseEntity<>("Task with id " + taskId + " not found", HttpStatus.NOT_FOUND);
	} else {
		taskimpl.delete(t);
		resp = new ResponseEntity<>("Task deleted successfully!!!", HttpStatus.OK);
	}
	return resp;
}


}
