package com.hingehealth.demo.models;


public class NodeRequest {

    private Integer parentId;

    private String label;

    public NodeRequest(Integer parentId, String label) {
        this.parentId = parentId;
        this.label = label;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
