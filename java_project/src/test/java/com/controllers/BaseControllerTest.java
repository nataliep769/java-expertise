package com.controllers;

import com.models.NodeRequest;
import com.services.TreeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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

    @Test
    void getTree() throws Exception {
        // GIVEN
        when(treeService.getTree()).thenReturn(new HashMap<>());

        // WHEN
        baseController.getTree();

        // THEN
        verify(treeService).getTree();
    }

    @Test
    void addToTree() throws Exception {
        // GIVEN
        NodeRequest nodeRequest = new NodeRequest(7, "special node");
        when(treeService.addNodeToTree(nodeRequest)).thenReturn("You successfully added a new node to your tree!");

        // WHEN
        String response = baseController.addNodeToTree(nodeRequest);

        // THEN
        verify(treeService).addNodeToTree(nodeRequest);
        assertEquals("You successfully added a new node to your tree!", response);
    }
}
