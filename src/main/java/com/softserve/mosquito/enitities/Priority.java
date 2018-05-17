package com.softserve.mosquito.enitities;

import java.util.Objects;

public final class Priority {
    private Byte id;
    private String title;



    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Priority() {
    }

    public Priority(Byte id, String title) {
        this.id = id;
        this.title = title;
    }

    public Priority(Byte id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Priority priority = (Priority) o;
        return Objects.equals(id, priority.id) &&
                Objects.equals(title, priority.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "Priority{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}


