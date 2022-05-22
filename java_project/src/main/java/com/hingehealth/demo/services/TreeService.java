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

    public Map<Integer, Map<String, Object>> createTreeMap() throws Exception {
        Optional<Node> rootOptional = nodeRepository.findById(1);
        if (rootOptional.isEmpty()) {
            throw new Exception("Root does not exist!");
        } else {
            Map<Integer, Map<String, Object>> nodeMap = new HashMap<>();
            return createTreeMap(rootOptional.get(), nodeMap);
        }
    }

    public String addChildToTree(Integer parentId, String label) throws Exception {
        Optional<Node> parentOptional = nodeRepository.findById(parentId);
        if (parentOptional.isEmpty()) {
            throw new Exception("A parent node with that id does not exist.");
        } else {
            Node node = new Node();
            node.setLabel(label);
            node.setParent(parentOptional.get());
            nodeRepository.save(node);

            return "You successfully added a new node to your tree!";
        }
    }

    public Map<Integer, Map<String, Object>> createTreeMap(Node node, Map<Integer, Map<String, Object>> nodeMap) {
        Map<String, Object> childrenMap = createChildrenMap(node);

        for (Node child : node.getChildren()) {
            Map<Integer, Map<String, Object>> childMap = new LinkedHashMap<>();
            Map<String, Object> internalMap = createChildrenMap(child);
            childMap.put(child.getId(), internalMap);
            ((List) childrenMap.get("children")).add(childMap);
            createTreeMap(child, childMap);
        }
        nodeMap.put(node.getId(), childrenMap);

        return nodeMap;
    }

    private Map<String, Object> createChildrenMap(Node node) {
        Map<String, Object> nodeMap = new LinkedHashMap<>();
        nodeMap.put("label", node.getLabel());
        nodeMap.put("children", new ArrayList<>());

        return nodeMap;
    }
}
