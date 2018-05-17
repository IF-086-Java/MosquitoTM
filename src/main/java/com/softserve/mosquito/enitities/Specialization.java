package com.softserve.mosquito.enitities;

public final class Specialization {
    private Byte id;
    private String title;

    public Specialization() {
    }

    public Specialization(String title) {
        this.title = title;
    }

    public Specialization(Byte id, String title) {
        this.id = id;
        this.title = title;
    }

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

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
