package com.softserve.mosquito.enitities;

public enum Priority {
    LOW(1), MIDDLE(2), HIGH(3);

    public final int priorityNumber;

    Priority(int priorNumber) {
        priorityNumber = priorNumber;
    }
}
