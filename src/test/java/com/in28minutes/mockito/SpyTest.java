package com.in28minutes.mockito;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SpyTest {

    @Test
    public void test() {
        List arrayListSpy = spy(ArrayList.class);
        arrayListSpy.add("Dummy");

        verify(arrayListSpy).add("Dummy");
        verify(arrayListSpy, never()).clear();


    }
}
