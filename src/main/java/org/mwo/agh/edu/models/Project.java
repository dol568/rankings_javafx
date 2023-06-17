package org.mwo.agh.edu.models;

import java.util.HashSet;
import java.util.Set;

public class Project {

    private String name;
    private Set<Activity> activities;

    public Project(String name) {
        this.name = name;
        this.activities = new HashSet<>();
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}
