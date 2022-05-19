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

    // Version without persistence
    public Map<Integer, Map<String, Object>> createTreeNoPersistence() {
        NodeResponse node2 = new NodeResponse();
        node2.getTreeMap().put(2, createInternalMap("ant", null));

        NodeResponse node6 = new NodeResponse();
        node6.getTreeMap().put(6, createInternalMap("elephant", null));

        NodeResponse node4 = new NodeResponse();
        node4.getTreeMap().put(4, createInternalMap("cat", null));

        NodeResponse node7 = new NodeResponse();
        node7.getTreeMap().put(7, createInternalMap("frog", null));

        NodeResponse node5 = new NodeResponse();
        node5.getTreeMap().put(5, createInternalMap("dog", Collections.singletonList(node6.getTreeMap())));

        NodeResponse node3 = new NodeResponse();
        node3.getTreeMap().put(3, createInternalMap("bear", Arrays.asList(node4.getTreeMap(), node5.getTreeMap())));

        NodeResponse root = new NodeResponse();
        root.getTreeMap().put(1, createInternalMap("root", Arrays.asList(node2.getTreeMap(), node3.getTreeMap(), node7.getTreeMap())));

        return root.getTreeMap();
    }

    public Map<Integer, Map<String, Object>> buildTree() {
        List<Node> nodes = nodeRepository.findAll();
        NodeResponse nodeResponse = new NodeResponse();

        Map<Integer, Map<String, Object>> treeMap = new HashMap<>();
        for (Node node : nodes) {
            Node temp = node;
            if (node.getChildren().isEmpty()) {
                treeMap.put(node.getId(), createInternalMap(node.getLabel(), null));
            } else {

            }
        }

        return treeMap;
    }

    private Map<String, Object> createInternalMap(String label, List<Map<Integer, Map<String, Object>>> children) {
        Map<String, Object> internalMap = new LinkedHashMap<>();
        internalMap.put("label", label);
        internalMap.put("children", children);

        return internalMap;
    }
}
