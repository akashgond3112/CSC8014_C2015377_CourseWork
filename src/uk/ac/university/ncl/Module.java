package uk.ac.university.ncl;

/**
 * @author akash.gond
 * @Project Learning
 * @Date 02022023
 * Copyright (C) 2023 Newcastle University, UK
 */
public class Module {

    private String name;
    private String code;
    private int semester;
    private int creditScore;

    public Module(String code, String name, int semester, int creditScore) {
        this.name = name;
        this.code = code;
        this.semester = semester;
        this.creditScore = creditScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
}
