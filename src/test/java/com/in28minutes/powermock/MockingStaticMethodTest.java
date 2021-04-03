package com.in28minutes.powermock;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class MockingStaticMethodTest {

    // Specific Runner
    // Initialize UtilityClass
    // mock


    @Mock
    Dependency dependency;

    @InjectMocks
    SystemUnderTest systemUnderTest;


    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock() {

        List<Integer> stats = Arrays.asList(1, 2, 3);

        when(dependency.retrieveAllStats()).thenReturn(stats);

        PowerMockito.mockStatic(UtilityClass.class);
        // UtilityClass.class

        when(UtilityClass.staticMethod(6)).thenReturn(150);

        systemUnderTest.methodCallingAStaticMethod();

    }


}
