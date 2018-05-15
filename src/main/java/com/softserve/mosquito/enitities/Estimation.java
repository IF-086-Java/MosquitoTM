package com.softserve.mosquito.enitities;

import java.util.ArrayList;
import java.util.List;

public class Estimation {
    private Long id;
    private int estimation;
    private int remaining;
    private List<LogWork> logWorks;

    public Estimation() {
        this.id = 0L;
        this.estimation = 0;
        this.remaining = 0;
        this.logWorks = new ArrayList<>();
    }

    /**
     * DB Insert constructor
     */
    public Estimation(int estimation) {
        this();
        this.estimation = estimation;
        this.remaining = estimation;
    }

    /**
     * @param estimation - Reserved time for task (in hours)
     * @param remaining  - Time for task that remains (in hours)
     */
    public Estimation(Long id, int estimation, int remaining) {
        this(estimation);
        this.id = id;
        this.remaining = remaining;
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

    public List<LogWork> getLogWorks() {
        return logWorks;
    }

    public void setLogWorks(List<LogWork> logWorks) {
        this.logWorks = logWorks;
    }

    @Override
    public String toString() {
        return "Estimation{" +
                "id=" + id +
                ", estimation=" + estimation +
                ", remaining=" + remaining +
                ", logWorks=" + logWorks +
                '}';
    }
}
