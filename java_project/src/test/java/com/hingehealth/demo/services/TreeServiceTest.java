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

import static org.junit.jupiter.api.Assertions.*;
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
        Node root = new Node(null, "root node");
        Node child1 = new Node(root, "first child");
        Node child2 = new Node(root, "second child");
        Node childOfChild = new Node(child2, "nested child");

        ReflectionTestUtils.setField(root, "id", 1);
        ReflectionTestUtils.setField(child1, "id", 2);
        ReflectionTestUtils.setField(child2, "id", 3);
        ReflectionTestUtils.setField(childOfChild, "id", 4);

        ReflectionTestUtils.setField(root, "children", Arrays.asList(child1, child2));
        ReflectionTestUtils.setField(child1, "children", new ArrayList<>());
        ReflectionTestUtils.setField(child2, "children", Collections.singletonList(childOfChild));
        ReflectionTestUtils.setField(childOfChild, "children", new ArrayList<>());

        when(nodeRepository.findById(1)).thenReturn(Optional.of(root));

        // WHEN
        Map<Integer, Map<String, Object>> result = treeService.getTree();

        // THEN
        Map<Integer, Map<String, Object>> childMap1 = new HashMap<>();
        Map<String, Object> expectedInternalChildMap1 = new LinkedHashMap<>();
        expectedInternalChildMap1.put("label", "first child");
        expectedInternalChildMap1.put("children", new ArrayList<>());
        childMap1.put(2, expectedInternalChildMap1);

        Map<Integer, Map<String, Object>> nestedChildMap = new HashMap<>();
        Map<String, Object> expectedNestedChildMap = new LinkedHashMap<>();
        expectedNestedChildMap.put("label", "nested child");
        expectedNestedChildMap.put("children", new ArrayList<>());
        nestedChildMap.put(4, expectedNestedChildMap);

        Map<Integer, Map<String, Object>> childMap2 = new HashMap<>();
        Map<String, Object> expectedInternalChildMap2 = new LinkedHashMap<>();
        expectedInternalChildMap2.put("label", "second child");
        expectedInternalChildMap2.put("children", Collections.singletonList(nestedChildMap));
        childMap2.put(3, expectedInternalChildMap2);

        assertNotNull(result.get(1));
        assertEquals("root node", result.get(1).get("label"));
        assertEquals(Arrays.asList(childMap1, childMap2), result.get(1).get("children"));
    }

    @Test
    void getTree_rootDoesNotExist_throwsException() {
        // GIVEN
        when(nodeRepository.findById(1)).thenReturn(Optional.empty());

        // WHEN
        Exception exception = assertThrows(Exception.class, () -> treeService.getTree());

        // THEN
        assertEquals("Root does not exist!", exception.getMessage());
    }
}
