package com.ashish.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.ashish.data.api.TodoService;

public class TodoBusinessImplMockTest {

	@Test
	public void testRetrieveTodosrElatedToSpring_usingAMock() {
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
	}

	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDDMock() {
		// Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		// when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(allTodos);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		// When
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		// Then
		assertThat(filteredTodos.size(), is(2));
	}

	@Test
	public void testDeleteTodoNotRelatedToSpring_usingBDDMock() {

		// Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		// when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(allTodos);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

		// Then
		// verify(todoServiceMock).deleteTodo("Learn to Dance");
		then(todoServiceMock).should().deleteTodo("Learn to Dance");
		// verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
		// verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
		// verify(todoServiceMock, atLeast(5)).deleteTodo("Learn to Dance");

		// verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");

		// verify(todoServiceMock, never()).deleteTodo("Learn Spring");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring");

	}

	@Test
	public void testDeleteTodoNotRelatedToSpring_usingBDD_argumentCapture() {

		// Declare Argument Captor
		ArgumentCaptor<String> stringArgumentCapture = ArgumentCaptor.forClass(String.class);
		// Define Argument Captor on specific method call
		// Capture the argument

		// Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		// when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(allTodos);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

		// Then
		then(todoServiceMock).should().deleteTodo(stringArgumentCapture.capture());
		assertThat(stringArgumentCapture.getValue(), is("Learn to Dance"));

	}

	@Test
	public void testDeleteTodoNotRelatedToSpring_usingBDD_argumentCaptureMultipleTimes() {

		// Declare Argument Captor
		ArgumentCaptor<String> stringArgumentCapture = ArgumentCaptor.forClass(String.class);
		// Define Argument Captor on specific method call
		// Capture the argument

		// Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to Dance");
		// when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(allTodos);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

		// Then
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCapture.capture());
		assertThat(stringArgumentCapture.getAllValues().size(), is(2));

	}
}
