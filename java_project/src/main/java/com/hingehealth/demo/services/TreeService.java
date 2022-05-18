package com.hingehealth.demo.services;

import com.hingehealth.demo.models.Node;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TreeService {

    public Node buildTree() {
        Node node2 = new Node();
        node2.getIdToNodesMap().put(2, createInternalMap("ant", null));

        Node node6 = new Node();
        node6.getIdToNodesMap().put(6, createInternalMap("elephant", null));

        Node node4 = new Node();
        node4.getIdToNodesMap().put(4, createInternalMap("cat", null));

        Node node7 = new Node();
        node7.getIdToNodesMap().put(7, createInternalMap("frog", null));

        Node node5 = new Node();
        node5.getIdToNodesMap().put(5, createInternalMap("dog", Collections.singletonList(node6)));

        Node node3 = new Node();
        node3.getIdToNodesMap().put(3, createInternalMap("bear", Arrays.asList(node4, node5)));

        Node root = new Node();
        root.getIdToNodesMap().put(1, createInternalMap("root", Arrays.asList(node2, node3, node7)));

        return root;
    }

    private Map<String, Object> createInternalMap(String label, List<Node> children) {
        Map<String, Object> internalMap = new HashMap<>();
        internalMap.put("children", children);
        internalMap.put("label", label);

        return internalMap;
    }
}
