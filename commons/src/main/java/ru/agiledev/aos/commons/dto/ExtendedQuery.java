package ru.agiledev.aos.commons.dto;

/**
 * Created by Anton Kirillov
 * Date: 8/12/11
 */

import java.util.List;

/**
 * DTO for extended query with meta-information (frontend class @see ru.agiledev.aos.frontend.shared.TransformationResults)
 */
public class ExtendedQuery {
    Integer id;
    String initialQuery;
    String transformedQuery;
    String firstObject;
    String secondObject;
    String thirdObject;
    String questionType;
    List<String> queries;



    //getters and setters


    public Integer getId() {
        return id;
    }

    public String getInitialQuery() {
        return initialQuery;
    }

    public void setInitialQuery(String initialQuery) {
        this.initialQuery = initialQuery;
    }

    public String getTransformedQuery() {
        return transformedQuery;
    }

    public void setTransformedQuery(String transformedQuery) {
        this.transformedQuery = transformedQuery;
    }

    public String getFirstObject() {
        return firstObject;
    }

    public void setFirstObject(String firstObject) {
        this.firstObject = firstObject;
    }

    public String getSecondObject() {
        return secondObject;
    }

    public void setSecondObject(String secondObject) {
        this.secondObject = secondObject;
    }

    public String getThirdObject() {
        return thirdObject;
    }

    public void setThirdObject(String thirdObject) {
        this.thirdObject = thirdObject;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<String> getQueries() {
        return queries;
    }

    public void setQueries(List<String> queries) {
        this.queries = queries;
    }
}
