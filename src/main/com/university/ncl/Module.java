package main.com.university.ncl;

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
    
}
