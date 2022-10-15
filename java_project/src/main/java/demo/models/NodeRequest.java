package demo.models;

import javax.validation.constraints.NotNull;

public class NodeRequest {

    @NotNull(message = "Parent is required")
    private Integer parentId;

    @NotNull(message = "Label is required")
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
