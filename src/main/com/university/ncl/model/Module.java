package main.com.university.ncl.model;

import java.util.Objects;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 02022023
 * Copyright (C) 2023 Newcastle University, UK
 */
public final class Module {

    private final String name;
    private final String code;
    private final int semester;
    private final int creditScore;

    /**
     * Create Module with given code, name, semester, creditScore
     *
     * @param code        the module code
     * @param name        the module name
     * @param semester    the semester in which module will be taught
     * @param creditScore the credit score for the module
     * @throws NullPointerException if either of the params is null
     */
    public Module(String code, String name, int semester, int creditScore) {
        if (code == null && name == null && semester == 0 && creditScore == 0)
            throw new NullPointerException("Please check the params provide for creating the Module object.");
        this.name = name;
        this.code = code;
        this.semester = semester;
        this.creditScore = creditScore;
    }

    // utility getter methods
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getSemester() {
        return semester;
    }

    public int getCreditScore() {
        return creditScore;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;
        Module module = (Module) o;
        return getSemester() == module.getSemester() && getCreditScore() == module.getCreditScore() && getName().equals(module.getName()) && getCode().equals(module.getCode());
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCode(), getSemester(), getCreditScore());
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", semester=" + semester +
                ", creditScore=" + creditScore +
                '}';
    }


}
