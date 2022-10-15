package demo.services;

import demo.repositories.NodeRepository;
import demo.models.Node;
import demo.models.NodeRequest;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TreeService {

    private final NodeRepository nodeRepository;

    public TreeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public Map<Integer, Map<String, Object>> getTree() throws NotFoundException {
        Optional<Node> rootOptional = nodeRepository.findById(1);
        if (rootOptional.isEmpty()) {
            throw new NotFoundException("Root does not exist!");
        } else {
            Map<Integer, Map<String, Object>> nodeMap = new HashMap<>();
            return getTree(rootOptional.get(), nodeMap);
        }
    }

    public String addNodeToTree(NodeRequest nodeRequest) throws NotFoundException {
        Optional<Node> parentOptional = nodeRepository.findById(nodeRequest.getParentId());
        if (parentOptional.isEmpty()) {
            throw new NotFoundException("A parent node with that id does not exist.");
        } else {
            Node node = new Node(parentOptional.get(), nodeRequest.getLabel());
            nodeRepository.save(node);

            return "You successfully added a new node to your tree!";
        }
    }

    public Map<Integer, Map<String, Object>> getTree(Node node, Map<Integer, Map<String, Object>> nodeMap) {
        Map<String, Object> childrenMap = createChildrenMap(node);

        for (Node child : node.getChildren()) {
            Map<Integer, Map<String, Object>> childMap = new LinkedHashMap<>();
            Map<String, Object> internalMap = createChildrenMap(child);
            childMap.put(child.getId(), internalMap);
            ((List) childrenMap.get("children")).add(childMap);
            getTree(child, childMap);
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
