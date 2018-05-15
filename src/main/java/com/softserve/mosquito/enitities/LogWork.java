package com.softserve.mosquito.enitities;

import java.time.LocalDateTime;


public class LogWork {
    private Long id;
    private String description;
    private int logged;
    private Long userId;
    private Long estimationId;
    private LocalDateTime lastUpdate;

    public LogWork() {
        this.id = 0L;
        this.description = "";
        this.logged = 0;
        this.userId = 0L;
        this.estimationId = 0L;
    }

    public LogWork(String description, int logged, Long userId, Long estimationId) {
        this();
        this.description = description;
        this.logged = logged;
        this.userId = userId;
        this.estimationId = estimationId;
    }

    public LogWork(Long id, String description, int logged, Long userId,
                   Long estimationId, LocalDateTime lastUpdate) {
        this.id = id;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.userId = userId;
        this.logged = logged;
        this.estimationId = estimationId;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLogged() {
        return logged;
    }

    public void setLogged(int logged) {
        this.logged = logged;
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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "LogWork{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", userId=" + userId +
                ", logged=" + logged +
                ", estimationId=" + estimationId +
                '}';
    }

}
