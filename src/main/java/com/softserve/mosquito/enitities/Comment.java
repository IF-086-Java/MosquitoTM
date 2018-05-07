package com.softserve.mosquito.enitities;

import java.util.Date;

public class Comment {
    private Long id;
    private Long taskId;
    private Long authorId;
    private String text;
    private Date date;

    public Comment(Long id, Long taskId, Long authorId, String text, Date date) {
        this.id = id;
        this.taskId = taskId;
        this.authorId = authorId;
        this.text = text;
        this.date = date;
    }

    public Comment(Long taskId, Long authorId, String text) {
        this.taskId = taskId;
        this.authorId = authorId;
        this.text = text;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", authorId=" + authorId +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
