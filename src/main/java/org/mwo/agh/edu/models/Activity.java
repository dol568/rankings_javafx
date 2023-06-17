package org.mwo.agh.edu.models;

import java.time.LocalDate;

public class Activity {

    private LocalDate date;
    private String description;
    private Double duration;

    public Activity(LocalDate date, String description, double duration) {
        this.date = date;
        this.description = description;
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}

