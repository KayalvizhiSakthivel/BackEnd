package com.iiht.capsuleproject.controller;



import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.capsuleproject.model.Task;
import com.iiht.capsuleproject.service.TaskService_impl;
import com.iiht.capsuleproject.test.TestUtil;




public class TaskControllerTest {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Mock
	private TaskService_impl taskservice;

	private MockMvc mockMvc;

	@InjectMocks
	private TaskController taskcontroller;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(taskcontroller).build();
	}

	@Test
	public void testListAllTask() throws Exception, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		LocalDate sd1 = LocalDate.of(2019,Month.JANUARY,20);
		LocalDate ed1 = LocalDate.of(2019,Month.MAY,18);
		LocalDate sd2 = LocalDate.of(2019,Month.JANUARY,20);
		LocalDate ed2 = LocalDate.of(2019,Month.MAY,18);
		Task task1 = new Task();
		Task task2 = new Task();
		task1.setTaskId(1);
		task1.setTask("Task1");
		task1.setPriority(12);
		task1.setParentTask("parentask1");
		task1.setStartDate(sd1);
		task1.setEndDate(ed1);
		
		task2.setTaskId(2);
		task2.setTask("Task2");
		task2.setPriority(13);
		task2.setParentTask("parentask2");
		task2.setStartDate(sd2);
		task2.setEndDate(ed2);

		List<Task> types = new ArrayList<Task>();
		types.add(task1);
		types.add(task2);
		when(taskservice.findAllTask()).thenReturn(types);
		mockMvc.perform(get("/viewTask")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$[0].taskId", is(1))).andExpect(jsonPath("$[0].task", is("Task1")))
				.andExpect(jsonPath("$[0].priority", is(12))).andExpect(jsonPath("$[0].parentTask", is("parentask1")))
				.andExpect(jsonPath("$[0].startDate", notNullValue()))
				.andExpect(jsonPath("$[0].endDate", notNullValue()))
			//	.andExpect(jsonPath("$[0].activeTask", is(true)))
				.andExpect(jsonPath("$[1].taskId", is(2))).andExpect(jsonPath("$[1].task", is("Task2")))
				.andExpect(jsonPath("$[1].priority", is(13))).andExpect(jsonPath("$[1].parentTask", is("parentask2")))
				//.andExpect(jsonPath("$[1].activeTask", is(false)))

				.andDo(print());
		verify(taskservice, times(1)).findAllTask();
		verifyNoMoreInteractions(taskservice);
	}
	

	@Test
	public void testPostTask() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		LocalDate sd2 = LocalDate.of(2019,Month.JANUARY,20);
		LocalDate ed2 = LocalDate.of(2019,Month.MAY,18);
		Task object = new Task();
		object.setTaskId(4);
		object.setTask("Task1");
		object.setPriority(12);
		object.setParentTask("parentask1");
		object.setStartDate(sd2);
		object.setEndDate(ed2);
		
	when(taskservice.addTask(object)).thenReturn(object);
		mockMvc.perform(
				post("/addTask").contentType(APPLICATION_JSON_UTF8).content(TestUtil.ObjecttoJSON(object)))
		.andExpect(status().isCreated()).andExpect(jsonPath("$.taskId", is(1)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.taskId", is(1)))
				.andExpect(jsonPath("$.task", is("Task1"))).andExpect(jsonPath("$.priority", is(12)))
			.andExpect(jsonPath("$.parentTask", is("parentask1")))
			.andExpect(jsonPath("$.startDate", notNullValue()))
			.andExpect(jsonPath("$.endDate", notNullValue()))
			.andExpect(jsonPath("$.activeTask", is(true))).andDo(print());
	}

	@Test
	public void testPostTaskExceptin() throws Exception {
		Task object = new Task();
		object.setTaskId(4);
		object.setTask("Task1");
		object.setPriority(12);
		object.setParentTask("parentask1");
		object.setStartDate(null);
		object.setEndDate(null);
		
	//	when(tservice.viewAllTask(object)).thenThrow(new NoValuesFoundException());
		mockMvc.perform(post("/api/task/create").contentType(APPLICATION_JSON_UTF8).content(asJsonString(object)))
				.andExpect(status().isBadRequest()).andDo(print());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testUpdateTask() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		LocalDate sd2 = LocalDate.of(2019,Month.JANUARY,20);
		LocalDate ed2 = LocalDate.of(2019,Month.MAY,18);
		Task task1 = new Task();
		Task task2 = new Task();
		task1.setTaskId(1);
		task1.setTask("Task1");
		
		task1.setPriority(12);
		task1.setParentTask("parentask1");
		task1.setStartDate(sd2);
		task1.setEndDate(ed2);
	
		task2.setTaskId(1);
		task2.setTask("Task1");
		task2.setPriority(13);
		task2.setParentTask("parentask1");
		task2.setStartDate(sd2);
		task2.setEndDate(ed2);
	
		
	}

	

}
