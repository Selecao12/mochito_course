package com.in28minutes.business;

import com.in28minutes.data.api.TodoBusinessImpl;
import com.in28minutes.data.api.TodoService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


public class TodoBusinessImplInjectMockTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Mock
    TodoService todoServiceMock;

    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD() {

        // Given
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring",
                "Learn to Dance");
        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        // When
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        // Then
        assertThat(filteredTodos.size(), is(3));
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD() {

        // Given
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring",
                "Learn to Dance");
        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        // When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        then(todoServiceMock).should().deleteTodo("Learn to Dance");

        then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");

        then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD_CaptureArguments() {

        // Given
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring",
                "Learn to Dance");
        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        // When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));

    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD_CaptureArgumentsMultiple() {

        // Given
        List<String> todos = Arrays.asList("Learn to Rock And Roll", "Learn Spring",
                "Learn to Dance");
        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        // When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
        assertThat(stringArgumentCaptor.getAllValues(), is(List.of("Learn to Rock And Roll", "Learn to Dance")));
    }


}
