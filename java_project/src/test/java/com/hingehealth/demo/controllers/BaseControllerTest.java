package com.hingehealth.demo.controllers;

import com.hingehealth.demo.services.TreeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseControllerTest {

    @Mock
    private TreeService treeService;

    @InjectMocks
    private BaseController baseController;

    @Test
    void testHelloWorld() {
        String responseContents = baseController.helloWorld();
        assertEquals("Hello World!", responseContents);
    }
}
