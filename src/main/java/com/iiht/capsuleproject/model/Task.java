package com.iiht.capsuleproject.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "taskId")
	private long taskId;
	@Column(name = "task")
	private String task;
	@Column(name = "priority")
	private int priority;
	@Column(name = "parentTask")
	private String parentTask;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	@Column(name = "startDate")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	@Column(name = "endDate")
	private LocalDate endDate;
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getParentTask() {
		return parentTask;
	}
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public Task(long taskId, String task, int priority, String parentTask, LocalDate startDate, LocalDate endDate) {
		super();
		this.taskId = taskId;
		this.task = task;
		this.priority = priority;
		this.parentTask = parentTask;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", task=" + task + ", priority=" + priority + ", parentTask=" + parentTask
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
