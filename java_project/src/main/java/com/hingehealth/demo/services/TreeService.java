package com.hingehealth.demo.services;

import com.hingehealth.demo.models.Node;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TreeService {

    public Map<Integer, Map<String, Object>> createTree() {
        Node node2 = new Node();
        node2.getTreeMap().put(2, createInternalMap("ant", null));

        Node node6 = new Node();
        node6.getTreeMap().put(6, createInternalMap("elephant", null));

        Node node4 = new Node();
        node4.getTreeMap().put(4, createInternalMap("cat", null));

        Node node7 = new Node();
        node7.getTreeMap().put(7, createInternalMap("frog", null));

        Node node5 = new Node();
        node5.getTreeMap().put(5, createInternalMap("dog", Collections.singletonList(node6.getTreeMap())));

        Node node3 = new Node();
        node3.getTreeMap().put(3, createInternalMap("bear", Arrays.asList(node4.getTreeMap(), node5.getTreeMap())));

        Node root = new Node();
        root.getTreeMap().put(1, createInternalMap("root", Arrays.asList(node2.getTreeMap(), node3.getTreeMap(), node7.getTreeMap())));

        return root.getTreeMap();
    }

    private Map<String, Object> createInternalMap(String label, List<Map<Integer, Map<String, Object>>> children) {
        Map<String, Object> internalMap = new LinkedHashMap<>();
        internalMap.put("label", label);
        internalMap.put("children", children);

        return internalMap;
    }
}
