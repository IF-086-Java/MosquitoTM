package com.softserve.mosquito.enitities;

public enum Status {
    TODO(1L), DOING(2L), DONE(3L);


    private final Long statusNumber;

    Status(Long statNumber) {
        statusNumber = statNumber;
    }

    public Long getStatusNumber() {
        return statusNumber;
    }
}
