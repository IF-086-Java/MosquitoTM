package com.softserve.mosquito.enitities;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private String text;
    private Long taskId;
    private Long authorId;
    private LocalDateTime lastUpdate;

    public Comment() {
    }

    public Comment(String text, Long taskId, Long authorId) {
        this.text = text;
        this.taskId = taskId;
        this.authorId = authorId;
    }

    public Comment(Long id, String text, Long taskId, Long authorId,  LocalDateTime lastUpdate) {
        this.id = id;
        this.taskId = taskId;
        this.authorId = authorId;
        this.text = text;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", taskId=" + taskId +
                ", authorId=" + authorId +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
