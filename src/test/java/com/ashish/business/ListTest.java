package com.ashish.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void letsMockListSizeMethod() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
	}

	@Test
	public void letsMockListSize_ReturnMultipleValues() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3);
		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
	}

	@Test
	public void letsMockListGet() {
		List listMock = mock(List.class);
		// Argument Matcher
		when(listMock.get(anyInt())).thenReturn("Ashish");
		assertEquals("Ashish", listMock.get(0));
		assertEquals("Ashish", listMock.get(1));		
		//assertEquals(null, listMock.get(1));

	}
	
	@Test
	public void letsMockListGet_usingBDD() {
		//Given
		List<String> listMock = mock(List.class);
		// Argument Matcher
		given(listMock.get(anyInt())).willReturn("Ashish");
		
		//When
		String firstElement = listMock.get(0);
		
		//Then
		assertThat(firstElement, is("Ashish"));
        //assertEquals("Ashish", firstElement);
        //assertEquals("Ashish", firstElement);		
		//assertEquals(null, listMock.get(1));

	}

	@Test(expected=RuntimeException.class)
	public void letsMockList_throwAnException() {
		List listMock = mock(List.class);
		// Argument Matcher
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
		listMock.get(0);

	}

}
