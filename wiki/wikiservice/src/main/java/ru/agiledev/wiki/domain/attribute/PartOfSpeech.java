package ru.agiledev.wiki.domain.attribute;

/**
 * Enum represents speech part.
 *
 * Created by a.kirillov
 * Date: 9/24/11 Time: 12:07 PM
 */
public enum PartOfSpeech {
    NOUN("существительное"),
    ADJECTIVE("прилагательное"),
    ADJECTIVE_PRONOUN("местоименное прилагательное"),
    NUMERAL("числительное"),
    ANUMERAL("порядковое числительное"),
    PRONOUN("местоимение"),
    VERB("глагол"),
    ADVERB("наречие"),
    ADVERB_PRONOUN("местоименное наречие"),
    PREPOSITION("предлог"),
    CONJUNCTION("союз"),
    PARTICLE("частица"),
    COM("часть композита (первая часть сложных слов)"),
    INTERJECTION("междометие"),
    UNDEFINED("неизвестно");

    private String wordType;

    private PartOfSpeech(String wordType) {
        this.wordType = wordType;
    }

    /**
     * Method for transforming mystem output properties to part of speech from enum
     * @param property String from mystem properties output
     * @return part of speech in form of enum
     */
    public static PartOfSpeech fromProperties(String property){
        if("A".equals(property)) return ADJECTIVE; //прилагательное
        if("ADV".equals(property)) return ADVERB;  //наречие
        if("ADVPRO".equals(property)) return ADVERB_PRONOUN;  //местоименное наречие
        if("ANUM".equals(property)) return ANUMERAL;  //порядковое числительное
        if("APRO".equals(property)) return ADJECTIVE_PRONOUN;  //местоименное прилагательное
        if("COM".equals(property)) return COM;  //часть композита (первая часть сложных слов)
        if("CONJ".equals(property)) return CONJUNCTION;  //союз
        if("INTJ".equals(property)) return INTERJECTION;  //междометие
        if("NUM".equals(property)) return NUMERAL;  //числительное
        if("PART".equals(property)) return PARTICLE;  //частица
        if("PR".equals(property)) return PREPOSITION;  //предлог
        if("S".equals(property)) return NOUN;  //существительное
        if("SPRO".equals(property)) return PRONOUN;  //местоимение
        if("V".equals(property)) return VERB;  //глагол

        return UNDEFINED;
    }
}
/*
======= Часть речи
A 	    прилагательное
ADV 	наречие
ADVPRO 	местоименное наречие
ANUM 	порядковое числительное
APRO 	местоименное прилагательное
COM 	часть композита (первая часть сложных слов)
CONJ 	союз
INTJ 	междометие
NUM 	числительное
PART 	частица
PR 	    предлог
S 	    существительное
SPRO 	местоимение
V 	    глагол
*/