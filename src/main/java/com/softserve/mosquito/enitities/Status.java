package com.softserve.mosquito.enitities;

import java.util.Objects;

public final class Status {
    private Byte statusId;
    private String title;

    public Status() {
    }

    public Status(Byte statusId, String title) {
    }

    public Status(Byte statusId) {
        this.statusId = statusId;
    }

    public Status(Byte statusId, String title, Boolean byDefault) {
        this.statusId = statusId;
        this.title = title;
    }

    public Byte getStatusId() {
        return statusId;
    }

    public void setStatusId(Byte statusId) {
        this.statusId = statusId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(statusId, status.statusId) &&
                Objects.equals(title, status.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(statusId, title);
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", title='" + title + '\'' +
                '}';
    }

}
