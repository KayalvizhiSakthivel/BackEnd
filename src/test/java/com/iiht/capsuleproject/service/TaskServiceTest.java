package com.iiht.capsuleproject.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iiht.capsuleproject.dao.Task_Repo;
import com.iiht.capsuleproject.model.Task;

public class TaskServiceTest {
	
	@InjectMocks
	TaskService_impl taskservice;
	
	@Mock
	Task_Repo taskrepo;
	
	@Before(value = "")
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void findAllTask()
	{
		Task t1=new Task();
		t1.setTaskId(1);
		t1.setPriority(20);
		t1.setTask("Task1");
		t1.setParentTask("Parent Task1");
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
		LocalDate sd1=LocalDate.of(2018,Month.JANUARY, 20);
		t1.setStartDate(sd1);
		LocalDate ed1=LocalDate.of(2018, Month.MAY,18);
		t1.setEndDate(ed1);
		
		Task t2=new Task();
		t2.setTaskId(2);
		t2.setTask("Task2");
		t2.setPriority(25);
		t2.setParentTask("Parent Task2");
		LocalDate sd2=LocalDate.of(2019, Month.JANUARY,20);
		t2.setStartDate(sd2);
		LocalDate ed2=LocalDate.of(2019, Month.MAY,18);
		t2.setEndDate(ed2);
		
		List<Task>list=new ArrayList<>();
		list.add(t1);
		list.add(t2);
		when(taskrepo.findAll()).thenReturn(list);
		
		List<Task>tasklist=new ArrayList<>();
		assertEquals(2, tasklist.size());
		verify(taskrepo,times(1)).findAll();
	}
	
	@Test
	public void addTask()
	{
		Task t=new Task();
		t.setTaskId(3);
		t.setTask("Task3");
		t.setPriority(10);
		t.setParentTask("Parent Task3");
	    SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
	    LocalDate sd3=LocalDate.of(2019, Month.FEBRUARY,20);
	    t.setStartDate(sd3);
	    LocalDate ed3=LocalDate.of(2019, Month.JUNE,25);
	    t.setEndDate(ed3);
	    
	    taskservice.addTask(t);
	    verify(taskrepo,times(1)).save(t);
	}

	@Test
	public void updateTask()
	{
		Task task=new Task();
		task.setTaskId(4);
		task.setTask("Task4");
		task.setPriority(10);
		task.setParentTask("Parent Task4");
	    SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
	    LocalDate sd3=LocalDate.of(2019, Month.FEBRUARY,20);
	    task.setStartDate(sd3);
	    LocalDate ed3=LocalDate.of(2019, Month.JUNE,25);
	    task.setEndDate(ed3);
	    
	   taskservice.updateTask(task);
	    verify(taskrepo,times(1)).save(task);
	}

	@Test
	public void findTaskById()
	{
		Task task=new Task();
		task.setTaskId(4);
		task.setTask("Task4");
		task.setPriority(10);
		task.setParentTask("Parent Task4");
	    SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
	    LocalDate sd3=LocalDate.of(2019, Month.FEBRUARY,20);
	    task.setStartDate(sd3);
	    LocalDate ed3=LocalDate.of(2019, Month.JUNE,25);
	    task.setEndDate(ed3);
	}

}
