package com.hingehealth.demo.models;

import java.util.HashMap;
import java.util.Map;

public class NodeResponse {

    private final Map<Integer, Map<String, Object>> treeMap = new HashMap<>();

    public Map<Integer, Map<String, Object>> getTreeMap() {
        return treeMap;
    }
}
