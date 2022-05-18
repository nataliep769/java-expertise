package com.hingehealth.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BaseControllerTest {

    private TreeService treeService;
    private BaseController baseController = new BaseController(treeService);

    @Test
    void testHelloWorld() {
        String responseContents = baseController.helloWorld();
        assertEquals("Hello World!", responseContents);
    }
}
