package ru.agiledev.aos.frontend.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;

/**
 * Created by Anton Kirillov
 * Date: 8/12/11
 */
public class TransformationResults implements IsSerializable {
    Integer id;
    String initialQuery;
    String transformedQuery;
    String firstObject;
    String secondObject;
    String thirdObject;
    String questionType;
    List<String> queries;




    //constructors

    public TransformationResults(){}

    public TransformationResults(String initialQuery, String transformedQuery, String firstObject, String secondObject, String thirdObject, String questionType, List<String> queries) {
        this.initialQuery = initialQuery;
        this.transformedQuery = transformedQuery;
        this.firstObject = firstObject;
        this.secondObject = secondObject;
        this.thirdObject = thirdObject;
        this.questionType = questionType;
        this.queries = queries;
    }

    //setters and getters
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
