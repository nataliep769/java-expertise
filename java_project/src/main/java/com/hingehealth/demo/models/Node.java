package com.hingehealth.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Node {

    @JsonProperty(value = " ")
    private Map<Integer, Map<String, Object>> idToNodesMap = new HashMap<>();

    public Map<Integer, Map<String, Object>> getIdToNodesMap() {
        return idToNodesMap;
    }
}
