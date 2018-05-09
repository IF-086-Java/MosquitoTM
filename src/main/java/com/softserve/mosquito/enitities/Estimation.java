package com.softserve.mosquito.enitities;

import java.util.ArrayList;
import java.util.List;

public class Estimation {
    private Long id;
    private int estimation;
    private int remaining;
    private List<LogWork> logs;

    /**
     * DB Insert constructor
     * @param estimation - Reserved time for task (in hours)
     * @param remaining - Time for task that remains (in hours)
     */
    public Estimation(int estimation, int remaining) {
        this.estimation = estimation;
        this.remaining = remaining;
        this.logs = new ArrayList<>();
    }

    public Estimation(Long id, int estimation, int remaining) {
        this.id = id;
        this.estimation = estimation;
        this.remaining = remaining;
        this.logs = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public List<LogWork> getLogs() {
        return logs;
    }

    public void setLogs(List<LogWork> logs) {
        this.logs = logs;
    }

    @Override
    public String toString() {
        return "Estimation{" +
                "id=" + id +
                ", estimation=" + estimation +
                ", remaining=" + remaining +
                ", logs=" + logs +
                '}';
    }
}
