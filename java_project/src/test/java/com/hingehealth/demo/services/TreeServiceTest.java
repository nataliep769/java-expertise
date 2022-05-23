package com.hingehealth.demo.services;

import com.hingehealth.demo.models.Node;
import com.hingehealth.demo.repositories.NodeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TreeServiceTest {

    @InjectMocks
    private TreeService treeService;

    @Mock
    private NodeRepository nodeRepository;

    @Test
    void getTree_valid() throws Exception {
        // GIVEN
        Node root = new Node();
        root.setLabel("root node");
        root.setParent(null);
        ReflectionTestUtils.setField(root, "id", 1);

        Node child1 = new Node();
        child1.setLabel("first child");
        child1.setParent(root);
        ReflectionTestUtils.setField(child1, "id", 2);

        Node child2 = new Node();
        child2.setLabel("second child");
        child2.setParent(root);
        ReflectionTestUtils.setField(child2, "id", 3);
        ReflectionTestUtils.setField(root, "children", Arrays.asList(child1, child2));
        ReflectionTestUtils.setField(child1, "children", new ArrayList<>());
        ReflectionTestUtils.setField(child2, "children", new ArrayList<>());

        when(nodeRepository.findById(1)).thenReturn(Optional.of(root));

        // WHEN
        Map<Integer, Map<String, Object>> result = treeService.getTree();

        // THEN
        Map<Integer, Map<String, Object>> childMap1 = new HashMap<>();
        Map<String, Object> expectedInternalChildMap1 = new HashMap<>();
        expectedInternalChildMap1.put("label", "first child");
        expectedInternalChildMap1.put("children", new ArrayList<>());
        childMap1.put(2, expectedInternalChildMap1);

        Map<Integer, Map<String, Object>> childMap2 = new HashMap<>();
        Map<String, Object> expectedInternalChildMap2 = new HashMap<>();
        expectedInternalChildMap2.put("label", "second child");
        expectedInternalChildMap2.put("children", new ArrayList<>());
        childMap2.put(3, expectedInternalChildMap2);

        assertEquals("root node", result.get(1).get("label"));
        assertEquals(Arrays.asList(childMap1, childMap2), result.get(1).get("children"));
    }
}
