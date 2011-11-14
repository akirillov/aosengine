package ru.agiledev.aos.data.domain;

/**
 * Created by a.kirillov
 * Date: 11/2/11 Time: 11:14 PM
 */
public enum WordTypes {

    TYPE_WORD("TYPE_WORD"),
    SECOND_TYPE_WORD("SECOND_TYPE_WORD"),
    EMPTY_CHAIN_PRODUCER("EMPTY_CHAIN_PRODUCER"),
    START_WORD("START_WORD");

    private String type;

    WordTypes(String s) {
        this.type = s;
    }
}
