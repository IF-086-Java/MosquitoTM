package com.softserve.mosquito.enitities;

import java.util.List;

public class Estimation {

    private Long id;
    private int estimation;
    private int remaining;
    private List<LogWork> logWorks;


    public Estimation(Long id, int estimation,
                      int remaining, List<LogWork> logWorks) {
        this.id = id;
        this.estimation = estimation;
        this.remaining = remaining;
        this.logWorks = logWorks;
    }

    public Estimation(Long id) {
        this.id = id;
    }

    public Estimation() {
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
