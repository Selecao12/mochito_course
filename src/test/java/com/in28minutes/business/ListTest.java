package com.in28minutes.business;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void letsMockListsSizeMethod() {
        // Given - setup
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);

        // When - actual method call

        // Then - asserts
        assertEquals(2, listMock.size());
        assertEquals(2, listMock.size());
        assertEquals(2, listMock.size());
    }

    @Test
    public void letsMockListsSize_ReturnMultipleValues() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3);
        assertEquals(2, listMock.size());
        assertEquals(3, listMock.size());
    }

    @Test
    public void letsMockListGet() {
        List listMock = mock(List.class);

        // Argument Matcher
        when(listMock.get(anyInt())).thenReturn("in28minutes");
        assertEquals("in28minutes", listMock.get(0));
        assertEquals("in28minutes", listMock.get(1));
    }
    @Test
    public void letsMockListGet_UsingBDD() {
        // Given
        List<String> listMock = mock(List.class);
        given(listMock.get(anyInt())).willReturn("in28minutes");

        // When
        String firstElement = listMock.get(0);

        // Then
        assertThat(firstElement, is("in28minutes"));
    }

    @Test(expected = RuntimeException.class)
    public void letsMockList_throwAnException() {
        List listMock = mock(List.class);

        // Argument Matcher
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
        listMock.get(1);
    }

    @Test(expected = RuntimeException.class)
    public void letsMockList_mixingUp() {
        List listMock = mock(List.class);

        // Argument Matcher
        when(listMock.subList(anyInt(), 5)).thenThrow(new RuntimeException("Something"));
        listMock.subList(1, 5);
        fail();
    }
}
