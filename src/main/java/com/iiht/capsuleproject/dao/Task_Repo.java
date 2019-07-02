package com.iiht.capsuleproject.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iiht.capsuleproject.model.Task;

@Repository
public interface Task_Repo extends CrudRepository<Task, Long> {
	

}
