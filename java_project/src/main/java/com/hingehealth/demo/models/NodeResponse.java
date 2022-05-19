package com.hingehealth.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

public class NodeResponse {

    private final Map<Integer, Map<String, Object>> treeMap = new HashMap<>();

    public Map<Integer, Map<String, Object>> getTreeMap() {
        return treeMap;
    }

    /*
    *     @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "treemap")
    private final Map<Integer, Map<String, Object>> treeMap = new HashMap<>();

    public Map<Integer, Map<String, Object>> getTreeMap() {
        return treeMap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    * */
}
