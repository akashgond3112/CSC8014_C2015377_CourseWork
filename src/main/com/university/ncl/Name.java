package main.com.university.ncl;

import java.util.Objects;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 02022023
 * Copyright (C) 2023 Newcastle University, UK
 */

public class Name {

    private final String firstName;
    private final String lastName;

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
