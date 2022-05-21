package com.hingehealth.demo.services;

import com.hingehealth.demo.models.Node;
import com.hingehealth.demo.repositories.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TreeService {

    private final NodeRepository nodeRepository;

    public TreeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public Map<Integer, Map<String, Object>> createMapFromNode() throws Exception {
        Optional<Node> rootOptional = nodeRepository.findById(1);
        if (rootOptional.isEmpty()) {
            throw new Exception("Root does not exist!");
        } else {
            Map<Integer, Map<String, Object>> nodeMap = new HashMap<>();
            return createMapFromNode(rootOptional.get(), nodeMap);
        }
    }

    public Map<Integer, Map<String, Object>> createMapFromNode(Node node, Map<Integer, Map<String, Object>> nodeMap) {
        Map<String, Object> childrenMap = new LinkedHashMap<>();
        childrenMap.put("label", node.getLabel());
        childrenMap.put("children", new ArrayList<>());

        // The children map needs to be built before we set it
        for (Node child : node.getChildren()) {
            Map<Integer, Map<String, Object>> childMap = new LinkedHashMap<>();
            Map<String, Object> internalMap = new HashMap<>();
            internalMap.put("label", child.getLabel());
            internalMap.put("children", new ArrayList<>());
            childMap.put(child.getId(), internalMap);
            ((List) childrenMap.get("children")).add(childMap);
            createMapFromNode(child, nodeMap);
        }
        nodeMap.put(node.getId(), childrenMap);

        return nodeMap;
    }
}
