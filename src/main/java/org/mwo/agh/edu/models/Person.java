package org.mwo.agh.edu.models;

import java.util.HashSet;
import java.util.Set;

public class Person {

    private String name;
    private String surname;
    private Set<Project> projects;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.projects = new HashSet<>();
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
