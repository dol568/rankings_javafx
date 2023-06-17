package org.mwo.agh.edu.models;

import java.util.HashSet;
import java.util.Set;

public class SpreadSheet {

    private Set<Person> persons;

    public SpreadSheet() {
        this.persons = new HashSet<>();
    }

    public void addPerson(Person person) {
        this.persons.add(person);
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
