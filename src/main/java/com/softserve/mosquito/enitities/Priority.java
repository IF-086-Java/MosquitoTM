package com.softserve.mosquito.enitities;

public enum Priority {
    LOW(1L), MIDDLE(2L), HIGH(3L);

    private final Long priorityNumber;

    public Long getPriorityNumber() {
        return priorityNumber;
    }

    Priority(Long priorNumber) {
        priorityNumber = priorNumber;
    }
}