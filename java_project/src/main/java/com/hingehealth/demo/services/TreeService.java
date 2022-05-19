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

    public Map<Integer, Map<String, Object>> createTree() {
        Node root = new Node();
        root.setId(1);
        root.setLabel("root");
        root.setParentId(null);
        nodeRepository.save(root);
        // root.getTreeMap().put(root.getId(), createInternalMap("root", Arrays.asList(node2.getTreeMap(), node3.getTreeMap(), node7.getTreeMap())));

        Node node2 = new Node();
        node2.setId(2);
        node2.setLabel("ant");
        node2.setParentId(root.getId());
       // node2.getTreeMap().put(node2.getId(), createInternalMap("ant", null));
        nodeRepository.save(node2);

        Node node3 = new Node();
        node3.setId(3);
        node3.setLabel("bear");
        node3.setParentId(root.getId());
        //node3.getTreeMap().put(node3.getId(), createInternalMap("bear", Arrays.asList(node4.getTreeMap(), node5.getTreeMap())));
        nodeRepository.save(node3);

        Node node4 = new Node();
        node4.setId(4);
        node4.setLabel("cat");
        node4.setParentId(node3.getId());
        //node4.getTreeMap().put(node4.getId(), createInternalMap("cat", null));
        nodeRepository.save(node4);

        Node node5 = new Node();
        node5.setId(5);
        node5.setLabel("dog");
        node5.setParentId(node3.getId());
        // node5.getTreeMap().put(node5.getId(), createInternalMap("dog", Collections.singletonList(node6.getTreeMap())));
        nodeRepository.save(node5);

        Node node6 = new Node();
        node6.setId(6);
        node6.setLabel("elephant");
        node6.setParentId(node5.getId());
      //  node6.getTreeMap().put(node6.getId(), createInternalMap("elephant", null));
        nodeRepository.save(node6);

        Node node7 = new Node();
        node7.setId(7);
        node7.setLabel("frog");
        node7.setParentId(root.getParentId());
        //node7.getTreeMap().put(node7.getId(), createInternalMap("frog", null));
        nodeRepository.save(node7);


        return new HashMap<>();
    }

//    public Node addChildToTree(Integer parentId, String label) throws Exception {
//        Optional<Node> parentNodeOptional = nodeRepository.findById(parentId); // Need to add null check
//
//        if (parentNodeOptional.isEmpty()) {
//            throw new Exception("A id supplied does not match any existing nodes");
//        } else {
//            Node parentNode = parentNodeOptional.get();
//            Node node = new Node();
//            node.setId(9); // do the save right here to generate the id for this node?
//            node.getTreeMap().put(node.getId(), createInternalMap(label, null));
//
//            Map<String, Object> children = parentNode.getTreeMap().get("children");
//            children.put(label, null);
//
//            return node;
//        }
//
//    }

    private Map<String, Object> createInternalMap(String label, List<Map<Integer, Map<String, Object>>> children) {
        Map<String, Object> internalMap = new LinkedHashMap<>();
        internalMap.put("label", label);
        internalMap.put("children", children);

        return internalMap;
    }
}
