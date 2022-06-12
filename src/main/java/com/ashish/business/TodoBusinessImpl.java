package com.ashish.business;

import java.util.ArrayList;
import java.util.List;

import com.ashish.data.api.TodoSevice;
//TodoBusinessImpl SUT
//TodoService Dependency

public class TodoBusinessImpl {
	private TodoSevice todoService;

	public TodoBusinessImpl(TodoSevice todoService) {
		super();
		this.todoService = todoService;
	}
	
	public List<String> retriveTodosRelatedToSpring(String user){
		List<String> filteredTodos = new ArrayList<String>();
		List<String> todos = todoService.retrieveTodos(user);
		
		for(String todo:todos) {
			if(todo.contains("Spring")) {
				filteredTodos.add(todo);
			}
		}
		return filteredTodos;
		
	}

}
