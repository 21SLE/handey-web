package com.handey.web.domain;

public class ToDoBox {
    private Long toDoBoxId;
    private String toDoBoxTitle;
    private String toDoElementContent;
    private boolean completed;
    private boolean fixed;

    public Long getToDoBoxId() {
        return toDoBoxId;
    }

    public void setToDoBoxId(Long toDoBoxId) {
        this.toDoBoxId = toDoBoxId;
    }

    public String getToDoBoxTitle() {
        return toDoBoxTitle;
    }

    public void setToDoBoxTitle(String toDoBoxTitle) {
        this.toDoBoxTitle = toDoBoxTitle;
    }

    public String getToDoElementContent() {
        return toDoElementContent;
    }

    public void setToDoElementContent(String toDoElementContent) {
        this.toDoElementContent = toDoElementContent;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }
}
