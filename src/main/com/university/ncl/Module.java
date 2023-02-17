package main.com.university.ncl;

import java.util.Objects;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 02022023
 * Copyright (C) 2023 Newcastle University, UK
 */
public class Module {

    private final String name;
    private final String code;
    private final int semester;
    private final int creditScore;

    public Module(String code, String name, int semester, int creditScore) {
        this.name = name;
        this.code = code;
        this.semester = semester;
        this.creditScore = creditScore;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;
        Module module = (Module) o;
        return getSemester() == module.getSemester() && getCreditScore() == module.getCreditScore() && getName().equals(module.getName()) && getCode().equals(module.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCode(), getSemester(), getCreditScore());
    }

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
