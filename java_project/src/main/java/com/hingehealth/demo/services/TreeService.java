package com.hingehealth.demo.services;

import com.hingehealth.demo.models.Node;
import com.hingehealth.demo.models.NodeResponse;
import com.hingehealth.demo.repositories.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TreeService {

    private final NodeRepository nodeRepository;

    public TreeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    // Save these on load
    public void getTree() {
        List<Node> nodes = nodeRepository.findAll();
        NodeResponse nodeResponse = new NodeResponse();

        Map<Integer, Map<String, Object>> treeMap = new HashMap<>();

        // TODO: Should this be recursive?
        for (Node node : nodes) {
            Map<String, Object> internalMap = new LinkedHashMap<>();
            internalMap.put("label", node.getLabel());
            internalMap.put("children", node.getChildren()); // worry about stripping extra values out later
            treeMap.put(node.getId(), internalMap);

            // Use a while loop and build tree structure?
            if (node.getChildren() != null) {
                for (Node child : node.getChildren()) {
                    Map<String, Object> childrenMap = new LinkedHashMap<>();
                    childrenMap.put("label", child.getLabel());
                    childrenMap.put("children", child.getChildren());
                }
            }
        }
        // root.getTreeMap().put(root.getId(), createInternalMap("root", Arrays.asList(node2.getTreeMap(), node3.getTreeMap(), node7.getTreeMap())));
        // node2.getTreeMap().put(node2.getId(), createInternalMap("ant", null));// Can we just find the newly added node by its parent id?
        //node3.getTreeMap().put(node3.getId(), createInternalMap("bear", Arrays.asList(node4.getTreeMap(), node5.getTreeMap())));
        //node4.getTreeMap().put(node4.getId(), createInternalMap("cat", null));
        // node5.getTreeMap().put(node5.getId(), createInternalMap("dog", Collections.singletonList(node6.getTreeMap())));
        //  node6.getTreeMap().put(node6.getId(), createInternalMap("elephant", null));
        //node7.getTreeMap().put(node7.getId(), createInternalMap("frog", null));
    }

    private Map<String, Object> createInternalMap(String label, List<Map<Integer, Map<String, Object>>> children) {
        Map<String, Object> internalMap = new LinkedHashMap<>();
        internalMap.put("label", label);
        internalMap.put("children", children);

        return internalMap;
    }
}
