package com.softserve.mosquito.enitities;

public enum Status {
    READY(1L), IN_PROGRESS(2L), DONE(3L);


    private final Long statusNumber;

    Status(Long statNumber) {
        statusNumber = statNumber;
    }

    public Long getStatusNumber() {
        return statusNumber;
    }
}
