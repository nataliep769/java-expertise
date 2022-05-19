package com.hingehealth.demo;

import com.hingehealth.demo.models.Node;
import com.hingehealth.demo.repositories.NodeRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements ApplicationRunner {

    private final NodeRepository nodeRepository;

    public DataLoader(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public void run(ApplicationArguments args) {
        Node root = new Node(1, "root");
        Node node2 = new Node(2, "ant");
        Node node3 = new Node(3, "bear");
        Node node4 = new Node(4,"cat");
        Node node5 = new Node(5, "dog");
        Node node6 = new Node(6,"elephant");
        Node node7 = new Node(7,  "frog");

        node2.setParent(root);
        node3.setParent(root);
        node7.setParent(root);
        node4.setParent(node3);
        node5.setParent(node3);
        node6.setParent(node5);

        nodeRepository.saveAll(Arrays.asList(root, node2, node3, node4, node5, node6, node7));
    }

}
