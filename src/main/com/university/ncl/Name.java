package main.com.university.ncl;

import java.util.Objects;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 02022023
 * Copyright (C) 2023 Newcastle University, UK
 */

public final class Name {

    private final String firstName;
    private final String lastName;

    /**
     * Create Module with given code, name, semester, creditScore
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @throws NullPointerException if either of the params is null
     */
    public Name(String firstName, String lastName) {
        if (firstName == null && lastName == null)
            throw new NullPointerException("first name and last name cannot be null");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // utility getter methods
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name)) return false;
        Name name = (Name) o;
        return getFirstName().equals(name.getFirstName()) && getLastName().equals(name.getLastName());
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return (this.firstName + " " + this.lastName);
    }

}
