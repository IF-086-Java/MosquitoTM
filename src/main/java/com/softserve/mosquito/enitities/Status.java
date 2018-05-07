package com.softserve.mosquito.enitities;

public enum Status {
    READY(1), IN_PROGRESS (2), DONE(3);


    public final int statusNumber;

    Status(int statNumber) {
        statusNumber = statNumber;
    }


}
