package com.hingehealth.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "node")
public class Node {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "label")
    private String label;

    @ManyToOne
    private Node parent;

    @OneToMany(mappedBy="parent")
    private List<Node> children;

    public Node() {
    }

    public Node(Integer id, String label) {
        this.id = id;
        this.label = label;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
