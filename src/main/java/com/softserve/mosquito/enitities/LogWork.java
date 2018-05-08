package com.softserve.mosquito.enitities;

import java.util.Date;

public class LogWork {

    private Long id;
    private String logDescription;
    private Date createdDate;
    private Long userId;
    private int commitLog;
    private Long estimationId;


    public LogWork(Long id, String logDescription,
                   Date createdDate, Long userId,
                   int commitLog, Long estimationId) {
        this.id = id;
        this.logDescription = logDescription;
        this.createdDate = createdDate;
        this.userId = userId;
        this.commitLog = commitLog;
        this.estimationId = estimationId;

    }

    public LogWork(Long id, Long userId, Long estimationId) {
        this.id = id;
        this.userId = userId;
        this.estimationId = estimationId;
    }

    public LogWork() {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getCommitLog() {
        return commitLog;
    }

    public void setCommitLog(int commitLog) {
        this.commitLog = commitLog;
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
                ", commitLog=" + commitLog +
                ", estimationId=" + estimationId +
                '}';
    }

}
