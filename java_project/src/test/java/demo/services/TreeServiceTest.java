package demo.services;

import demo.models.Node;
import demo.models.NodeRequest;
import demo.repositories.NodeRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TreeServiceTest {

    private TreeService treeService;

    @Captor
    private ArgumentCaptor<Node> nodeCaptor;

    @Mock
    private NodeRepository nodeRepository;

    @BeforeEach
    public void setup() {
        treeService = new TreeService(nodeRepository);
    }

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
        Exception exception = assertThrows(NotFoundException.class, () -> treeService.getTree());

        // THEN
        assertEquals("Root does not exist!", exception.getMessage());
    }

    @Test
    void addNodeToTree_valid() throws Exception {
        // GIVEN
        NodeRequest nodeRequest = new NodeRequest(1, "hello");
        Node parentNode = new Node(null, "parent node");
        when(nodeRepository.findById(1)).thenReturn(Optional.of(parentNode));

        // WHEN
        String success = treeService.addNodeToTree(nodeRequest);

        // THEN
        verify(nodeRepository).save(nodeCaptor.capture());
        Node expectedNode = nodeCaptor.getValue();
        assertEquals("hello", expectedNode.getLabel());
        assertEquals(parentNode, expectedNode.getParent());
        assertEquals("You successfully added a new node to your tree!", success);
    }

    @Test
    void addNodeToTree_parentDoesNotExist_throwsException() {
        // GIVEN
        NodeRequest nodeRequest = new NodeRequest(1, "hello");
        when(nodeRepository.findById(1)).thenReturn(Optional.empty());

        // WHEN
        Exception exception = assertThrows(NotFoundException.class, () -> treeService.addNodeToTree(nodeRequest));

        // THEN
        assertEquals("A parent node with that id does not exist.", exception.getMessage());
    }
}
