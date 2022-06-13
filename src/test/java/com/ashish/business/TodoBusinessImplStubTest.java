package com.ashish.business;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.ashish.data.api.TodoService;
import com.ashish.data.api.TodoServiceStub;

public class TodoBusinessImplStubTest {
	
	@Test
	public void usingAStub() {
		TodoService todoService = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> filteredTodos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");
		assertEquals(2, filteredTodos.size());
	}

}

