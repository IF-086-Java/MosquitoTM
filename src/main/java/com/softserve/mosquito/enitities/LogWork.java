package com.softserve.mosquito.enitities;

import java.time.LocalDateTime;


public class LogWork {
    private Long id;
    private String logDescription;
    private LocalDateTime createdDate;
    private Long userId;
    private int loggedTime;
    private Long estimationId;


    public LogWork(Long id, String logDescription, Long userId,
                   int loggedTime, Long estimationId) {
        this.id = id;
        this.logDescription = logDescription;
        this.createdDate = LocalDateTime.now();
        this.userId = userId;
        this.loggedTime = loggedTime;
        this.estimationId = estimationId;

    }

    public LogWork(Long id, Long userId, Long estimationId) {
        this.id = id;
        this.createdDate = LocalDateTime.now();
        this.userId = userId;
        this.estimationId = estimationId;
    }

    public Long getId() {
        return id;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Long getUserId() {
        return userId;
    }

    public int getLoggedTime() {
        return loggedTime;
    }

    public void setLoggedTime(int loggedTime) {
        this.loggedTime = loggedTime;
    }

    public Long getEstimationId() {
        return estimationId;
    }

    public void setEstimationId(Long estimationId) {
        this.estimationId = estimationId;
    }

    @Override
    public String toString() {
        return "LogWork{" +
                "id=" + id +
                ", logDescription='" + logDescription + '\'' +
                ", createdDate=" + createdDate +
                ", userId=" + userId +
                ", loggedTime=" + loggedTime +
                ", estimationId=" + estimationId +
                '}';
    }

}
