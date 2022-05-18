package com.hingehealth.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private final Map<Integer, Map<String, Object>> treeMap = new HashMap<>();

    public Map<Integer, Map<String, Object>> getTreeMap() {
        return treeMap;
    }
}
