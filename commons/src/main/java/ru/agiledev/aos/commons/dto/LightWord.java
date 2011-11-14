package ru.agiledev.aos.commons.dto;

import java.util.HashSet;

/**
 * Object for passing word representation from wiki
 * Created by a.kirillov
 * Date: 11/10/11 Time: 9:11 PM
 */
public class LightWord {
    String value;
    HashSet<String> stems;

    public LightWord(String value) {
        this.value = value;
    }

    public LightWord(String value, HashSet<String> stems) {
        this.value = value;
        this.stems = stems;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HashSet<String> getStems() {
        return stems;
    }

    public void setStems(HashSet<String> stems) {
        this.stems = stems;
    }
}
