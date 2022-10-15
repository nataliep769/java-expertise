package demo.controllers;

import demo.models.NodeRequest;
import demo.services.TreeService;
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
class TreeControllerTest {

    @Mock
    private TreeService treeService;

    @InjectMocks
    private TreeController treeController;

    @Test
    void testHelloWorld() {
        String responseContents = treeController.helloWorld();
        assertEquals("Hello World!", responseContents);
    }

    @Test
    void getTree() throws Exception {
        // GIVEN
        when(treeService.getTree()).thenReturn(new HashMap<>());

        // WHEN
        treeController.getTree();

        // THEN
        verify(treeService).getTree();
    }

    @Test
    void addToTree() throws Exception {
        // GIVEN
        NodeRequest nodeRequest = new NodeRequest(7, "special node");
        when(treeService.addNodeToTree(nodeRequest)).thenReturn("You successfully added a new node to your tree!");

        // WHEN
        String response = treeController.addNodeToTree(nodeRequest);

        // THEN
        verify(treeService).addNodeToTree(nodeRequest);
        assertEquals("You successfully added a new node to your tree!", response);
    }
}
