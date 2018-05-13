package com.softserve.mosquito.enitities;

import java.time.LocalDateTime;


public class LogWork {
    private Long id;
    private String logDescription;
    private int loggedTime;
    private LocalDateTime createdDate;
    private Long userId;
    private Long estimationId;

    public LogWork() {
        this.id = 0L;
        this.logDescription = "";
        this.loggedTime = 0;
        this.createdDate = LocalDateTime.now();
        this.userId = 0L;
        this.estimationId = 0L;
    }

    public LogWork(String logDescription, int loggedTime, Long userId, Long estimationId) {
        this();
        this.logDescription = logDescription;
        this.loggedTime = loggedTime;
        this.userId = userId;
        this.estimationId = estimationId;
    }

    public LogWork(Long id, String logDescription, LocalDateTime createdDate, Long userId,
                   int loggedTime, Long estimationId) {
        this.id = id;
        this.logDescription = logDescription;
        this.createdDate = createdDate;
        this.userId = userId;
        this.loggedTime = loggedTime;
        this.estimationId = estimationId;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    public int getLoggedTime() {
        return loggedTime;
    }

    public void setLoggedTime(int loggedTime) {
        this.loggedTime = loggedTime;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
