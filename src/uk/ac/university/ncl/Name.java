package uk.ac.university.ncl;

import java.util.Objects;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 02022023
 * Copyright (C) 2023 Newcastle University, UK
 */

public class Name {

    private String firstName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name name)) return false;
        return Objects.equals(firstName, name.firstName) && Objects.equals(lastName, name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public Name() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;

    @Override
    public String toString() {
        return "Name{}";
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
